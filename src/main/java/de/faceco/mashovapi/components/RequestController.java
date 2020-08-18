package de.faceco.mashovapi.components;

import com.google.gson.Gson;
import de.faceco.mashovapi.API;
import okhttp3.*;
import org.apache.tika.Tika;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

@SuppressWarnings("ConstantConditions")
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
  private static final Map<String, String> cookies = new HashMap<>();
  private static String csrfToken = null;
  
  public static School[] schools() throws IOException {
    Request request = Requests.SCHOOLS;
    Response response = http.newCall(request).execute();
    
    return gson.fromJson(response.body().string(), School[].class);
  }
  
  private static <T> void async(Call call, Consumer<T> onResult, Runnable onFail, Class<T> type) throws IOException {
    CallbackFuture future = new CallbackFuture();
    call.enqueue(future);
    try {
      Response response = future.get();
      if (response.isSuccessful()) {
        T result = gson.fromJson(response.body().string(), type);
        onResult.accept(result);
      } else {
        onFail.run();
      }
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }
  
  private static <T> void async(Request request, Consumer<T> onResult, Runnable onFail, Class<T> type)
      throws IOException {
    async(http.newCall(request), onResult, onFail, type);
  }
  
  public static void schoolsAsync(Consumer<School[]> onResult, Runnable onFail) throws IOException {
    async(Requests.SCHOOLS, onResult, onFail, School[].class);
  }
  
  private static void handleCookies(Request request, Response response) {
    cookies.clear();
    csrfToken = null;
    
    Headers headers = response.headers();
    csrfToken = Cookie.parse(request.url(), headers.get("Set-Cookie")).value();
    List<String> rawCookies = headers.values("Set-Cookie");
    for (String c : rawCookies) {
      String[] split = c.trim().split("=", 2);
      cookies.put(split[0], split[1].substring(0, split[1].indexOf(";")));
    }
  }
  
  public static void loginAsync(Login l, Consumer<LoginInfo> onResult, Runnable onFail) throws IOException {
    CallbackFuture future = new CallbackFuture();
    Request request = Requests.login(l);
    http.newCall(request).enqueue(future);
    try {
      Response response = future.get();
      if (response.isSuccessful()) {
        handleCookies(request, response);
        LoginInfo li = gson.fromJson(response.body().string(), LoginInfo.class);
        API.getInstance().setUid(li.getCredential().getUserId());
        onResult.accept(li);
      } else {
        onFail.run();
      }
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }
  
  public static LoginResponse login(Login l) throws IOException {
    Request request = Requests.login(l);
    Response response = http.newCall(request).execute();
    
    int code = response.code();
    if (code != 200) {
      LoginFailed fail = gson.fromJson(response.body().string(), LoginFailed.class);
      fail.setErrorCode(code);
      return fail;
    }
    
    handleCookies(request, response);
    
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
   * Attempts to create a GET request to the API based on the requested path, and wrap it in a {@link Call}.
   *
   * <p>The request has the following headers:
   * User-Agent, Cookie, x-csrf-token</p>
   *
   * @param path The path to the API contents.
   * @return A {@link Response} corresponding to the API server's response.
   */
  private static Call apiCall(String path) {
    Request request = new Request.Builder()
        .url(BASE_URL + path)
        .method("GET", null)
        .addHeader("User-Agent", USER_AGENT)
        .addHeader("x-csrf-token", csrfToken)
        .addHeader("Cookie", cookieHeader())
        .build();
    return http.newCall(request);
  }
  
  public static Grade[] grades(String uid) throws IOException {
    Response response = apiCall("/students/" + uid + "/grades").execute();
    return gson.fromJson(response.body().string(), Grade[].class);
  }
  
  public static void gradesAsync(String uid, Consumer<Grade[]> onResult, Runnable onFail) throws IOException {
    async(apiCall("/students/" + uid + "/grades"), onResult, onFail, Grade[].class);
  }
  
  public static BagrutGrade[] bagrutGrades(String uid) throws IOException {
    Response response = apiCall("/students/" + uid + "/bagrut/grades").execute();
    return gson.fromJson(response.body().string(), BagrutGrade[].class);
  }
  
  public static void bagrutGradesAsync(String uid, Consumer<BagrutGrade[]> onResult, Runnable onFail)
      throws IOException {
    async(apiCall("/students/" + uid + "/bagrut/grades"), onResult, onFail, BagrutGrade[].class);
  }
  
  public static BagrutTime[] bagrutTimes(String uid) throws IOException {
    Response response = apiCall("/students/" + uid + "/bagrut/sheelonim").execute();
    return gson.fromJson(response.body().string(), BagrutTime[].class);
  }
  
  public static void bagrutTimesAsync(String uid, Consumer<BagrutTime[]> onResult, Runnable onFail) throws IOException {
    async(apiCall("/students/" + uid + "/bagrut/sheelonim"), onResult, onFail, BagrutTime[].class);
  }
  
  public static Birthday birthday(String uid) throws IOException {
    Response response = apiCall("/user/" + uid + "/birthday").execute();
    return gson.fromJson(response.body().string(), Birthday.class);
  }
  
  public static void birthdayAsync(String uid, Consumer<Birthday> onResult, Runnable onFail) throws IOException {
    async(apiCall("/user/" + uid + "/birthday"), onResult, onFail, Birthday.class);
  }
  
  public static Group[] groups(String uid) throws IOException {
    Response response = apiCall("/students/" + uid + "/groups").execute();
    return gson.fromJson(response.body().string(), Group[].class);
  }
  
  public static void groupsAsync(String uid, Consumer<Group[]> onResult, Runnable onFail) throws IOException {
    async(apiCall("/students/" + uid + "/groups"), onResult, onFail, Group[].class);
  }
  
  public static Period[] bells() throws IOException {
    Response response = apiCall("/bells").execute();
    return gson.fromJson(response.body().string(), Period[].class);
  }
  
  public static void bellsAsync(Consumer<Period[]> onResult, Runnable onFail) throws IOException {
    Consumer<Period[]> sortAndAccept = periods -> {
      Arrays.sort(periods);
      onResult.accept(periods);
    };
    async(apiCall("/bells"), sortAndAccept, onFail, Period[].class);
  }
  
  public static Lesson[] timetable(String uid) throws IOException {
    Response response = apiCall("/students/" + uid + "/timetable").execute();
    return gson.fromJson(response.body().string(), Lesson[].class);
  }
  
  public static void timetableAsync(String uid, Consumer<Lesson[]> onResult, Runnable onFail) throws IOException {
    async(apiCall("/students/" + uid + "/timetable"), onResult, onFail, Lesson[].class);
  }
  
  /**
   * Asks the server for the saved picture of a user.
   *
   * @param uid The Mashov UUID of the user.
   * @return An array of bytes corresponding to the user picture in JPG format.
   * @throws IOException In case of an I/O server exception or an unauthorized request.
   */
  public static byte[] picture(String uid) throws IOException {
    Response response = apiCall("/user/" + uid + "/picture").execute();
    return response.body().bytes();
  }
  
  public static void pictureAsync(String uid, Consumer<byte[]> onResult, Runnable onFail) throws IOException {
    CallbackFuture future = new CallbackFuture();
    apiCall("/user/" + uid + "/picture").enqueue(future);
    
    try {
      Response response = future.get();
      if (response.isSuccessful()) {
        onResult.accept(response.body().bytes());
      } else {
        onFail.run();
      }
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }
  
  public static Hatama[] hatamot(String uid) throws IOException {
    return gson.fromJson(apiCall("/students/" + uid + "/hatamot").execute().body().string(), Hatama[].class);
  }
  
  public static void hatamotAsync(String uid, Consumer<Hatama[]> onResult, Runnable onFail) throws IOException {
    async(apiCall("/students/" + uid + "/hatamot"), onResult, onFail, Hatama[].class);
  }
  
  public static StudyMaterial[] studyMaterials(String uid) throws IOException {
    return gson.fromJson(apiCall("/students/" + uid + "/files").execute().body().string(), StudyMaterial[].class);
  }
  
  public static void studyMaterialsAsync(String uid, Consumer<StudyMaterial[]> onResult, Runnable onFail)
      throws IOException {
    async(apiCall("/students/" + uid + "/files"), onResult, onFail, StudyMaterial[].class);
  }
  
  public static Announcement[] messageBoard(String uid) throws IOException {
    return gson.fromJson(apiCall("/students/" + uid + "/messageBoard").execute().body().string(),
        Announcement[].class);
  }
  
  public static void messageBoardAsync(String uid, Consumer<Announcement[]> onResult, Runnable onFail)
      throws IOException {
    async(apiCall("/students/" + uid + "/messageBoard"), onResult, onFail, Announcement[].class);
  }
  
  public static Homework[] homework(String uid) throws IOException {
    return gson.fromJson(apiCall("/students/" + uid + "/homework").execute().body().string(), Homework[].class);
  }
  
  public static void homeworkAsync(String uid, Consumer<Homework[]> onResult, Runnable onFail) throws IOException {
    async(apiCall("/students/" + uid + "/homework"), onResult, onFail, Homework[].class);
  }
  
  public static Recipient[] recipients() throws IOException {
    Response response = apiCall("/mail/recipients").execute();
    return gson.fromJson(response.body().string(), Recipient[].class);
  }
  
  public static void recipientsAsync(Consumer<Recipient[]> onResult, Runnable onFail) throws IOException {
    async(apiCall("/mail/recipients"), onResult, onFail, Recipient[].class);
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
    Response response = apiCall("/mail/inbox/conversations").execute();
    return gson.fromJson(response.body().string(), Conversation[].class);
  }
  
  public static void inboxAsync(Consumer<Conversation[]> onResult, Runnable onFail) throws IOException {
    async(apiCall("/mail/inbox/conversations"), onResult, onFail, Conversation[].class);
  }
  
  public static Conversation[] outbox() throws IOException {
    Response response = apiCall("/mail/sent/conversations").execute();
    return gson.fromJson(response.body().string(), Conversation[].class);
  }
  
  public static void outboxAsync(Consumer<Conversation[]> onResult, Runnable onFail) throws IOException {
    async(apiCall("/mail/sent/conversations"), onResult, onFail, Conversation[].class);
  }
  
  public static Conversation[] unread() throws IOException {
    Response response = apiCall("/mail/unread/conversations").execute();
    return gson.fromJson(response.body().string(), Conversation[].class);
  }
  
  public static void unreadAsync(Consumer<Conversation[]> onResult, Runnable onFail) throws IOException {
    async(apiCall("/mail/unread/conversations"), onResult, onFail, Conversation[].class);
  }
  
  public static Conversation singleCon(String cid) throws IOException {
    Response response = apiCall("/mail/conversations/" + cid).execute();
    return gson.fromJson(response.body().string(), Conversation.class);
  }
  
  public static void singleConAsync(String cid, Consumer<Conversation> onResult, Runnable onFail) throws IOException {
    async(apiCall("/mail/conversations/" + cid), onResult, onFail, Conversation.class);
  }
  
  public static Behave[] behaves(String uid) throws IOException {
    Response response = apiCall("/students/" + uid + "/behave").execute();
    return gson.fromJson(response.body().string(), Behave[].class);
  }
  
  public static void behavesAsync(String uid, Consumer<Behave[]> onResult, Runnable onFail) throws IOException {
    async(apiCall("/students/" + uid + "/behave"), onResult, onFail, Behave[].class);
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
  
  public static MoodleAssignment[] moodleAssignments(String uid) throws IOException {
    Response response = apiCall("/students/" + uid + "/moodle/assignments/grades").execute();
    return gson.fromJson(response.body().string(), MoodleAssignment[].class);
  }
  
  public static void moodleAssignmentsAsync(String uid, Consumer<MoodleAssignment[]> onResult, Runnable onFail)
      throws IOException {
    async(apiCall("/students/" + uid + "/moodle/assignments/grades"), onResult, onFail, MoodleAssignment[].class);
  }
  
  public static MoodleInfo moodleInfo() throws IOException {
    Response response = apiCall("/user/moodle").execute();
    return gson.fromJson(response.body().string(), MoodleInfo.class);
  }
  
  public static void moodleInfoAsync(Consumer<MoodleInfo> onResult, Runnable onFail) throws IOException {
    async(apiCall("/user/moodle"), onResult, onFail, MoodleInfo.class);
  }
  
  /**
   * Attempts to send a logout request to Mashov.
   *
   * @return The status code of the logout GET request, should be 200 if OK.
   * @throws IOException In case of an I/O exception.
   */
  public static int logout() throws IOException {
    Response response = apiCall("/logout").execute();
    cookies.clear();
    csrfToken = null;
    return response.code();
  }
  
  private static class CallbackFuture extends CompletableFuture<Response> implements Callback {
    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
      super.completeExceptionally(e);
      e.printStackTrace();
    }
    
    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) {
      super.complete(response);
    }
  }
  
  private static class Requests {
    private static final Request SCHOOLS = new Request.Builder()
        .url(BASE_URL + "/schools")
        .method("GET", null)
        .addHeader("User-Agent", USER_AGENT)
        .build();
    
    private static Request login(Login l) {
      MediaType json = MediaType.parse("application/json");
      RequestBody body = RequestBody.create(gson.toJson(l), json);
      return new Request.Builder()
          .url(BASE_URL + "/login")
          .method("POST", body)
          .addHeader("User-Agent", USER_AGENT)
          .addHeader("Content-Type", "application/json")
          .build();
    }
  }
}
