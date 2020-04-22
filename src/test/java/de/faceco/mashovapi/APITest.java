package de.faceco.mashovapi;

import java.io.IOException;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.faceco.mashovapi.components.Group;
import de.faceco.mashovapi.components.LoginInfo;

import static org.junit.Assert.*;

public class APITest {
  private API api;
  private LoginInfo li;
  
  @Before
  public void before() throws IOException {
    api = API.getInstance();
  
    api.fetchSchool(Secret.SCHOOL_ID);
    assertNotNull(api.getSchool());
    li = api.login(2020, Secret.MASHOV_USER, Secret.MASHOV_PASSWD);
    // Secret class = private information
  }
  
  @Test
  public void timetableRequest() throws IOException {
    assertEquals(Secret.LESSON_COUNT, api.getTimetable().length);
  }
  
  @After
  public void after() throws IOException {
    assertEquals(200, api.logout());
  }
}
