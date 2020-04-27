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
  
  /**
   * Returns the API single instance.
   *
   * @return The MashovAPI
   */
  public static API getInstance() {
    if (singleton == null) {
      singleton = new API();
    }
    
    return singleton;
  }
  
  private API() {
    mailPage = 20;
  }
  
  /**
   * Sets the school to a school chosen from an array
   *
   * @param school The school reference
   */
  public void setSchool(School school) {
    this.school = school;
  }
  
  /**
   * @return The API's currently selected school
   */
  public School getSchool() {
    return school;
  }
  
  /**
   * Sets the school reference to null.
   */
  public void eraseSchool() {
    school = null;
  }
  
  /**
   * @return The amount of mail conversations per page.
   */
  public int getMailPage() {
    return mailPage;
  }
  
  /**
   * Sets the amount of mail conversations per page.
   *
   * @param mailPage The amount of mail per page.
   */
  public void setMailPage(int mailPage) {
    this.mailPage = mailPage;
  }
  
  /**
   * Tries to fetch a school with a given ID.
   * <p>If the school is found, the school will be auto-saved in the API instance, although it
   * can be changed using the {@link #setSchool(School)} method.</p>
   *
   * @param id The school ID, as specified by the Ministry of Education.
   * @return The school with the specified ID.
   * @throws IOException              If an IO exception occurs.
   * @throws IllegalArgumentException If the school is not found in the database.
   */
  public School fetchSchool(int id) throws IOException, IllegalArgumentException {
    School[] schools = allSchools();
    
    for (School s : schools) {
      if (s.getId() == id) {
        school = s;
        return s;
      }
    }
    throw new IllegalArgumentException("School with ID " + id + " not found.");
  }
  
  /**
   * Tries to fetch the list of the entire school list.
   *
   * @return A sorted array containing all of the schools.
   * @throws IOException If an error occurs.
   */
  public School[] allSchools() throws IOException {
    School[] schools = RequestController.schools();
    Arrays.sort(schools);
    return schools;
  }
  
  /**
   * Attempts to login to the Mashov system, using the selected school from {@link #fetchSchool(int)} or {@link #setSchool(School)}.
   *
   * <p>Returns a LoginResponse instance, which can be either a LoginInfo or a LoginFailed object. If the response is an
   * instance of LoginInfo, the user ID will be automatically set in the API.</p>
   *
   * @param year The year of login
   * @param user The username
   * @param pass The password
   * @return A <code>LoginResponse</code> object.
   * @throws IOException If an I/O error occurs.
   */
  public LoginResponse login(int year, String user, String pass) throws IOException {
    LoginResponse lr = RequestController.login(school, year, user, pass);
    if (lr instanceof LoginInfo) {
      LoginInfo li = (LoginInfo) lr;
      this.uid = li.getCredential().getUserId();
    }
    return lr;
  }
  
  /**
   * Attempts to fetch the list of grades from the API.
   *
   * @return An array of Grade objects.
   * @throws IOException If anything goes wrong.
   */
  public Grade[] getGrades() throws IOException {
    return RequestController.grades(uid);
  }
  
  /**
   * Attempts to fetch the birth date of the user.
   *
   * @return A Birthday object.
   * @throws IOException If anything goes wrong.
   */
  public Birthday getBirthday() throws IOException {
    return RequestController.birthday(uid);
  }
  
  /**
   * Attempts to fetch a list of all the groups a user belongs to. A Group is a group of students learning the same
   * subject together.
   *
   * @return An array of Group objects.
   * @throws IOException In case of an emergency.
   */
  public Group[] getGroups() throws IOException {
    return RequestController.groups(uid);
  }
  
  /**
   * Attempts to fetch the student's class members.
   *
   * @return An array of Contact objects.
   * @throws IOException If someone at the Mashov HQ made a mistake.
   */
  public Contact[] getClassMembers() throws IOException {
    return RequestController.classContacts(uid);
  }
  
  /**
   * Attempts to fetch the students in a group.
   *
   * @param g The group to look in.
   * @return An array of Contact objects.
   * @throws IOException If the servers won't cooperate.
   * @see #getGroupMembers(int)
   */
  public Contact[] getGroupMembers(Group g) throws IOException {
    return getGroupMembers(g.getGroupId());
  }
  
  /**
   * Attempts to fetch the students in a group.
   *
   * @param guid The ID of the group to look in.
   * @return An array of Contact objects.
   * @throws IOException If the HTTP gods aren't in your favor.
   * @see #getGroupMembers(Group)
   */
  public Contact[] getGroupMembers(int guid) throws IOException {
    return RequestController.groupContacts(guid);
  }
  
  /**
   * Attempts to fetch the periods a user has. A period is a time slot a lesson can be allocated to.
   *
   * @return A sorted array of Period objects.
   * @throws IOException If Mashov is doing some system update.
   */
  public Period[] getPeriods() throws IOException {
    Period[] bells = RequestController.bells();
    Arrays.sort(bells);
    return bells;
  }
  
  /**
   * Attempts to fetch the time table of a user.
   * A Lesson object consists of a TimeTable object noting the time and place of the lesson, and a Group object noting
   * the group of the lesson.
   *
   * @return An array of Lesson objects, sorted by day and lesson (Sunday:1, Sunday:2, ..., Monday:1, ...)
   * @throws IOException If the gerbil powering the entire system has stopped for a break.
   */
  public Lesson[] getTimetable() throws IOException {
    Lesson[] timetable = RequestController.timetable(uid);
    Arrays.sort(timetable);
    return timetable;
  }
  
  /**
   * Attempts to fetch the nth page of the user's inbox.
   *
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
   *
   * @param c The Conversation to look in.
   * @return A Conversation element with all the required detailed.
   * @throws IOException In case of an I/O error.
   */
  public Conversation getMessages(Conversation c) throws IOException {
    return RequestController.singleCon(c.getConversationId());
  }
  
  /**
   * Attempts to fetch the list of behave events. A behave event can be an absent student, a class disruption,
   * a good note etc.
   * @return An array of Behave objects.
   * @throws IOException If the servers are not behaving.
   */
  public Behave[] getBehaves() throws IOException {
    return RequestController.behaves(uid);
  }
  
  /**
   * Attempts to log out from Mashov.
   *
   * @return The status code of the logout GET request, should be 200 if OK.
   * @throws IOException In case of an I/O exception.
   */
  public int logout() throws IOException {
    return RequestController.logout();
  }
  
  /**
   * Asks the server for the saved picture of a user. Saves the picture in a File, and returns the file.
   *
   * @return The {@link File} object encoding the user JPG picture.
   * @throws IOException In case of an I/O exception or an unauthorized request.
   * @see #getPictureAsBytes()
   */
  public File getPicture() throws IOException {
    File pic = new File("picture.jpg");
    Files.write(pic.toPath(), getPictureAsBytes());
    return pic;
  }
  
  /**
   * Attempts to fetch the user's picture from the server, and returns the result as a byte array.
   *
   * @return The user's profile picture encoding, in JPG format.
   * @throws IOException In case of an I/O exception or an unauthorized request.
   * @see #getPicture()
   */
  public byte[] getPictureAsBytes() throws IOException {
    return RequestController.picture(uid);
  }
}
