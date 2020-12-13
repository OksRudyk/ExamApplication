package com.example.testsapplication.model;

public class Answer {
  private boolean mIsCorrect;
  private String mAnsBody;

  public Answer(String body, boolean correct) {
    mAnsBody = body;
    mIsCorrect = correct;
  }
  public String getAnsBody() {
    return mAnsBody;
  }
  public boolean is_Correct() {
    return mIsCorrect;
  }
}
