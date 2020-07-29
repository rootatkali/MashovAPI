package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import de.faceco.mashovapi.API;

import java.util.Objects;

/**
 * A container representing a grading event, which may or may not have a numerical grade. The natural order of Grade
 * elements (as specified by {@link #compareTo(Grade)} is numGrade -- textualGrade.
 *
 * @see API#getGrades()
 */
public final class Grade implements Comparable<Grade> {
  private String studentGuid;
  private int gradingEventId;
  private Integer grade;
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
    if (g == null) return 1;
    if (grade != null && g.grade != null) {
      return Integer.compare(grade, g.grade);
    } else if (grade != null) {
      return 1;
    } else if (g.grade != null) {
      return -1;
    } else if (textualGrade != null && g.textualGrade != null) {
      return textualGrade.compareTo(g.textualGrade);
    } else if (textualGrade != null) {
      return 1;
    } else if (g.textualGrade != null) {
      return -1;
    } else {
      return 0;
    }
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    Grade grade1 = (Grade) o;
    
    if (gradingEventId != grade1.gradingEventId) return false;
    if (Double.compare(grade1.rate, rate) != 0) return false;
    if (groupId != grade1.groupId) return false;
    if (id != grade1.id) return false;
    if (gradingPeriod != grade1.gradingPeriod) return false;
    if (Double.compare(grade1.gradeRate, gradeRate) != 0) return false;
    if (gradeTypeId != grade1.gradeTypeId) return false;
    if (!Objects.equals(studentGuid, grade1.studentGuid)) return false;
    if (!Objects.equals(grade, grade1.grade)) return false;
    if (!Objects.equals(rangeGrade, grade1.rangeGrade)) return false;
    if (!Objects.equals(textualGrade, grade1.textualGrade)) return false;
    if (!Objects.equals(timestamp, grade1.timestamp)) return false;
    if (!Objects.equals(teacherName, grade1.teacherName)) return false;
    if (!Objects.equals(groupName, grade1.groupName)) return false;
    if (!Objects.equals(subjectName, grade1.subjectName)) return false;
    if (!Objects.equals(eventDate, grade1.eventDate)) return false;
    if (!Objects.equals(gradingEvent, grade1.gradingEvent)) return false;
    return Objects.equals(gradeType, grade1.gradeType);
  }
  
  @Override
  public int hashCode() {
    int result;
    long temp;
    result = studentGuid != null ? studentGuid.hashCode() : 0;
    result = 31 * result + gradingEventId;
    result = 31 * result + (grade != null ? grade.hashCode() : 0);
    result = 31 * result + (rangeGrade != null ? rangeGrade.hashCode() : 0);
    result = 31 * result + (textualGrade != null ? textualGrade.hashCode() : 0);
    temp = Double.doubleToLongBits(rate);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
    result = 31 * result + (teacherName != null ? teacherName.hashCode() : 0);
    result = 31 * result + groupId;
    result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
    result = 31 * result + (subjectName != null ? subjectName.hashCode() : 0);
    result = 31 * result + (eventDate != null ? eventDate.hashCode() : 0);
    result = 31 * result + id;
    result = 31 * result + gradingPeriod;
    result = 31 * result + (gradingEvent != null ? gradingEvent.hashCode() : 0);
    temp = Double.doubleToLongBits(gradeRate);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + gradeTypeId;
    result = 31 * result + (gradeType != null ? gradeType.hashCode() : 0);
    return result;
  }
}
