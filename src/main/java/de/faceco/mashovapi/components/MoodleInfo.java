package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import de.faceco.mashovapi.API;

public final class MoodleInfo {
  private long username;
  private String password;
  
  MoodleInfo() {
  
  }
  
  public long getUsername() {
    return username;
  }
  
  public String getPassword() {
    return password;
  }
  
  /**
   * Create a link to view a Moodle assignment
   *
   * @param loginInfo The LoginInfo object returned from {@link API#login(int, String, String)}
   * @param linkId    The link ID
   * @return A string representing a URL
   */
  public String generateAssignmentLink(LoginInfo loginInfo, long linkId) {
    return String.format("%s/login/qslogin.php?mu=%d&mp=%s&wantsurl=mod/assign/view.php?id=%d",
        loginInfo.getAccessToken().getSchoolOptions().getMoodleSite(),
        username,
        password,
        linkId);
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("username", username)
        .add("password", password)
        .toString();
  }
}
