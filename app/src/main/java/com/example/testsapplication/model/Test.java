package com.example.testsapplication.model;

import java.util.List;


public class Test {

  private String mTestName;
  private String mDescription;
  private List<Question> mQuestions;
  private Integer mQuestionsInExam;  /* value received from JSON that represents only the our desision regarding how many questions will be selected from ALL questions array to the test*/

  public Test() {

  }

  public Test(List<Question> list, String name, String description, Integer questions_in_exam) {
    mQuestions = list;
    mTestName = name;
    mDescription = description;
    mQuestionsInExam = questions_in_exam;
  }

  public String getDescription() {
    return mDescription;
  }

  public String getTestName() {
    return mTestName;
  }

  public int getQuestionsCount() {
    return mQuestions.size();
  }

  public List<Question> getQuestions() {
    return mQuestions;
  }

  public Integer getQuestionsInExam() {
    return mQuestionsInExam;
  }
}