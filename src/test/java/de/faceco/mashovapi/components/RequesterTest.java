package de.faceco.mashovapi.components;

import java.io.IOException;
import java.util.Arrays;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequesterTest {
  @Test
  public void getSchoolTest() {
    String kyhs = "{\"semel\":580019,\"name\":\"הכפר הירוק ע\\\"ש לוי אשכול\",\"years\":[2008," +
        "2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020]}";
    assertEquals(Requester.jsonToSchool(kyhs).toString(), "School{id=580019, name=הכפר הירוק ע\"ש" +
        " לוי " +
        "אשכול, years=[2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, " +
        "2020]}");
  
    School ky = null;
    
    try {
      School[] schools = Requester.getSchools();
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
  
    assertEquals(2020, Requester.jsonToSchool(kyhs).getCurrentYear());
  }
  
  @Test
  public void loginTest() {
    String kyhs = "{\"semel\":580019,\"name\":\"הכפר הירוק ע\\\"ש לוי אשכול\",\"years\":[2008," +
        "2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020]}";
    School ky = Requester.jsonToSchool(kyhs);
    String user = "325693455";
    String pass = "Mashov2020";
    try {
      System.out.println(new Gson().toJson(new Login(ky, 2020, user, pass)));
      System.out.println(Requester.login(ky, 2020, user, pass));
      System.out.println();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
}