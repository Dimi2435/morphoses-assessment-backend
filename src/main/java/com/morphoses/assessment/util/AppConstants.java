package com.morphoses.assessment.util;

public class AppConstants {
  public static final String API_BASE_PATH = "/api";
  public static final String DEFAULT_API_VERSION = "1";

  // Injected application properties
  public static final String PROJECT_NAME = "Morphoses Assessment";
  public static final String PROJECT_VERSION = "1.0";
  public static final String CLASSROOMS_ENDPOINT =
      API_BASE_PATH + "/v" + DEFAULT_API_VERSION + "/classrooms";
  public static final String REPORTS_ENDPOINT =
      API_BASE_PATH + "/v" + DEFAULT_API_VERSION + "/reports";
  public static final String SESSIONS_ENDPOINT =
      API_BASE_PATH + "/v" + DEFAULT_API_VERSION + "/sessions";
  public static final String USERS_ENDPOINT = API_BASE_PATH + "/v" + DEFAULT_API_VERSION + "/users";
}
