package de.faceco.mashovapi;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import de.faceco.mashovapi.components.Group;
import de.faceco.mashovapi.components.LoginInfo;

import static org.junit.Assert.*;

public class APITest {
  private API api;
  
  @Before
  public void before() {
    api = API.getInstance();
  }
  
  @Test
  public void login() throws IOException {
    // Variables from the Secret class are private information
    api.fetchSchool(Secret.SCHOOL_ID);
    assertNotNull(api.getSchool());
    LoginInfo li = api.login(2020, Secret.MASHOV_USER, Secret.MASHOV_PASSWD);
    Group[] groups = api.getGroups();

    Group math = null;
    for (Group g : groups) {
      if (g.getGroupTeachers()[0].getTeacherName().equals(Secret.MATH_TEACHER)) {
        math = g;
        break;
      }
    }
    assertNotNull(math);
    assertNotNull(api.getGroupMembers(math));

    assertEquals(200, api.logout());
  }
}
