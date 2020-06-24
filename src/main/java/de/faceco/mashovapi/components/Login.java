package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import de.faceco.mashovapi.API;

import java.util.Objects;
import java.util.stream.IntStream;

/**
 * The body of a login request. Used with {@link de.faceco.mashovapi.API#login(int, String, String)}
 */
public final class Login {
  private final int semel;
  private final String username;
  private final String password;
  private final int year;
  private final String appName;
  private final String apiVersion;
  private final String appVersion;
  private final String appBuild;
  private final String deviceUuid;
  private final String devicePlatform;
  private final String deviceManufacturer;
  private final String deviceModel;
  private final String deviceVersion;
  
  /**
   * Create a login request with a given year.
   *
   * @param school   The School object that represents the user's school. Contains an array of years in the system.
   * @param username The username, usually an ID.
   * @param password The user's password.
   * @param year     The requested year. The year must be included in school.years.
   * @throws IllegalArgumentException If the requested year is not in the school's object.
   */
  public Login(School school, int year, String username, String password) {
    // Check if the requested year is present in Mashov's database for this school.
    if (IntStream.of(school.getYears()).noneMatch(x -> x == year)) {
      throw new IllegalArgumentException("Year not in school!");
    }
    this.year = year;
    
    semel = school.getId();
    this.username = username;
    this.password = password;
    appName = "info.mashov.students";
    appBuild = apiVersion = appVersion = "3.20200528";
    devicePlatform = deviceUuid = "chrome";
    deviceManufacturer = "windows";
    deviceModel = "desktop";
    deviceVersion = "83.0.4103.61";
  }
  
  /**
   * Create a login request for the most recent year supported by the school.
   *
   * <p><b>CAUTION:</b> May not work as expected in summer months.</p>
   *
   * @param school   The user's school, from {@link API#getAllSchools()} or {@link API#fetchSchool(int) API#fetchSchool()}
   * @param username The student's username.
   * @param password The student's password.
   */
  public Login(School school, String username, String password) {
    this(school, school.getCurrentYear(), username, password);
  }
  
  /**
   * Returns the school's ID.
   *
   * @see School#getId()
   */
  public int getSchoolId() {
    return semel;
  }
  
  public String getUsername() {
    return username;
  }
  
  public String getPassword() {
    return password;
  }
  
  public int getYear() {
    return year;
  }
  
  public String getAppName() {
    return appName;
  }
  
  public String getApiVersion() {
    return apiVersion;
  }
  
  public String getAppVersion() {
    return appVersion;
  }
  
  public String getAppBuild() {
    return appBuild;
  }
  
  public String getDeviceUuid() {
    return deviceUuid;
  }
  
  public String getDevicePlatform() {
    return devicePlatform;
  }
  
  public String getDeviceManufacturer() {
    return deviceManufacturer;
  }
  
  public String getDeviceModel() {
    return deviceModel;
  }
  
  public String getDeviceVersion() {
    return deviceVersion;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    Login l = (Login) o;
    
    if (semel != l.semel) return false;
    if (year != l.year) return false;
    if (!Objects.equals(username, l.username)) return false;
    if (!Objects.equals(password, l.password)) return false;
    if (!Objects.equals(appName, l.appName)) return false;
    if (!Objects.equals(apiVersion, l.apiVersion)) return false;
    if (!Objects.equals(appVersion, l.appVersion)) return false;
    if (!Objects.equals(appBuild, l.appBuild)) return false;
    if (!Objects.equals(deviceUuid, l.deviceUuid)) return false;
    if (!Objects.equals(devicePlatform, l.devicePlatform)) return false;
    if (!Objects.equals(deviceManufacturer, l.deviceManufacturer)) return false;
    if (!Objects.equals(deviceModel, l.deviceModel)) return false;
    return Objects.equals(deviceVersion, l.deviceVersion);
  }
  
  @Override
  public int hashCode() {
    int result = semel;
    result = 31 * result + (username != null ? username.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + year;
    result = 31 * result + (appName != null ? appName.hashCode() : 0);
    result = 31 * result + (apiVersion != null ? apiVersion.hashCode() : 0);
    result = 31 * result + (appVersion != null ? appVersion.hashCode() : 0);
    result = 31 * result + (appBuild != null ? appBuild.hashCode() : 0);
    result = 31 * result + (deviceUuid != null ? deviceUuid.hashCode() : 0);
    result = 31 * result + (devicePlatform != null ? devicePlatform.hashCode() : 0);
    result = 31 * result + (deviceManufacturer != null ? deviceManufacturer.hashCode() : 0);
    result = 31 * result + (deviceModel != null ? deviceModel.hashCode() : 0);
    result = 31 * result + (deviceVersion != null ? deviceVersion.hashCode() : 0);
    return result;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("semel", semel)
        .add("username", username)
        .add("password", password)
        .add("year", year)
        .add("appName", appName)
        .add("apiVersion", apiVersion)
        .add("appVersion", appVersion)
        .add("appBuild", appBuild)
        .add("deviceUuid", deviceUuid)
        .add("devicePlatform", devicePlatform)
        .add("deviceManufacturer", deviceManufacturer)
        .add("deviceModel", deviceModel)
        .add("deviceVersion", deviceVersion)
        .toString();
  }
}
