package de.faceco.mashovapi;

import java.io.IOException;

import jdk.nashorn.internal.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;

import de.faceco.mashovapi.components.Grade;

import static org.junit.Assert.*;

public class APITest {
  private API api;
  
  @Before
  public void before() {
    api = new API();
  }
  
  @Test
  public void login() throws IOException {
    api.fetchSchool(580019);
    assertNotNull(api.getSchool());
    api.login(2020, "325693455", System.getenv("MASHOV_PASSWD"));
    Grade[] grades = api.getGrades();
    assertTrue(grades.length == 36); // Amount of grades
    assertTrue(grades[0].getGrade() == 100);
  
    System.out.println(api.getBirthday());
  }
}
