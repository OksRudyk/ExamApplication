package com.example.testsapplication.model;

import android.os.CountDownTimer;

import java.util.List;

/*USELESS*/
/*Created for encapsulating timer, but seems it is impossible to update ui from other class in adequate way*/
/*DO NOT USE IT */
@Deprecated
public class ExamQuestion {
  Question mManagingQuestion;
  private CountDownTimer mQuestionTimer;
  private long mMillisTimeLeft;
  private int mAnswerIndex;

  ExamQuestion() {

  }

  ExamQuestion(Question question) {
    mManagingQuestion = question;
    mMillisTimeLeft = 30000;
    mQuestionTimer = new CountDownTimer(mMillisTimeLeft, 1000) {
      @Override
      public void onTick(long millisUntilFinished) {
        mMillisTimeLeft = millisUntilFinished;
      }

      @Override
      public void onFinish() {

      }
    };
  }

  public void questionChosen() {
    mQuestionTimer.start();
  }

  public void answerQuestion(int ans_id) {
    mAnswerIndex = ans_id;
    mQuestionTimer.cancel();
  }

  public boolean checkAnswerCorrect(int ans_id) {
    if (mManagingQuestion.getAnswers().get(ans_id).is_Correct()) {
      return true;
    } else {
      return false;
    }
  }

  public void skipQuestion() {
    mAnswerIndex = -1;
    mQuestionTimer.cancel();
  }

  public String getBody() {
    return mManagingQuestion.getBody();
  }

  public int getAnswerCount() {
    return mManagingQuestion.getAnswerCount();
  }

  public List<Answer> getAnswers() {
    return mManagingQuestion.getAnswers();
  }

  public long getTimeLeftinMilillis() {
    return mMillisTimeLeft;
  }
}