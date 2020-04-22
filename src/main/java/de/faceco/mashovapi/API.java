package de.faceco.mashovapi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import de.faceco.mashovapi.components.*;

public class API {
  private School school;
  private String uid; // Unique User ID gave by the Mashov interface
  
  private static API singleton;
  
  public static API getInstance() {
    if (singleton == null) {
      singleton = new API();
    }
    return singleton;
  }
  
  private API() {
  
  }
  
  public void setSchool(School school) {
    this.school = school;
  }
  
  public School getSchool() {
    return school;
  }
  
  /**
   * Tries to fetch a school with a given ID.
   * @param id The school ID, as specified by the Ministry of Education.
   * @throws IOException If an IO exception occurs
   */
  public void fetchSchool(int id) throws IOException {
    School[] schools = RequestController.getSchools();
    for (School s: schools) {
      if (s.getId() == id) {
        school = s;
        return;
      }
    }
    throw new RuntimeException("School with ID " + id + " not found.");
  }
  
  public School[] allSchools() throws IOException {
    return RequestController.getSchools();
  }
  
  public LoginInfo login(int year, String user, String pass) throws IOException {
    LoginInfo li = RequestController.login(school, year, user, pass);
    
    this.uid = li.getCredential().getUserId();
    
    return li;
  }
  
  public Grade[] getGrades() throws IOException {
    return RequestController.grades(uid);
  }
  
  public Birthday getBirthday() throws IOException {
    return RequestController.birthday(uid);
  }
  
  public Group[] getGroups() throws IOException {
    return RequestController.groups(uid);
  }
  
  public Contact[] getClassMembers() throws IOException {
    return RequestController.classContacts(uid);
  }
  
  public Contact[] getGroupMembers(Group g) throws IOException {
    return RequestController.groupContacts(g.getGroupId());
  }
  
  public Period[] getPeriods() throws IOException {
    return RequestController.bells();
  }
  
  public int logout() throws IOException {
    return RequestController.logout();
  }
  
  public File getPicture() throws IOException {
    File pic = new File("picture.jpg");
    Files.write(pic.toPath(), RequestController.picture(uid));
    return pic;
  }
}
