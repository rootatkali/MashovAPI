package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;

public class Message {
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
    return MoreObjects.toStringHelper(this).add("messageId", messageId).add("conversationId",
        conversationId).add("senderId", senderId).add("senderName", senderName).add("subject",
        subject).add("lastUpdate", lastUpdate).add("sendTime", sendTime).add("folder", folder).add("isNew", isNew).add("isDeleted", isDeleted).add("sendOnBehalf", sendOnBehalf).add("sendViaEmail", sendViaEmail).add("preventReply", preventReply).toString();
  }
}
