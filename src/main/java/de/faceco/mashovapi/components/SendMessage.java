package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import com.google.gson.Gson;
import de.faceco.mashovapi.API;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
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
  private transient boolean fromConv;
  
  private String messageId;
  private final String conversationId;
  private final String senderId;
  private String subject;
  private String body;
  private String lastUpdate;
  private Recipient[] recipients;
  private Recipient[] cc;
  private Recipient[] bcc;
  private int folder;
  private boolean isNew;
  private boolean isDeleted;
  private String[] labels;
  private boolean sendOnBehalf;
  private boolean sendViaEmail;
  private boolean preventReply;
  private String lastSaved;
  
  private SendMessage(String messageId, String conversationId) {
    this.messageId = messageId;
    this.conversationId = conversationId;
    this.senderId = API.getInstance().getStudentId();
    fromConv = false;
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
    fromConv = true;
    isNew = true;
    subject = c.getSubject();
    body = c.getMessages()[c.getMessages().length - 1].getBody();
  
    Recipient[] recipients = API.getInstance().getMailRecipients();
    String senderId = c.getMessages()[0].getSenderId();
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
   * @param subject The subject to set.
   * @return The SendMessage instance.
   */
  public SendMessage subject(String subject) {
    if (fromConv) throw new RuntimeException();
    if (this.subject != null && !this.subject.isEmpty()) throw new RuntimeException("Subject already set.");
    Objects.requireNonNull(subject);
    this.subject = subject;
    return this;
  }
  
  /**
   * Sets the content of the response. If this is a reply, the contents of the ast message will be in a blockquote.
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
   * @param recipients The recipients to choose.
   * @return This.
   */
  public SendMessage to(Recipient... recipients) {
    if (fromConv) throw new RuntimeException();
    if (recipients == null || recipients.length == 0) throw new NullPointerException();
    this.recipients = recipients;
    return this;
  }
  
  /**
   * Select the CC recipients (Carbon Copy). Do not use on reply messages.
   * @param recipients An array of recipients.
   * @return This.
   */
  public SendMessage cc(Recipient... recipients) {
    if (fromConv) throw new RuntimeException();
    if (recipients == null || recipients.length == 0) throw new NullPointerException();
    this.cc = recipients;
    return this;
  }
  
  /**
   * Select the BCC recipients (Black Carbon Copy). Do not use on reply messages.
   * @param recipients An array of recipients.
   * @return This.
   */
  public SendMessage bcc(Recipient... recipients) {
    if (fromConv) throw new RuntimeException();
    if (recipients == null || recipients.length == 0) throw new NullPointerException();
    this.bcc = recipients;
    return this;
  }
  
  /**
   * Attempts to send the message. Use only after setting (recipients, a subject and) a body.
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
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("messageId", messageId)
        .add("conversationId", conversationId)
        .add("senderId", senderId)
        .add("subject", subject)
        .add("body", body)
        .add("lastUpdate", lastUpdate)
        .add("recipients", recipients)
        .add("cc", cc)
        .add("bcc", bcc)
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
    
    SendMessage that = (SendMessage) o;
    
    if (folder != that.folder) return false;
    if (isNew != that.isNew) return false;
    if (isDeleted != that.isDeleted) return false;
    if (sendOnBehalf != that.sendOnBehalf) return false;
    if (sendViaEmail != that.sendViaEmail) return false;
    if (preventReply != that.preventReply) return false;
    if (!messageId.equals(that.messageId)) return false;
    if (!conversationId.equals(that.conversationId)) return false;
    if (!senderId.equals(that.senderId)) return false;
    if (!Objects.equals(subject, that.subject)) return false;
    if (!Objects.equals(body, that.body)) return false;
    if (!Objects.equals(lastUpdate, that.lastUpdate)) return false;
    if (!Arrays.equals(recipients, that.recipients)) return false;
    if (!Arrays.equals(cc, that.cc)) return false;
    if (!Arrays.equals(bcc, that.bcc)) return false;
    if (!Arrays.equals(labels, that.labels)) return false;
    return Objects.equals(lastSaved, that.lastSaved);
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
