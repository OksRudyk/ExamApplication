package com.example.testsapplication.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExamController {
  private String mExamName;
  private String mExamDescription;
  private int mPosition;
  List<Question> mQuizList;
  private Integer mExamQuestionsCount;

  private int mCorrectAnswers;
  private int mSkippedQuestions;
  private List<Integer> mQnA;

  public ExamController() {

  }

  public ExamController(Test test) {
    mExamName = test.getTestName();
    mExamDescription = test.getDescription();
    mExamQuestionsCount = test.getQuestionsInExam();
    List<Question> imported_qs = test.getQuestions();
    mQuizList = new ArrayList<>();
    importRandomQuestions(imported_qs);
    mQnA = new ArrayList<>(Collections.nCopies(mExamQuestionsCount, 0));
    mPosition = 0;

  }

  public int getPosition() {
    return mPosition;
  }

  public void skipQuestion() {
    mQnA.set(mPosition, -1);
    mSkippedQuestions++;
    moveToNext();
  }

  public Question getCurrentQuestion() {
    return mQuizList.get(mPosition);
  }

  public String getName() {
    return mExamName;
  }

  public String getDescription() {
    return mExamDescription;
  }

  public int getExamQuestionsCount() {
    return mExamQuestionsCount;
  }

  public void startExam() {

  }

  public void endExam() {

  }
  /* answer index is  number of checkbox */
  public void answerQuestion(int ans_id) {
    mQnA.set(mPosition, ans_id);
    if (mQuizList.get(mPosition).getAnswers().get(ans_id).is_Correct()) {
      mCorrectAnswers++;
    }
    moveToNext();
  }

  private void moveToNext() {
    mPosition++;
  }

  public int getCorrectAnswers() {
    return mCorrectAnswers;
  }

  public int getSkippedQuestions() {
    return mSkippedQuestions;
  }

  public int getIncorrectAnswers() {
    return mExamQuestionsCount - mCorrectAnswers - mSkippedQuestions;
  }
  /*Questions should display in random order, answers should display in as they are in db*/
  private void importRandomQuestions(List<Question> imported_qs) {
    List<Question> availableQuestions = new ArrayList<>(imported_qs);
    int leftToGenerate = mExamQuestionsCount;
    List<Question> generatedExam = new ArrayList<>();
    while (availableQuestions.size() > 0 && leftToGenerate > 0) {
      int position = (int) (Math.random() * availableQuestions.size());
      generatedExam.add(availableQuestions.get(position));
      availableQuestions.remove(position);
      leftToGenerate--;
    }
    mQuizList = generatedExam;
  }
}


