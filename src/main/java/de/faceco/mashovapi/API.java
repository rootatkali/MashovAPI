package de.faceco.mashovapi;

import java.io.IOException;

import de.faceco.mashovapi.components.Grade;
import de.faceco.mashovapi.components.LoginInfo;
import de.faceco.mashovapi.components.Requester;
import de.faceco.mashovapi.components.School;

public class API {
  private School school;
  private String userId; // Unique User ID gave by the Mashov interface
  
  private static API singleton;
  
  public static API getInstance() {
    if (singleton == null) {
      singleton = new API();
    }
    return singleton;
  }
  
  private API() {
  
  }
  
  public School getSchool() {
    return school;
  }
  
  /**
   * Tries to fetch a school with a given ID.
   * @param id The school ID, as specified by the Ministry of Education.
   * @throws IOException
   */
  public void fetchSchool(int id) throws IOException {
    School[] schools = Requester.getSchools();
    for (School s: schools) {
      if (s.getId() == id) {
        school = s;
        return;
      }
    }
    throw new RuntimeException("School with ID " + id + " not found.");
  }
  
  public LoginInfo login(int year, String user, String pass) throws IOException {
    LoginInfo li = Requester.login(school, year, user, pass);
    
    this.userId = li.getCredential().getUserId();
    
    return li;
  }
  
  public Grade[] getGrades() throws IOException {
    return Requester.grades(userId);
  }
  
  public String getBirthday() throws IOException {
    return Requester.birthday(userId);
  }
}
