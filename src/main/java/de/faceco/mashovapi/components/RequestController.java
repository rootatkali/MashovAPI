package de.faceco.mashovapi.components;

import com.google.gson.Gson;
import okhttp3.*;
import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class RequestController {
  /**
   * This is the base URL of the Mashov API
   */
  public static final String BASE_URL = "https://web.mashov.info/api";
  private static final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) " +
      "Chrome/81.0.4044.122 Safari/537.36";
  private static final Gson gson = new Gson();
  private static final OkHttpClient http
      = new OkHttpClient.Builder()
      .build();
  private static String csrfToken = null;
  private static final Map<String, String> cookies = new HashMap<>();
  
  public static School[] schools() throws IOException {
    Request request = new Request.Builder()
        .url(BASE_URL + "/schools")
        .method("GET", null)
        .addHeader("User-Agent", USER_AGENT)
        .build();
    Response response = http.newCall(request).execute();
    
    return gson.fromJson(response.body().string(), School[].class);
  }
  
  public static LoginResponse login(School s, int year, String user, String pass) throws IOException {
    Login l = new Login(s, year, user, pass);
    
    MediaType json = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(gson.toJson(l), json);
    
    Request request = new Request.Builder()
        .url(BASE_URL + "/login")
        .method("POST", body)
        .addHeader("User-Agent", USER_AGENT)
        .addHeader("Content-Type", "application/json")
        .build();
    Response response = http.newCall(request).execute();
    
    int code = response.code();
    if (code != 200) {
      LoginFailed fail = gson.fromJson(response.body().string(), LoginFailed.class);
      fail.setErrorCode(code);
      return fail;
    }
    
    cookies.clear();
    csrfToken = null;
    
    Headers headers = response.headers();
    csrfToken = Cookie.parse(request.url(), headers.get("Set-Cookie")).value();
    List<String> rawCookies = headers.values("Set-Cookie");
    
    for (String c : rawCookies) {
      String[] split = c.trim().split("=", 2);
      cookies.put(split[0], split[1].substring(0, split[1].indexOf(";")));
    }
    
    return gson.fromJson(response.body().string(), LoginInfo.class);
  }
  
  /**
   * Generates a cookie HTTP request header from the cookies entered at login;
   *
   * @return a <code>Cookie</code> header with the required cookies.
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
  
  /**
   * Attempts to perform a GET request to the API, based on the requested path.
   *
   * <p>The request has the following headers:
   * User-Agent, Cookie, x-csrf-token</p>
   * @param path The path to the API contents.
   * @return A {@link Response} corresponding to the API server's response.
   * @throws IOException If anything goes wrong.
   */
  private static Response apiGet(String path) throws IOException {
    Request request = new Request.Builder()
        .url(BASE_URL + path)
        .method("GET", null)
        .addHeader("User-Agent", USER_AGENT)
        .addHeader("x-csrf-token", csrfToken)
        .addHeader("Cookie", cookieHeader())
        .build();
    return http.newCall(request).execute();
  }
  
  public static Grade[] grades(String uid) throws IOException {
    Response response = apiGet("/students/" + uid + "/grades");
    return gson.fromJson(response.body().string(), Grade[].class);
  }
  
  public static BagrutGrade[] bagrutGrades(String uid) throws IOException {
    Response response = apiGet("/students/" + uid + "/bagrut/grades");
    return gson.fromJson(response.body().string(), BagrutGrade[].class);
  }
  
  public static BagrutTime[] bagrutTimes(String uid) throws IOException {
    Response response = apiGet("/students/" + uid + "/bagrut/sheelonim");
    return gson.fromJson(response.body().string(), BagrutTime[].class);
  }
  
  public static Birthday birthday(String uid) throws IOException {
    Response response = apiGet("/user/" + uid + "/birthday");
    return gson.fromJson(response.body().string(), Birthday.class);
  }
  
  public static Group[] groups(String uid) throws IOException {
    Response response = apiGet("/students/" + uid + "/groups");
    return gson.fromJson(response.body().string(), Group[].class);
  }
  
  public static Period[] bells() throws IOException {
    Response response = apiGet("/bells");
    return gson.fromJson(response.body().string(), Period[].class);
  }
  
  public static Lesson[] timetable(String uid) throws IOException {
    Response response = apiGet("/students/" + uid + "/timetable");
    return gson.fromJson(response.body().string(), Lesson[].class);
  }
  
  /**
   * Asks the server for the saved picture of a user.
   *
   * @param uid The Mashov UUID of the user.
   * @return An array of bytes corresponding to the user picture in JPG format.
   * @throws IOException In case of an I/O server exception or an unauthorized request.
   */
  public static byte[] picture(String uid) throws IOException {
    Response response = apiGet("/user/" + uid + "/picture");
    return response.body().bytes();
  }
  
  public static Recipient[] recipients() throws IOException {
    Response response = apiGet("/mail/recipients");
    return gson.fromJson(response.body().string(), Recipient[].class);
  }
  
  static String msgIdReply(Conversation c) throws IOException {
    MediaType json = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(gson.toJson(new SendMessage(c).body("")), json);
    
    Request request = new Request.Builder()
        .url(BASE_URL + "/mail/conversations/" + c.getConversationId() + "/draft")
        .put(body)
        .addHeader("User-Agent", USER_AGENT)
        .addHeader("x-csrf-token", csrfToken)
        .addHeader("Cookie", cookieHeader())
        .build();
    Response response = http.newCall(request).execute();
    Message msg = gson.fromJson(response.body().string(), Message.class);
    return msg.getMessageId();
  }
  
  static Message msgNew() throws IOException {
    String blankMsg = "{\"isNew\":true,\"isDeleted\":false,\"body\":\"\"}";
    MediaType json = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(blankMsg, json);
    
    Request request = new Request.Builder()
        .url(BASE_URL + "/mail/conversations/draft")
        .put(body)
        .addHeader("User-Agent", USER_AGENT)
        .addHeader("x-csrf-token", csrfToken)
        .addHeader("Cookie", cookieHeader())
        .build();
    Response response = http.newCall(request).execute();
    return gson.fromJson(response.body().string(), Message.class);
  }
  
  public static Message send(SendMessage s) throws IOException {
    String msg = gson.toJson(s);
  
    MediaType json = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(msg, json);
    
    Request request = new Request.Builder()
        .url(BASE_URL + "/mail/messages/" + s.getMessageId())
        .post(body)
        .addHeader("User-Agent", USER_AGENT)
        .addHeader("x-csrf-token", csrfToken)
        .addHeader("Cookie", cookieHeader())
        .build();
    Response response = http.newCall(request).execute();
    return gson.fromJson(response.body().string(), Message.class);
  }
  
  public static Conversation[] inbox() throws IOException {
    Response response = apiGet("/mail/inbox/conversations");
    return gson.fromJson(response.body().string(), Conversation[].class);
  }
  
  public static Conversation[] unread() throws IOException {
    Response response = apiGet("/mail/unread/conversations");
    return gson.fromJson(response.body().string(), Conversation[].class);
  }
  
  public static Conversation singleCon(String cid) throws IOException {
    Response response = apiGet("/mail/conversations/" + cid);
    return gson.fromJson(response.body().string(), Conversation.class);
  }
  
  public static Behave[] behaves(String uid) throws IOException {
    Response response = apiGet("/students/" + uid + "/behave");
    return gson.fromJson(response.body().string(), Behave[].class);
  }
  
  public static Attachment file(SendMessage msg, File file) throws IOException {
    if (!file.exists()) throw new IOException("File does not exist.");
    if (!file.isFile()) throw new IOException("Please select a file.");
    
    String mime = new Tika().detect(file); // Detect MIME type from file using Apache Tika
    MediaType mediaType = MediaType.parse(mime);
    
    RequestBody body = new MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart("fileUpload", file.getName(), RequestBody.create(file, mediaType))
        .build();
    
    Request request = new Request.Builder()
        .url(BASE_URL + "/mail/messages/" + msg.getMessageId() + "/files")
        .post(body)
        .addHeader("User-Agent", USER_AGENT)
        .addHeader("x-csrf-token", csrfToken)
        .addHeader("Cookie", cookieHeader())
        .build();
    
    Response response = http.newCall(request).execute();
    
    return gson.fromJson(response.body().string(), Attachment[].class)[0];
  }
  
  public static Attachment file(SendMessage msg, String path) throws IOException {
    return file(msg, new File(path));
  }
  
  /**
   * Attempts to send a logout request to Mashov.
   *
   * @return The status code of the logout GET request, should be 200 if OK.
   * @throws IOException In case of an I/O exception.
   */
  public static int logout() throws IOException {
    Response response = apiGet("/logout");
    return response.code();
  }
  
  public static School jsonToSchool(String json) {
    return gson.fromJson(json, School.class);
  }
}
