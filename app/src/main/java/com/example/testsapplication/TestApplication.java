package com.example.testsapplication;

import com.example.testsapplication.composeexamdb.ExamDB;
import com.example.testsapplication.composeexamdb.JSONProvider;
import com.example.testsapplication.composeexamdb.JsonBasedExamDB;
import com.example.testsapplication.testmanager.TestManager;

import org.json.JSONException;

public class TestApplication extends android.app.Application {
  public TestApplication() {

  }
  @Override
  public void onCreate() {
    super.onCreate();
    JSONProvider loaded_json_resource;
    ExamDB loaded_main_tests_db;
    try {
      loaded_json_resource = new JSONProvider();
      loaded_json_resource.loadJSONFromResourceFile(getApplicationContext(), R.raw.first_test);
      loaded_main_tests_db = new JsonBasedExamDB(loaded_json_resource.getJsonDB());
      TestManager.getInstance().setImportedDb(loaded_main_tests_db.getAllTests());
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
}
