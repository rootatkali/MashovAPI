package de.faceco.mashovapi;

import java.io.IOException;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.faceco.mashovapi.components.*;

import static org.junit.Assert.*;

import static de.faceco.mashovapi.Secret.*;

public class APITest {
  private API api;
  private LoginInfo li;
  
  @Before
  public void before() throws IOException {
    api = API.getInstance();
  
    api.fetchSchool(SCHOOL_ID);
    assertNotNull(api.getSchool());
    LoginResponse lr = api.login(2020, MASHOV_USER, MASHOV_PASSWD);
    assertTrue(lr instanceof LoginInfo);
    li = (LoginInfo) lr;
    // Secret class = private information
    assertNotNull(li);
  }
  
  @Test
  public void loginInfo() {
    assertEquals(li.getCredential().getIdNumber(), ID_NUM);
    assertEquals(li.getAccessToken().getSchoolOptions().getMoodleSite(), MOODLE_SITE);
    assertTrue(li.getAccessToken().getUserOptions().hasEmailNotifications());
    assertEquals(li.getCredential().getSchoolId(), api.getSchool().getId());
  }
  
  @Test
  public void grades() throws IOException {
    Grade[] grades = api.getGrades();
    assertNotNull(grades);
    Arrays.sort(grades); // Sort from lowest grade to highest
    
    assertEquals(100, grades[grades.length - 1].getGrade());
  }
  
  @Test
  public void groups() throws IOException {
    assertTrue(api.getGroups().length > 0);
  }
  
  @Test
  public void birthday() throws IOException {
    assertEquals(BDAY_YEAR, api.getBirthday().getYear());
  }
  
  @Test
  public void classMembers() throws IOException {
    Contact[] classMembers = api.getClassMembers();
    Arrays.sort(classMembers);
    assertEquals(FIRST_CLASSMATE, classMembers[0].getFamilyName());
  }
  
  @Test
  public void timetable() throws IOException {
    assertEquals(LESSON_COUNT, api.getTimetable().length);
  }
  
  @Test
  public void behaves() throws IOException {
    Behave[] behaves = api.getBehaves();
    assertTrue(behaves.length > 0);
    System.out.println(Arrays.toString(behaves));
  }
  
  @After
  public void after() throws IOException {
    assertEquals(200, api.logout());
  }
}
