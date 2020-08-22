package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import de.faceco.mashovapi.API;

import java.util.Arrays;

/**
 * A container for a conversation. Contains an array of {@link Message} objects, corresponding to the actual messages.
 *
 * @see de.faceco.mashovapi.components.Message
 * @see API#getInbox()
 */
public final class Conversation {
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
        "hasAttachments", hasAttachments).add("messages", messages).add("labels", labels).add("preventReply",
        preventReply).toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    Conversation c = (Conversation) o;
    
    if (isNew != c.isNew) return false;
    if (hasDrafts != c.hasDrafts) return false;
    if (hasAttachments != c.hasAttachments) return false;
    if (preventReply != c.preventReply) return false;
    if (!conversationId.equals(c.conversationId)) return false;
    if (!subject.equals(c.subject)) return false;
    if (!sendTime.equals(c.sendTime)) return false;
    if (!Arrays.equals(messages, c.messages)) return false;
    return Arrays.equals(labels, c.labels);
  }
  
  @Override
  public int hashCode() {
    int result = conversationId.hashCode();
    result = 31 * result + subject.hashCode();
    result = 31 * result + sendTime.hashCode();
    result = 31 * result + (isNew ? 1 : 0);
    result = 31 * result + (hasDrafts ? 1 : 0);
    result = 31 * result + (hasAttachments ? 1 : 0);
    result = 31 * result + Arrays.hashCode(messages);
    result = 31 * result + Arrays.hashCode(labels);
    result = 31 * result + (preventReply ? 1 : 0);
    return result;
  }
}
