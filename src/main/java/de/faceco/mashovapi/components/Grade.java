package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;

public class Grade {
  private String studentGuid;
  private int gradingEventId;
  private int grade;
  private String rangeGrade;
  private double rate;
  private String timestamp;
  private String teacherName;
  private int groupId;
  private String groupName;
  private String subjectName;
  private String eventDate;
  private int id;
  private int gradingPeriod;
  private String gradingEvent;
  private double gradeRate;
  private int gradeTypeId;
  private String gradeType;
  
  Grade() {
  
  }
  
  public String getStudentGuid() {
    return studentGuid;
  }
  
  public int getGradingEventId() {
    return gradingEventId;
  }
  
  public int getGrade() {
    return grade;
  }
  
  public String getRangeGrade() {
    return rangeGrade;
  }
  
  public double getRate() {
    return rate;
  }
  
  public String getTimestamp() {
    return timestamp;
  }
  
  public String getTeacherName() {
    return teacherName;
  }
  
  public int getGroupId() {
    return groupId;
  }
  
  public String getGroupName() {
    return groupName;
  }
  
  public String getSubjectName() {
    return subjectName;
  }
  
  public String getEventDate() {
    return eventDate;
  }
  
  public int getId() {
    return id;
  }
  
  public int getGradingPeriod() {
    return gradingPeriod;
  }
  
  public String getGradingEvent() {
    return gradingEvent;
  }
  
  public double getGradeRate() {
    return gradeRate;
  }
  
  public int getGradeTypeId() {
    return gradeTypeId;
  }
  
  public String getGradeType() {
    return gradeType;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("studentGuid", studentGuid).add("gradingEventId",
        gradingEventId).add("grade", grade).add("rangeGrade", rangeGrade).add("rate", rate).add(
            "timestamp", timestamp).add("teacherName", teacherName).add("groupId", groupId).add(
                "groupName", groupName).add("subjectName", subjectName).add("eventDate",
        eventDate).add("id", id).add("gradingPeriod", gradingPeriod).add("gradingEvent",
        gradingEvent).add("gradeRate", gradeRate).add("gradeTypeId", gradeTypeId).add("gradeType"
        , gradeType).toString();
  }
}
