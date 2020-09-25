package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import de.faceco.mashovapi.DateTime;

import java.util.Objects;

public final class JustificationRequest {
  private String endDate;
  private int endLesson;
  private int eventCode;
  private String requestComment;
  private int requestedJustification;
  private String requestedFiles;
  private int requestStatus;
  private String startDate;
  private int startLesson;
  private String userGuid;
  
  public JustificationRequest(DateTime endDate, int endLesson, int eventCode, String requestComment,
                              int requestedJustification, String requestedFiles, int requestStatus, DateTime startDate,
                              int startLesson, String userGuid) {
    this.endDate = endDate.toString();
    this.endLesson = endLesson;
    this.eventCode = eventCode;
    this.requestComment = requestComment;
    this.requestedJustification = requestedJustification;
    this.requestedFiles = requestedFiles;
    this.requestStatus = requestStatus;
    this.startDate = startDate.toString();
    this.startLesson = startLesson;
    this.userGuid = userGuid;
  }
  
  public JustificationRequest(DateTime endDate, int endLesson, Achva event, String requestComment,
                              Justification justification, String requestedFiles, int requestStatus, DateTime startDate,
                              int startLesson, String userGuid) {
    this(endDate, endLesson, event.getCode(), requestComment, justification.getJustificationId(), requestedFiles,
        requestStatus, startDate, startLesson, userGuid);
  }
  
  public String getEndDate() {
    return endDate;
  }
  
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
  
  public int getEndLesson() {
    return endLesson;
  }
  
  public void setEndLesson(int endLesson) {
    this.endLesson = endLesson;
  }
  
  public int getEventCode() {
    return eventCode;
  }
  
  public void setEventCode(int eventCode) {
    this.eventCode = eventCode;
  }
  
  public String getRequestComment() {
    return requestComment;
  }
  
  public void setRequestComment(String requestComment) {
    this.requestComment = requestComment;
  }
  
  public int getRequestedJustification() {
    return requestedJustification;
  }
  
  public void setRequestedJustification(int requestedJustification) {
    this.requestedJustification = requestedJustification;
  }
  
  public String getRequestedFiles() {
    return requestedFiles;
  }
  
  public void setRequestedFiles(String requestedFiles) {
    this.requestedFiles = requestedFiles;
  }
  
  public int getRequestStatus() {
    return requestStatus;
  }
  
  public void setRequestStatus(int requestStatus) {
    this.requestStatus = requestStatus;
  }
  
  public String getStartDate() {
    return startDate;
  }
  
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
  
  public int getStartLesson() {
    return startLesson;
  }
  
  public void setStartLesson(int startLesson) {
    this.startLesson = startLesson;
  }
  
  public String getUserGuid() {
    return userGuid;
  }
  
  public void setUserGuid(String userGuid) {
    this.userGuid = userGuid;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("endDate", endDate)
        .add("endLesson", endLesson)
        .add("eventCode", eventCode)
        .add("requestComment", requestComment)
        .add("requestedJustification", requestedJustification)
        .add("requestedFiles", requestedFiles)
        .add("requestStatus", requestStatus)
        .add("startDate", startDate)
        .add("startLesson", startLesson)
        .add("userGuid", userGuid)
        .toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    JustificationRequest that = (JustificationRequest) o;
    return endLesson == that.endLesson &&
        eventCode == that.eventCode &&
        requestedJustification == that.requestedJustification &&
        requestStatus == that.requestStatus &&
        startLesson == that.startLesson &&
        Objects.equals(endDate, that.endDate) &&
        Objects.equals(requestComment, that.requestComment) &&
        Objects.equals(requestedFiles, that.requestedFiles) &&
        Objects.equals(startDate, that.startDate) &&
        Objects.equals(userGuid, that.userGuid);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(endDate, endLesson, eventCode, requestComment, requestedJustification, requestedFiles,
        requestStatus, startDate, startLesson, userGuid);
  }
}
