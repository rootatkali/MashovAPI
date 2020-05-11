package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;

import java.util.Arrays;

public final class Message {
  private String messageId;
  private String conversationId;
  private String senderId;
  private String senderName;
  private String subject;
  private String body;
  private String lastUpdate;
  private String sendTime;
  private Recipient[] recipients;
  private int folder;
  private boolean isNew;
  private boolean isDeleted;
  private Attachment[] files;
  private String[] labels;
  private boolean sendOnBehalf;
  private boolean sendViaEmail;
  private boolean preventReply;
  
  Message() {
  
  }
  
  public String getMessageId() {
    return messageId;
  }
  
  public String getConversationId() {
    return conversationId;
  }
  
  public String getSenderId() {
    return senderId;
  }
  
  public String getSenderName() {
    return senderName;
  }
  
  public String getSubject() {
    return subject;
  }
  
  public String getBody() {
    return body;
  }
  
  public String getLastUpdate() {
    return lastUpdate;
  }
  
  public String getSendTime() {
    return sendTime;
  }
  
  public Recipient[] getRecipients() {
    return recipients;
  }
  
  public int getFolder() {
    return folder;
  }
  
  public boolean isNew() {
    return isNew;
  }
  
  public boolean isDeleted() {
    return isDeleted;
  }
  
  public Attachment[] getFiles() {
    return files;
  }
  
  public String[] getLabels() {
    return labels;
  }
  
  public boolean isSendOnBehalf() {
    return sendOnBehalf;
  }
  
  public boolean isSendViaEmail() {
    return sendViaEmail;
  }
  
  public boolean isPreventReply() {
    return preventReply;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("messageId", messageId)
        .add("conversationId", conversationId)
        .add("senderId", senderId)
        .add("senderName", senderName)
        .add("subject", subject)
        .add("body", body)
        .add("lastUpdate", lastUpdate)
        .add("sendTime", sendTime)
        .add("recipients", recipients)
        .add("folder", folder)
        .add("isNew", isNew)
        .add("isDeleted", isDeleted)
        .add("files", files)
        .add("labels", labels)
        .add("sendOnBehalf", sendOnBehalf)
        .add("sendViaEmail", sendViaEmail)
        .add("preventReply", preventReply)
        .toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    Message message = (Message) o;
    
    if (folder != message.folder) return false;
    if (isNew != message.isNew) return false;
    if (isDeleted != message.isDeleted) return false;
    if (sendOnBehalf != message.sendOnBehalf) return false;
    if (sendViaEmail != message.sendViaEmail) return false;
    if (preventReply != message.preventReply) return false;
    if (!messageId.equals(message.messageId)) return false;
    if (!conversationId.equals(message.conversationId)) return false;
    if (!senderId.equals(message.senderId)) return false;
    if (!senderName.equals(message.senderName)) return false;
    if (!subject.equals(message.subject)) return false;
    if (!body.equals(message.body)) return false;
    if (!lastUpdate.equals(message.lastUpdate)) return false;
    if (!sendTime.equals(message.sendTime)) return false;
    if (!Arrays.equals(recipients, message.recipients)) return false;
    if (!Arrays.equals(files, message.files)) return false;
    return Arrays.equals(labels, message.labels);
  }
  
  @Override
  public int hashCode() {
    int result = messageId.hashCode();
    result = 31 * result + conversationId.hashCode();
    result = 31 * result + senderId.hashCode();
    result = 31 * result + senderName.hashCode();
    result = 31 * result + subject.hashCode();
    result = 31 * result + body.hashCode();
    result = 31 * result + lastUpdate.hashCode();
    result = 31 * result + sendTime.hashCode();
    result = 31 * result + Arrays.hashCode(recipients);
    result = 31 * result + folder;
    result = 31 * result + (isNew ? 1 : 0);
    result = 31 * result + (isDeleted ? 1 : 0);
    result = 31 * result + Arrays.hashCode(files);
    result = 31 * result + Arrays.hashCode(labels);
    result = 31 * result + (sendOnBehalf ? 1 : 0);
    result = 31 * result + (sendViaEmail ? 1 : 0);
    result = 31 * result + (preventReply ? 1 : 0);
    return result;
  }
}
