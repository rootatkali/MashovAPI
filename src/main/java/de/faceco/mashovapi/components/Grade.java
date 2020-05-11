package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import de.faceco.mashovapi.API;
import org.jetbrains.annotations.NotNull;

/**
 * A container representing a grading event, which may or may not have a numerical grade. The natural order of Grade
 * elements (as specified by {@link #compareTo(Grade)} is numGrade -> textualGrade.
 *
 * @see API#getGrades()
 */
public final class Grade implements Comparable<Grade> {
  private String studentGuid;
  private int gradingEventId;
  private int grade;
  private String rangeGrade;
  private String textualGrade;
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
  
  public Integer getGrade() {
    return grade;
  }
  
  public String getRangeGrade() {
    return rangeGrade;
  }
  
  public String getTextualGrade() {
    return textualGrade;
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
    return MoreObjects.toStringHelper(this)
        .add("studentGuid", studentGuid)
        .add("gradingEventId", gradingEventId)
        .add("grade", grade)
        .add("rangeGrade", rangeGrade)
        .add("textualGrade", textualGrade)
        .add("rate", rate)
        .add("timestamp", timestamp)
        .add("teacherName", teacherName)
        .add("groupId", groupId)
        .add("groupName", groupName)
        .add("subjectName", subjectName)
        .add("eventDate", eventDate)
        .add("id", id)
        .add("gradingPeriod", gradingPeriod)
        .add("gradingEvent", gradingEvent)
        .add("gradeRate", gradeRate)
        .add("gradeTypeId", gradeTypeId)
        .add("gradeType", gradeType)
        .toString();
  }
  
  @Override
  public int compareTo(Grade g) {
    if (textualGrade == null) return Integer.compare(grade, g.grade);
    
    return ComparisonChain.start()
        .compare(grade, g.grade)
        .compare(textualGrade, g.textualGrade)
        .result();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    Grade g = (Grade) o;
    
    if (gradingEventId != g.gradingEventId) return false;
    if (Double.compare(g.rate, rate) != 0) return false;
    if (groupId != g.groupId) return false;
    if (id != g.id) return false;
    if (gradingPeriod != g.gradingPeriod) return false;
    if (Double.compare(g.gradeRate, gradeRate) != 0) return false;
    if (gradeTypeId != g.gradeTypeId) return false;
    if (!studentGuid.equals(g.studentGuid)) return false;
    if (grade != g.grade) return false;
    if (!rangeGrade.equals(g.rangeGrade)) return false;
    if (!textualGrade.equals(g.textualGrade)) return false;
    if (!timestamp.equals(g.timestamp)) return false;
    if (!teacherName.equals(g.teacherName)) return false;
    if (!groupName.equals(g.groupName)) return false;
    if (!subjectName.equals(g.subjectName)) return false;
    if (!eventDate.equals(g.eventDate)) return false;
    if (!gradingEvent.equals(g.gradingEvent)) return false;
    return gradeType.equals(g.gradeType);
  }
  
  @Override
  public int hashCode() {
    int result;
    long temp;
    result = studentGuid.hashCode();
    result = 31 * result + gradingEventId;
    result = 31 * result + grade;
    result = 31 * result + rangeGrade.hashCode();
    result = 31 * result + textualGrade.hashCode();
    temp = Double.doubleToLongBits(rate);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + timestamp.hashCode();
    result = 31 * result + teacherName.hashCode();
    result = 31 * result + groupId;
    result = 31 * result + groupName.hashCode();
    result = 31 * result + subjectName.hashCode();
    result = 31 * result + eventDate.hashCode();
    result = 31 * result + id;
    result = 31 * result + gradingPeriod;
    result = 31 * result + gradingEvent.hashCode();
    temp = Double.doubleToLongBits(gradeRate);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + gradeTypeId;
    result = 31 * result + gradeType.hashCode();
    return result;
  }
}
