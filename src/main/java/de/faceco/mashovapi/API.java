package de.faceco.mashovapi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import de.faceco.mashovapi.components.*;

/**
 * The main class of the MashovAPI library. All requests to the Mashov servers should be
 */
public class API {
  private School school;
  private String uid; // Unique User ID gave by the Mashov interface
  
  private int mailPage;
  
  private static API singleton;
  
  public static API getInstance() {
    if (singleton == null) {
      singleton = new API();
    }

    return singleton;
  }
  
  private API() {
    mailPage = 20;
  }
  
  public void setSchool(School school) {
    this.school = school;
  }
  
  public School getSchool() {
    return school;
  }
  
  public int getMailPage() {
    return mailPage;
  }
  
  public void setMailPage(int mailPage) {
    this.mailPage = mailPage;
  }
  
  /**
   * Tries to fetch a school with a given ID.
   * <p>If the school is found, the school will be auto-saved in the API instance, although it
   * can be changed using the {@link #setSchool(School)} method.</p>
   * @param id The school ID, as specified by the Ministry of Education.
   * @return The school with the specified ID.
   * @throws IOException If an IO exception occurs
   * @throws IllegalArgumentException If the school is not found in the database.
   */
  public School fetchSchool(int id) throws IOException, IllegalArgumentException {
    School[] schools = RequestController.schools();
    for (School s: schools) {
      if (s.getId() == id) {
        school = s;
        return s;
      }
    }
    throw new IllegalArgumentException("School with ID " + id + " not found.");
  }
  
  public School[] allSchools() throws IOException {
    School[] schools = RequestController.schools();
    Arrays.sort(schools);
    return schools;
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
  
  public Lesson[] getTimetable() throws IOException {
    Lesson[] timetable = RequestController.timetable(uid);
    Arrays.sort(timetable);
    return timetable;
  }
  
  /**
   * Attempts to fetch the nth page of the user's inbox.
   * @param page The page in the inbox. First page is 0, and each page contains 20 conversations
   *             (configurable through {@link #setMailPage(int)}.)
   * @return An array of Conversations, with no message bodies or lists of recipients.
   * @throws IOException In case of an I/O error.
   * @see #getMessages(Conversation)
   */
  public Conversation[] getInbox(int page) throws IOException {
    return RequestController.inbox(20 * page, page);
  }
  
  /**
   * Attempts to fetch the missing details about a Conversation - messages and recipients.
   * @param c The Conversation to look in.
   * @return A Conversation element with all the required detailed.
   * @throws IOException In case of an I/O error.
   */
  public Conversation getMessages(Conversation c) throws IOException {
    return RequestController.singleCon(c.getConversationId());
  }
  
  /**
   * Attempts to log out from Mashov.
   * @return The status code of the logout GET request, should be 200 if OK.
   * @throws IOException In case of an I/O exception.
   */
  public int logout() throws IOException {
    return RequestController.logout();
  }
  
  /**
   * Asks the server for the saved picture of a user. Saves the picture in a File, and returns
   * the file.
   * @return The {@link File} object encoding the user JPG picture.
   * @throws IOException In case of an I/O exception or an unauthorized request.
   */
  public File getPicture() throws IOException {
    File pic = new File("picture.jpg");
    Files.write(pic.toPath(), RequestController.picture(uid));
    return pic;
  }
}
