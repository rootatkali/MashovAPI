package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;

/**
 * A data type noting a behave event, which can be justified or not.
 */
public final class Behave {
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
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    Behave behave = (Behave) o;
    
    if (eventCode != behave.eventCode) return false;
    if (justified != behave.justified) return false;
    if (lessonId != behave.lessonId) return false;
    if (groupId != behave.groupId) return false;
    if (lessonType != behave.lessonType) return false;
    if (lesson != behave.lesson) return false;
    if (achvaCode != behave.achvaCode) return false;
    if (justificationId != behave.justificationId) return false;
    if (!studentGuid.equals(behave.studentGuid)) return false;
    if (!reporterGuid.equals(behave.reporterGuid)) return false;
    if (!timestamp.equals(behave.timestamp)) return false;
    if (!lessonDate.equals(behave.lessonDate)) return false;
    if (!lessonReporter.equals(behave.lessonReporter)) return false;
    if (!achvaName.equals(behave.achvaName)) return false;
    if (!achvaAval.equals(behave.achvaAval)) return false;
    if (!justification.equals(behave.justification)) return false;
    if (!reporter.equals(behave.reporter)) return false;
    if (!subject.equals(behave.subject)) return false;
    return justifiedBy.equals(behave.justifiedBy);
  }
  
  @Override
  public int hashCode() {
    int result = studentGuid.hashCode();
    result = 31 * result + eventCode;
    result = 31 * result + justified;
    result = 31 * result + lessonId;
    result = 31 * result + reporterGuid.hashCode();
    result = 31 * result + timestamp.hashCode();
    result = 31 * result + groupId;
    result = 31 * result + lessonType;
    result = 31 * result + lesson;
    result = 31 * result + lessonDate.hashCode();
    result = 31 * result + lessonReporter.hashCode();
    result = 31 * result + achvaCode;
    result = 31 * result + achvaName.hashCode();
    result = 31 * result + achvaAval.hashCode();
    result = 31 * result + justificationId;
    result = 31 * result + justification.hashCode();
    result = 31 * result + reporter.hashCode();
    result = 31 * result + subject.hashCode();
    result = 31 * result + justifiedBy.hashCode();
    return result;
  }
}
