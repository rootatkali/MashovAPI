package de.faceco.mashovapi.components;

import java.io.IOException;
import java.util.*;

import com.google.gson.Gson;
import okhttp3.*;

public class Requester {
  /**
   * This is the base URL of the Mashov API
   */
  public static final String BASE_URL = "https://web.mashov.info/api";
  public static final String USER_AGENT = "Mozilla/5.0";
  public static final int TIMEOUT = 10000;
  private static Gson gson = new Gson();
  private static OkHttpClient http = new OkHttpClient().newBuilder().build();
  private static String csrfToken;
  private static Map<String, String> cookies = new HashMap<>();
  
  public static School[] getSchools() throws IOException {
    School[] ret = null;
    
    Request request = new Request.Builder()
        .url(BASE_URL + "/schools")
        .method("GET", null)
        .build();
    
    Response response = http.newCall(request).execute();
    
    String content = response.body().string();
    
    ret = gson.fromJson(content, School[].class);
    Arrays.sort(ret); // Sorts schools by id from smallest to largest
    
    return ret;
  }
  
  public static LoginInfo login(School s, int year, String user, String pass) throws IOException {
    Login l = new Login(s, year, user, pass);
    MediaType json = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(gson.toJson(l), json);
    Request request = new Request.Builder()
        .url(BASE_URL + "/login/")
        .method("POST", body)
        .addHeader("Content-Type", "application/json")
        .build();
    Response response = http.newCall(request).execute();
    Headers headers = response.headers();
    csrfToken = Cookie.parse(request.url() ,headers.get("Set-Cookie")).value();
    List<String> rawCookies = headers.values("Set-Cookie");
    for (String c : rawCookies) {
      String[] split = c.trim().split("=", 2);
      cookies.put(split[0], split[1].substring(0, split[1].indexOf(";")));
    }
    return gson.fromJson(response.body().string(), LoginInfo.class);
  }
  
  /**
   * A way to use cookies
   * @return
   */
  private static String cookieHeader() {
    StringBuilder sb = new StringBuilder();
    
    for (Map.Entry<String, String> e : cookies.entrySet()) { // Loop for cookies from login
      sb.append(
          String.format("%s=%s; ", e.getKey(), e.getValue())
      );
    }
    String cookieHeader = sb.toString();
  
    if (cookieHeader.lastIndexOf(";") == cookieHeader.length() - 1) {
      cookieHeader = cookieHeader.substring(0, cookieHeader.length() - 1);
    }
    return cookieHeader;
  }
  
  public static Grade[] grades(String uid) throws IOException {
    Request request = new Request.Builder()
        .url(BASE_URL + "/students/" + uid + "/grades")
        .method("GET", null)
        .addHeader("x-csrf-token", csrfToken)
        .addHeader("Cookie", cookieHeader())
        .build();
    Response response = http.newCall(request).execute();
    return gson.fromJson(response.body().string(), Grade[].class);
  }
  
  // TODO: Get birthday
  public static Birthday birthday(String uid) throws IOException {
    Request request = new Request.Builder()
        .url(BASE_URL + "/user/" + uid + "/birthday")
        .method("GET", null)
        .addHeader("x-csrf-token", csrfToken)
        .addHeader("Cookie", cookieHeader())
        .build();
    Response response = http.newCall(request).execute();
    return gson.fromJson(response.body().string(), Birthday.class);
  }
  
  // TODO: Get teachers
  
  // TODO: Get timetable
  
  // TODO: Get picture
  
  // TODO: Get mail
  
  // TODO: Get lessons
  
  // TODO: Get behaves
  
  public static School jsonToSchool(String json) {
    return gson.fromJson(json, School.class);
  }
}
