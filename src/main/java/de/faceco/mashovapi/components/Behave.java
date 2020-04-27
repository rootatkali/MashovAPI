package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;

public class Behave {
  private String studentGuid;
  private int eventCode;
  private int justified;
  private int lessonId;
  private String reporterGuid;
  private String timestamp;
  private int groupId;
  private int lessonType;
  private int lesson;
  private String lessonDate;
  private String lessonReporter;
  private int achvaCode;
  private String achvaName;
  private String achvaAval;
  private int justificationId;
  private String justification;
  private String reporter;
  private String subject;
  private String justifiedBy;
  
  Behave() {
  
  }
  
  public String getStudentGuid() {
    return studentGuid;
  }
  
  public int getEventCode() {
    return eventCode;
  }
  
  public int getJustified() {
    return justified;
  }
  
  public int getLessonId() {
    return lessonId;
  }
  
  public String getReporterGuid() {
    return reporterGuid;
  }
  
  public String getTimestamp() {
    return timestamp;
  }
  
  public int getGroupId() {
    return groupId;
  }
  
  public int getLessonType() {
    return lessonType;
  }
  
  public int getLesson() {
    return lesson;
  }
  
  public String getLessonDate() {
    return lessonDate;
  }
  
  public String getLessonReporter() {
    return lessonReporter;
  }
  
  public int getAchvaCode() {
    return achvaCode;
  }
  
  public String getAchvaName() {
    return achvaName;
  }
  
  public String getAchvaAval() {
    return achvaAval;
  }
  
  public int getJustificationId() {
    return justificationId;
  }
  
  public String getJustification() {
    return justification;
  }
  
  public String getReporter() {
    return reporter;
  }
  
  public String getSubject() {
    return subject;
  }
  
  public String getJustifiedBy() {
    return justifiedBy;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("studentGuid", studentGuid)
        .add("eventCode", eventCode)
        .add("justified", justified)
        .add("lessonId", lessonId)
        .add("reporterGuid", reporterGuid)
        .add("timestamp", timestamp)
        .add("groupId", groupId)
        .add("lessonType", lessonType)
        .add("lesson", lesson)
        .add("lessonDate", lessonDate)
        .add("lessonReporter", lessonReporter)
        .add("achvaCode", achvaCode)
        .add("achvaName", achvaName)
        .add("achvaAval", achvaAval)
        .add("justificationId", justificationId)
        .add("justification", justification)
        .add("reporter", reporter)
        .add("subject", subject)
        .add("justifiedBy", justifiedBy)
        .toString();
  }
}
