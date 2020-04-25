package de.faceco.mashovapi.components;

import java.io.IOException;

import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestControllerTest {
  @Test
  public void getSchoolTest() {
    String kyhs = "{\"semel\":580019,\"name\":\"הכפר הירוק ע\\\"ש לוי אשכול\",\"years\":[2008," +
        "2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020]}";
    assertEquals(RequestController.jsonToSchool(kyhs).toString(), "School{id=580019, name=הכפר הירוק ע\"ש" +
        " לוי " +
        "אשכול, years=[2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, " +
        "2020]}");
  
    School ky = null;
    
    try {
      School[] schools = RequestController.schools();
      System.out.println(schools.length);
      //System.out.println(Arrays.toString(schools));
      for (School s : schools) {
        if (s.getId() == 580019) {
          ky = s;
          break;
        }
      }
      assertNotNull(ky);
      assertEquals(ky.toString(), "School{id=580019, name=הכפר הירוק ע\"ש לוי " +
          "אשכול, years=[2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, " +
          "2020]}");
  
      System.out.println(new Gson().toJson(ky));
    } catch (IOException e) {
      e.printStackTrace();
    }
  
    assertEquals(2020, RequestController.jsonToSchool(kyhs).getCurrentYear());
  }
  
  @Test
  public void loginTest() {
    String kyhs = "{\"semel\":580019,\"name\":\"הכפר הירוק ע\\\"ש לוי אשכול\",\"years\":[2008," +
        "2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020]}";
    School ky = RequestController.jsonToSchool(kyhs);
    String user = "325693455";
    String pass = "Mashov2020";
    try {
      System.out.println(new Gson().toJson(new Login(ky, 2020, user, pass)));
      System.out.println(RequestController.login(ky, 2020, user, pass));
      System.out.println();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
}