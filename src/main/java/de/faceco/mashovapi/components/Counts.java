package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public final class Counts {
  private int allMessages;
  private int draftMessages;
  private int inboxMessages;
  private int archiveMessages;
  private int sentMessages;
  private int readMessages;
  private int unreadMessages;
  private int newMessages;
  private int deletedMessages;
  private int allConversations;
  private int draftConversations;
  private int inboxConversations;
  private int archiveConversations;
  private int sentConversations;
  private int readConversations;
  private int unreadConversations;
  private int newConversations;
  private int deletedConversations;
  
  Counts() {
  
  }
  
  public int getAllMessages() {
    return allMessages;
  }
  
  public int getDraftMessages() {
    return draftMessages;
  }
  
  public int getInboxMessages() {
    return inboxMessages;
  }
  
  public int getArchiveMessages() {
    return archiveMessages;
  }
  
  public int getSentMessages() {
    return sentMessages;
  }
  
  public int getReadMessages() {
    return readMessages;
  }
  
  public int getUnreadMessages() {
    return unreadMessages;
  }
  
  public int getNewMessages() {
    return newMessages;
  }
  
  public int getDeletedMessages() {
    return deletedMessages;
  }
  
  public int getAllConversations() {
    return allConversations;
  }
  
  public int getDraftConversations() {
    return draftConversations;
  }
  
  public int getInboxConversations() {
    return inboxConversations;
  }
  
  public int getArchiveConversations() {
    return archiveConversations;
  }
  
  public int getSentConversations() {
    return sentConversations;
  }
  
  public int getReadConversations() {
    return readConversations;
  }
  
  public int getUnreadConversations() {
    return unreadConversations;
  }
  
  public int getNewConversations() {
    return newConversations;
  }
  
  public int getDeletedConversations() {
    return deletedConversations;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("allMessages", allMessages)
        .add("draftMessages", draftMessages)
        .add("inboxMessages", inboxMessages)
        .add("archiveMessages", archiveMessages)
        .add("sentMessages", sentMessages)
        .add("readMessages", readMessages)
        .add("unreadMessages", unreadMessages)
        .add("newMessages", newMessages)
        .add("deletedMessages", deletedMessages)
        .add("allConversations", allConversations)
        .add("draftConversations", draftConversations)
        .add("inboxConversations", inboxConversations)
        .add("archiveConversations", archiveConversations)
        .add("sentConversations", sentConversations)
        .add("readConversations", readConversations)
        .add("unreadConversations", unreadConversations)
        .add("newConversations", newConversations)
        .add("deletedConversations", deletedConversations)
        .toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Counts counts = (Counts) o;
    return allMessages == counts.allMessages &&
        draftMessages == counts.draftMessages &&
        inboxMessages == counts.inboxMessages &&
        archiveMessages == counts.archiveMessages &&
        sentMessages == counts.sentMessages &&
        readMessages == counts.readMessages &&
        unreadMessages == counts.unreadMessages &&
        newMessages == counts.newMessages &&
        deletedMessages == counts.deletedMessages &&
        allConversations == counts.allConversations &&
        draftConversations == counts.draftConversations &&
        inboxConversations == counts.inboxConversations &&
        archiveConversations == counts.archiveConversations &&
        sentConversations == counts.sentConversations &&
        readConversations == counts.readConversations &&
        unreadConversations == counts.unreadConversations &&
        newConversations == counts.newConversations &&
        deletedConversations == counts.deletedConversations;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(allMessages, draftMessages, inboxMessages, archiveMessages, sentMessages, readMessages,
        unreadMessages, newMessages, deletedMessages, allConversations, draftConversations, inboxConversations,
        archiveConversations, sentConversations, readConversations, unreadConversations, newConversations,
        deletedConversations);
  }
}
