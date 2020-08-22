package de.faceco.mashovapi.components.logininfo;

import com.google.common.base.MoreObjects;

public class SchoolOptions {
  private boolean hasParents;
  private boolean hasParentsOutgoingMail;
  private boolean hasParentsAlfon;
  private boolean hasParentsHistory;
  private boolean hasParentsBehave;
  private boolean hasParentsMaakav;
  private boolean hasParentsRegularGrades;
  private boolean hasParentsPeriodicGrades;
  private boolean denyStudentsJustificationRequests;
  private boolean allowStudentsGroupPlans;
  private boolean denyTeachersToSendEmails;
  private boolean denyInviteBbbExternalParticipant;
  private boolean forceFillLessonMethods;
  private boolean offerToDuplicateAllLessons;
  private int maxGrade;
  private int maxPeriodGrade;
  private boolean hasBagrutGrades;
  private boolean hasNikud;
  private String schoolSite;
  private String moodleSite;
  private String iscoolSite;
  private String schoolName;
  private int[] schoolYears;
  private boolean isReadOnly;
  private boolean denyTeachersEditStudentEvaluation;
  
  public SchoolOptions() {
  
  }
  
  public boolean hasParents() {
    return hasParents;
  }
  
  public boolean hasParentsOutgoingMail() {
    return hasParentsOutgoingMail;
  }
  
  public boolean hasParentsAlfon() {
    return hasParentsAlfon;
  }
  
  public boolean hasParentsHistory() {
    return hasParentsHistory;
  }
  
  public boolean hasParentsBehave() {
    return hasParentsBehave;
  }
  
  public boolean hasParentsMaakav() {
    return hasParentsMaakav;
  }
  
  public boolean hasParentsRegularGrades() {
    return hasParentsRegularGrades;
  }
  
  public boolean hasParentsPeriodicGrades() {
    return hasParentsPeriodicGrades;
  }
  
  public boolean isDenyStudentsJustificationRequests() {
    return denyStudentsJustificationRequests;
  }
  
  public boolean isAllowStudentsGroupPlans() {
    return allowStudentsGroupPlans;
  }
  
  public boolean isDenyTeachersToSendEmails() {
    return denyTeachersToSendEmails;
  }
  
  public boolean isDenyInviteBbbExternalParticipant() {
    return denyInviteBbbExternalParticipant;
  }
  
  public boolean isForceFillLessonMethods() {
    return forceFillLessonMethods;
  }
  
  public boolean isOfferToDuplicateAllLessons() {
    return offerToDuplicateAllLessons;
  }
  
  public int getMaxGrade() {
    return maxGrade;
  }
  
  public int getMaxPeriodGrade() {
    return maxPeriodGrade;
  }
  
  public boolean hasBagrutGrades() {
    return hasBagrutGrades;
  }
  
  public boolean hasNikud() {
    return hasNikud;
  }
  
  public String getSchoolSite() {
    return schoolSite;
  }
  
  public String getMoodleSite() {
    return moodleSite;
  }
  
  public String getIscoolSite() {
    return iscoolSite;
  }
  
  public String getSchoolName() {
    return schoolName;
  }
  
  public int[] getSchoolYears() {
    return schoolYears;
  }
  
  public boolean isReadOnly() {
    return isReadOnly;
  }
  
  public boolean isDenyTeachersEditStudentEvaluation() {
    return denyTeachersEditStudentEvaluation;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("hasParents", hasParents).add(
        "hasParentsOutgoingMail", hasParentsOutgoingMail).add("hasParentsAlfon", hasParentsAlfon).add(
        "hasParentsHistory", hasParentsHistory).add("hasParentsBehave", hasParentsBehave).add("hasParentsMaakav",
        hasParentsMaakav).add("hasParentsRegularGrades", hasParentsRegularGrades).add("hasParentsPeriodicGrades",
        hasParentsPeriodicGrades).add("denyStudentsJustificationRequests", denyStudentsJustificationRequests).add(
        "allowStudentsGroupPlans", allowStudentsGroupPlans).add("denyTeachersToSendEmails",
        denyTeachersToSendEmails).add("denyInviteBbbExternalParticipant", denyInviteBbbExternalParticipant).add(
        "forceFillLessonMethods", forceFillLessonMethods).add("offerToDuplicateAllLessons",
        offerToDuplicateAllLessons).add("maxGrade", maxGrade).add("maxPeriodGrade", maxPeriodGrade).add(
        "hasBagrutGrades", hasBagrutGrades).add("hasNikud", hasNikud).add("schoolSite", schoolSite).add(
        "moodleSite", moodleSite).add("iscoolSite", iscoolSite).add("schoolName", schoolName).add(
        "schoolYears", schoolYears).add("isReadOnly", isReadOnly).add("denyTeachersEditStudentEvaluation"
        , denyTeachersEditStudentEvaluation).toString();
  }
}
