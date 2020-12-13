package com.example.testsapplication.composeexamdb;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class JSONProvider {

  private JSONObject mJsonObject;

  public JSONProvider() {

  }

  public JSONObject getJsonDB() {
    return mJsonObject;
  }

  public JSONObject loadJSONFromResourceFile(Context context, int resource_id) throws JSONException {
    InputStream stream = context.getResources().openRawResource(resource_id);
    mJsonObject = new JSONObject(inputStreamToString(stream));
    return mJsonObject;
  }

  private String inputStreamToString(InputStream inputStream) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    try {
      int ctr = inputStream.read();
      while (ctr != -1) {
        byteArrayOutputStream.write(ctr);
        ctr = inputStream.read();
      }
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return byteArrayOutputStream.toString();
  }
}
