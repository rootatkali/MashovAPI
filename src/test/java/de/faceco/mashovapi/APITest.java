package de.faceco.mashovapi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import org.jetbrains.annotations.TestOnly;
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
  public void recipients() throws IOException {
    System.out.println(Arrays.toString(api.getMailRecipients()));
  }
  
  @Test
  public void sendMessageReply() throws IOException {
//    Conversation c = api.getInbox()[0];
//    System.out.println(c);
//    SendMessage msg = SendMessage.from(c)
//        .body("<p>aaaaaaa</p>");
//    System.out.println(new Gson().toJson(msg));
  }
  
  @Test
  public void gradesNull() throws IOException {
    Grade[] grades = api.getGrades();
    List<Grade> nulls = new ArrayList<>();
    for (Grade g : grades) {
      if (g.getGrade() == null) {
        nulls.add(g);
      }
    }
    assertTrue(nulls.size() > 0);
    System.out.println(nulls);
  }
  
  @Test
  public void upload() throws IOException {
    File f = new File("/home/rotem/Documents/out.pdf");
    SendMessage test = SendMessage.asNew()
        .attach(f)
        .attach("/home/rotem/bitmap.png");
    System.out.println(test);
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
    
    assertEquals(100, grades[grades.length - 1].getGrade().intValue());
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
  public void timetable() throws IOException {
    assertTrue(api.getTimetable().length > 0);
  }
  
  @Test
  public void behaves() throws IOException {
    Behave[] behaves = api.getBehaves();
    assertTrue(behaves.length > 0);
    System.out.println(Arrays.toString(behaves));
  }
  
  @Test
  public void inbox() throws IOException {
    Conversation[] c = api.getInbox();
    System.out.println(c.length);
    System.out.println(c[0].getMessages()[0].getBody());
  }
  
  @After
  public void after() throws IOException {
    assertEquals(200, api.logout());
  }
}
