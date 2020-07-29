package de.faceco.mashovapi;

import de.faceco.mashovapi.components.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Objects;

/**
 * The main class of the MashovAPI library. All requests to the Mashov servers should be handled from here, and not from
 * {@link RequestController}. The only exception is the {@link SendMessage} class.
 *
 * <p>The API can be initialized or called from the following code:</p>
 * <pre>
 * API api = API.getInstance();
 * </pre>
 * <p>After initializing the API, you should set the API school, using either of the following methods:</p>
 * <pre>
 *   School[] schools = api.allSchools();
 *   api.setSchool(...);
 *
 *   // OR
 *
 *   int schoolId;
 *   //...
 *   api.fetchSchool(schoolId);
 * </pre>
 */
public final class API {
  private School school;
  private String uid; // Unique User ID gave by the Mashov interface
  
  
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
    School[] schools = getAllSchools();
    
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
   * @deprecated Use {@link #getAllSchools()} instead
   */
  @Deprecated
  public School[] allSchools() throws IOException {
    return getAllSchools();
  }
  
  /**
   * Tries to fetch the list of the entire school list.
   *
   * @return A sorted array containing all of the schools.
   * @throws IOException If an error occurs.
   */
  public School[] getAllSchools() throws IOException {
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
  
  public String getStudentId() {
    return uid;
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
  
  public BagrutGrade[] getBagrutGrades() throws IOException {
    return RequestController.bagrutGrades(uid);
  }
  
  public BagrutTime[] getBagrutTimes() throws IOException {
    return RequestController.bagrutTimes(uid);
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
   * Generates a download URL for a mail attachment.
   * @param a The attachment to download.
   * @return A string representing a URL.
   */
  public String generateAttachmentDownloadLink(Attachment a) {
    return RequestController.BASE_URL +
        "/mail/messages/" +
        a.getOwnerGroup() +
        "/files/" +
        a.getFileId() +
        "/download/";
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
   * Attempts to complete the missing info about Conversations: recipients and message body.
   * @param c An array of Conversations.
   * @throws IOException If there was an error.
   */
  private void completeConversations(Conversation[] c) throws IOException {
    Objects.requireNonNull(c);
    for (int i = 0; i < c.length; i++) {
      c[i] = RequestController.singleCon(c[i].getConversationId());
    }
  }
  
  public Recipient[] getMailRecipients() throws IOException {
    return RequestController.recipients();
  }
  
  /**
   * Attempts to fetch the first page of the user's inbox.
   *
   * @return An array of Conversations.
   * @throws IOException In case of an I/O error.
   * @see #getInbox(int)
   */
  public Conversation[] getInbox() throws IOException {
    Conversation[] inbox = RequestController.inbox();
    completeConversations(inbox);
    return inbox;
  }
  
  /**
   * Attempts to fetch the nth page of the user's inbox.
   *
   * @param page The page in the inbox. First page is 0, and each page contains 20 conversations.
   * @return An array of Conversations.
   * @throws IOException In case of an I/O error.
   * @deprecated Now returns all conversations. Use {@link #getInbox()} instead.
   */
  @Deprecated
  public Conversation[] getInbox(int page) throws IOException {
    Conversation[] inbox = RequestController.inbox();
    completeConversations(inbox);
    return inbox;
  }
  
  public Conversation[] getUnreadMail() throws IOException {
    return RequestController.unread();
  }
  
  @Deprecated
  public Conversation[] getUnreadMail(int page) throws IOException {
    return RequestController.unread();
  }
  
  /**
   * Attempts to fetch the missing details about a Conversation - messages and recipients.
   *
   * @deprecated Feature incorporated into {@link #getInbox(int)}
   * @param c The Conversation to look in.
   * @return A Conversation element with all the required detailed.
   * @throws IOException In case of an I/O error.
   */
  @Deprecated
  public Conversation getMessages(Conversation c) throws IOException {
    return RequestController.singleCon(c.getConversationId());
  }
  
  /**
   * Attempts to fetch the list of behave events. A behave event can be an absent student, a class disruption,
   * a good note etc.
   *
   * @return An array of Behave objects.
   * @throws IOException If the servers are not behaving.
   */
  public Behave[] getBehaves() throws IOException {
    return RequestController.behaves(uid);
  }
  
  public MoodleInfo getMoodleInfo() throws IOException {
    return RequestController.moodleInfo();
  }
  
  public MoodleAssignment[] getMoodleAssignments() throws IOException {
    return RequestController.moodleAssignments(uid);
  }
  
  /**
   * Attempts to log out from Mashov.
   *
   * @return The status code of the logout GET request, should be 200 if OK.
   * @throws IOException In case of an I/O exception.
   */
  public int logout() throws IOException {
    int logout = 0;
    try {
      logout = RequestController.logout();
      uid = null;
    } catch (IOException e) {
      throw e;
    }
    return logout;
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
