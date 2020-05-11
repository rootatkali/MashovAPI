package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;

/**
 * The body of a login request. Used with {@link de.faceco.mashovapi.API#login(int, String, String)}
 */
public final class Login {
  private String username;
  private School school;
  private int year;
  private String password;
  private int semel;
  private String appName;
  private double appVersion;
  private double appBuild;
  private double apiVersion;
  private String deviceUuid;
  private String devicePlatform;
  private String deviceManufacturer;
  private String deviceModel;
  private String deviceVersion;
  
  Login() {
  
  }
  
  Login(School school, int year, String username, String password) {
    this.school = school;
    this.year = year;
    this.username = username;
    this.password = password;
    semel = school.getId();
    appName = "com.mashov.main";
    appVersion = 3.20191017;
    appBuild = 3.20191017;
    apiVersion = 3.20191017;
    deviceUuid = "chrome";
    devicePlatform = "chrome";
    deviceManufacturer = "win";
    deviceModel = "desktop";
    deviceVersion = "78.0.3904.87";
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("school", school).add("year", year).add("username"
        , username).add("password", password).add("semel", semel).add("appName", appName).add(
            "appVersion", appVersion).add("appBuild", appBuild).add("apiVersion", apiVersion)
        .add("deviceUuid", deviceUuid).add("devicePlatform", devicePlatform)
        .add("deviceManufacturer", deviceManufacturer).add("deviceModel", deviceModel)
        .add("deviceVersion", deviceVersion).toString();
  }
}
