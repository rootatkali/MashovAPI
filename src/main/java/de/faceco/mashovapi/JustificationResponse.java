package de.faceco.mashovapi;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public final class JustificationResponse {
  private long requestId;
  private long studentId;
  private int eventCode;
  private int requestedJustification;
  private String userGuid;
  private String requestTime;
  private String startDate;
  private String endDate;
  private int startLesson;
  private int endLesson;
  private int requestStatus;
  private String requestComment;
  
  JustificationResponse() {
  
  }
  
  public long getRequestId() {
    return requestId;
  }
  
  public long getStudentId() {
    return studentId;
  }
  
  public int getEventCode() {
    return eventCode;
  }
  
  public int getRequestedJustification() {
    return requestedJustification;
  }
  
  public String getUserGuid() {
    return userGuid;
  }
  
  public String getRequestTime() {
    return requestTime;
  }
  
  public String getStartDate() {
    return startDate;
  }
  
  public String getEndDate() {
    return endDate;
  }
  
  public int getStartLesson() {
    return startLesson;
  }
  
  public int getEndLesson() {
    return endLesson;
  }
  
  public int getRequestStatus() {
    return requestStatus;
  }
  
  public String getRequestComment() {
    return requestComment;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("requestId", requestId)
        .add("studentId", studentId)
        .add("eventCode", eventCode)
        .add("requestedJustification", requestedJustification)
        .add("userGuid", userGuid)
        .add("requestTime", requestTime)
        .add("startDate", startDate)
        .add("endDate", endDate)
        .add("startLesson", startLesson)
        .add("endLesson", endLesson)
        .add("requestStatus", requestStatus)
        .add("requestComment", requestComment)
        .toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    JustificationResponse that = (JustificationResponse) o;
    return requestId == that.requestId &&
        studentId == that.studentId &&
        eventCode == that.eventCode &&
        requestedJustification == that.requestedJustification &&
        startLesson == that.startLesson &&
        endLesson == that.endLesson &&
        requestStatus == that.requestStatus &&
        Objects.equals(userGuid, that.userGuid) &&
        Objects.equals(requestTime, that.requestTime) &&
        Objects.equals(startDate, that.startDate) &&
        Objects.equals(endDate, that.endDate) &&
        Objects.equals(requestComment, that.requestComment);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(requestId, studentId, eventCode, requestedJustification, userGuid, requestTime, startDate,
        endDate, startLesson, endLesson, requestStatus, requestComment);
  }
}
