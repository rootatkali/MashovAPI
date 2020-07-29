package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import de.faceco.mashovapi.API;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A class for generating outgoing messages. A message built as a reply should use the {@link #from(Conversation)}
 * static method to build, while a new message should use the {@link #asNew()} method. SendMessage objects are immutable
 * and cannot be changed after creation.
 *
 * <p>An example for building a new message:</p>
 * <pre>
 *   Recipient[] rec = API.getInstance().getMailRecipients();
 *   Message callback = SendMessage.asNew()
 *       .to(rec[0])
 *       .cc(rec[1], rec[2])
 *       .subject("Test message")
 *       .body("&lt;p&gt;Html content&lt;/p&gt;")
 *       .attachAll("/path/to/file1", "/path/to/file2")
 *       .send();
 * </pre>
 *
 * <p>An example for responding to a conversation:</p>
 * <pre>
 *   Conversation c = API.getInstance().getInbox()[0];
 *   Message callback = SendMessage.from(c)
 *       .body("&lt;p&gt;Html content&lt;/p&gt;")
 *       .send();
 * </pre>
 */
public final class SendMessage {
  private transient boolean reply;
  
  private String messageId;
  private final String conversationId;
  private final String senderId;
  private String subject;
  private String body;
  private final String lastUpdate;
  private Recipient[] recipients;
  private Recipient[] cc;
  private Recipient[] bcc;
  private Attachment[] files;
  private final int folder;
  private boolean isNew;
  private final boolean isDeleted;
  private final String[] labels;
  private final boolean sendOnBehalf;
  private final boolean sendViaEmail;
  private final boolean preventReply;
  private final String lastSaved;
  
  private SendMessage(String messageId, String conversationId) {
    this.messageId = messageId;
    this.conversationId = conversationId;
    this.senderId = API.getInstance().getStudentId();
    reply = false;
    sendOnBehalf = false;
    sendViaEmail = false;
    isDeleted = false;
    folder = 0;
    labels = new String[0];
    preventReply = false;
    lastSaved = LocalDateTime.now().toString();
    lastUpdate = lastSaved;
  }
  
  /**
   * Generate a new SendMessage with a conversation, without a messageId.
   */
  SendMessage(Conversation c) throws IOException {
    this(null, c.getConversationId());
    reply = true;
    isNew = true;
    subject = c.getSubject();
    body = c.getMessages()[0].getBody();
  
    Recipient[] recipients = API.getInstance().getMailRecipients();
    String senderId = c.getMessages()[c.getMessages().length - 1].getSenderId();
    Recipient og = null;
    
    for (Recipient r : recipients) {
      if (r.getValue().equals(senderId)) {
        og = r;
        break;
      }
    }
    if (og == null) throw new NullPointerException();
    
    this.recipients = new Recipient[]{og};
  }
  
  /**
   * Creates a new SendMessage instance from an existing conversation - a reply.
   *
   * @param c The conversation to reply to.
   * @return The created SendMessage object.
   * @throws IOException If the Mashov server is inaccessible.
   */
  public static SendMessage from(Conversation c) throws IOException {
    Objects.requireNonNull(c);
    SendMessage ret = new SendMessage(c);
    ret.messageId = RequestController.msgIdReply(c);
    return ret;
  }
  
  /**
   * Creates a new SendMessage instance using a new conversation.
   *
   * @return The created SendMessage instance.
   * @throws IOException If an error occurs.
   */
  public static SendMessage asNew() throws IOException {
    Message m = RequestController.msgNew();
    SendMessage ret = new SendMessage(m.getMessageId(), m.getConversationId());
    ret.isNew = true;
    return ret;
  }
  
  /**
   * Sets the subject of the message to be sent. Use only on non-reply SendMessages.
   *
   * @param subject The subject to set.
   * @return The SendMessage instance.
   */
  public SendMessage subject(String subject) {
    if (reply) throw new RuntimeException();
    if (this.subject != null && !this.subject.isEmpty()) throw new RuntimeException("Subject already set.");
    Objects.requireNonNull(subject);
    this.subject = subject;
    return this;
  }
  
  /**
   * Sets the content of the response. If this is a reply, the contents of the ast message will be in a blockquote.
   *
   * @param body The message body, in proper HTML formatting.
   * @return This.
   */
  public SendMessage body(String body) {
    Objects.requireNonNull(body);
    if (this.body == null || this.body.isEmpty()) {
      this.body = body;
    } else {
      this.body = body + "\n<p></p><blockquote>" + this.body + "\n</blockquote>";
    }
    return this;
  }
  
  /**
   * Set the recipients of the message. Do not use on reply messages.
   *
   * @param recipients The recipients to choose.
   * @return This.
   */
  public SendMessage to(Recipient... recipients) {
    if (reply) throw new RuntimeException();
    if (recipients == null || recipients.length == 0) throw new NullPointerException();
    this.recipients = recipients;
    return this;
  }
  
  /**
   * Select the CC recipients (Carbon Copy). Do not use on reply messages.
   *
   * @param recipients An array of recipients.
   * @return This.
   */
  public SendMessage cc(Recipient... recipients) {
    if (reply) throw new RuntimeException();
    if (recipients == null || recipients.length == 0) throw new NullPointerException();
    this.cc = recipients;
    return this;
  }
  
  /**
   * Select the BCC recipients (Black Carbon Copy). Do not use on reply messages.
   *
   * @param recipients An array of recipients.
   * @return This.
   */
  public SendMessage bcc(Recipient... recipients) {
    if (reply) throw new RuntimeException();
    if (recipients == null || recipients.length == 0) throw new NullPointerException();
    this.bcc = recipients;
    return this;
  }
  
  /**
   * Attempts to upload a file to attach. If it does not succeed, it will return this object without any changes.
   *
   * @param file The file to upload
   * @return This.
   */
  public SendMessage attach(File file) throws IOException {
    List<Attachment> list;
    if (files != null) {
      list = new ArrayList<>(Arrays.asList(files));
    } else {
      list = new ArrayList<>();
    }
    
    Attachment attach = RequestController.file(this, file);
    list.add(attach);
    
    files = list.toArray(new Attachment[0]);
    return this;
  }
  
  /**
   * Attempts to attach a file. If it does not succeed, it will return this object without any changes.
   *
   * @param path The path to the file to upload.
   * @return This.
   */
  public SendMessage attach(String path) throws IOException {
    return attach(new File(path));
  }
  
  /**
   * Attempts to attach multiple files to the message.
   *
   * @param paths Paths to files to upload
   * @return This.
   */
  public SendMessage attachAll(String... paths) throws IOException {
    for (String path : paths) {
      attach(path);
    }
    
    return this;
  }
  
  /**
   * Attempts to attach multiple files to the message.
   *
   * @param files All files to upload
   * @return This.
   */
  public SendMessage attachAll(File... files) throws IOException {
    for (File file : files) {
      attach(file);
    }
    
    return this;
  }
  
  /**
   * Attempts to send the message. Use only after setting (recipients, a subject and) a body.
   *
   * @return The callback message from the Mashov web servers.
   * @throws IOException If the message could not be sent.
   */
  public Message send() throws IOException {
    this.isNew = false;
    return RequestController.send(this);
  }
  
  String getConversationId() {
    return conversationId;
  }
  
  public boolean isNew() {
    return isNew;
  }
  
  String getMessageId() {
    return messageId;
  }
  
  public Recipient[] getRecipients() {
    return recipients;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("fromConv", reply)
        .add("messageId", messageId)
        .add("conversationId", conversationId)
        .add("senderId", senderId)
        .add("subject", subject)
        .add("body", body)
        .add("lastUpdate", lastUpdate)
        .add("recipients", recipients)
        .add("cc", cc)
        .add("bcc", bcc)
        .add("files", files)
        .add("folder", folder)
        .add("isNew", isNew)
        .add("isDeleted", isDeleted)
        .add("labels", labels)
        .add("sendOnBehalf", sendOnBehalf)
        .add("sendViaEmail", sendViaEmail)
        .add("preventReply", preventReply)
        .add("lastSaved", lastSaved)
        .toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
  
    SendMessage sm = (SendMessage) o;
  
    if (folder != sm.folder) return false;
    if (isNew != sm.isNew) return false;
    if (isDeleted != sm.isDeleted) return false;
    if (sendOnBehalf != sm.sendOnBehalf) return false;
    if (sendViaEmail != sm.sendViaEmail) return false;
    if (preventReply != sm.preventReply) return false;
    if (!messageId.equals(sm.messageId)) return false;
    if (!conversationId.equals(sm.conversationId)) return false;
    if (!senderId.equals(sm.senderId)) return false;
    if (!Objects.equals(subject, sm.subject)) return false;
    if (!Objects.equals(body, sm.body)) return false;
    if (!Objects.equals(lastUpdate, sm.lastUpdate)) return false;
    if (!Arrays.equals(recipients, sm.recipients)) return false;
    if (!Arrays.equals(cc, sm.cc)) return false;
    if (!Arrays.equals(bcc, sm.bcc)) return false;
    if (!Arrays.equals(labels, sm.labels)) return false;
    return Objects.equals(lastSaved, sm.lastSaved);
  }
  
  @Override
  public int hashCode() {
    int result = messageId.hashCode();
    result = 31 * result + conversationId.hashCode();
    result = 31 * result + senderId.hashCode();
    result = 31 * result + (subject != null ? subject.hashCode() : 0);
    result = 31 * result + (body != null ? body.hashCode() : 0);
    result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
    result = 31 * result + Arrays.hashCode(recipients);
    result = 31 * result + Arrays.hashCode(cc);
    result = 31 * result + Arrays.hashCode(bcc);
    result = 31 * result + folder;
    result = 31 * result + (isNew ? 1 : 0);
    result = 31 * result + (isDeleted ? 1 : 0);
    result = 31 * result + Arrays.hashCode(labels);
    result = 31 * result + (sendOnBehalf ? 1 : 0);
    result = 31 * result + (sendViaEmail ? 1 : 0);
    result = 31 * result + (preventReply ? 1 : 0);
    result = 31 * result + (lastSaved != null ? lastSaved.hashCode() : 0);
    return result;
  }
}
