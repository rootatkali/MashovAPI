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
    api.fetchSchool(580019);
    assertNotNull(api.getSchool());
    LoginInfo li = api.login(2020, "325693455", System.getenv("MASHOV_PASSWD"));
    Group[] groups = api.getGroups();
    
    Group math = null;
    for (Group g : groups) {
      if (g.getGroupTeachers()[0].getTeacherName().equals("ליבנר ברוס")) {
        math = g;
        break;
      }
    }
    assertNotNull(math);
    assertNotNull(api.getGroupMembers(math));
  
    assertEquals(200, api.logout());
  }
}
