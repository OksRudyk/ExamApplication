package com.example.testsapplication.model;

import java.util.List;

public class Question {

  private List<Answer> mAnswers;
  private String mBody;

  public Question(List<Answer> answers, String body) {
    mAnswers = answers;
    mBody = body;
  }

  public String getBody() {
    return mBody;
  }

  public int getAnswerCount() {
    return mAnswers.size();
  }

  public List<Answer> getAnswers() {
    return mAnswers;
  }
}
