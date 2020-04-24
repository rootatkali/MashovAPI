package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;

public class Conversation {
  private String conversationId;
  private String subject;
  private String sendTime;
  private boolean isNew;
  private boolean hasDrafts;
  private boolean hasAttachments;
  private Message[] messages;
  private String[] labels;
  private boolean preventReply;
  
  Conversation() {
  
  }
  
  public String getConversationId() {
    return conversationId;
  }
  
  public String getSubject() {
    return subject;
  }
  
  public String getSendTime() {
    return sendTime;
  }
  
  public boolean isNew() {
    return isNew;
  }
  
  public boolean hasDrafts() {
    return hasDrafts;
  }
  
  public boolean hasAttachments() {
    return hasAttachments;
  }
  
  public Message[] getMessages() {
    return messages;
  }
  
  public String[] getLabels() {
    return labels;
  }
  
  public boolean isPreventReply() {
    return preventReply;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("conversationId", conversationId).add("subject",
        subject).add("sendTime", sendTime).add("isNew", isNew).add("hasDrafts", hasDrafts).add(
            "hasAttachments", hasAttachments).add("messages", messages).add("labels", labels).add("preventReply", preventReply).toString();
  }
}
