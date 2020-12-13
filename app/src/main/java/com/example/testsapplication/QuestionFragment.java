package com.example.testsapplication;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.testsapplication.model.Answer;
import com.example.testsapplication.model.Question;
import com.example.testsapplication.testmanager.TestManager;

import java.util.List;
import java.util.Locale;


public class QuestionFragment extends Fragment {

  private TextView display_time;

  public QuestionFragment() {
    // Required empty public constructor
  }

  public static QuestionFragment newInstance() {
    return new QuestionFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    View returnView = inflater.inflate(R.layout.fragment_question, container, false);
    Button answer_button = returnView.findViewById(R.id.answer_button);
    Button skip_button = returnView.findViewById(R.id.skip_button);
    display_time = returnView.findViewById(R.id.time_shower);
    TextView tv_question_body = returnView.findViewById(R.id.question_body);
    TextView tv_question_number = returnView.findViewById(R.id.question_number);
    final RadioGroup radioGroup = (RadioGroup) returnView.findViewById(R.id.answers_radio_group);

    final Question curr_question = TestManager.getInstance().getCurrentExam().getCurrentQuestion();
    String position = String.valueOf(TestManager.getInstance().getCurrentExam().getPosition() + 1);
    String max_position = String.valueOf(TestManager.getInstance().getCurrentExam().getExamQuestionsCount());
    tv_question_body.setText(curr_question.getBody());
    tv_question_number.setText(position + "/" + max_position);

    List<Answer> answer_list = curr_question.getAnswers();
    for (Answer answer : answer_list) {
      RadioButton rdbtn = new RadioButton(getContext());
      rdbtn.setId(View.generateViewId());
      rdbtn.setText(answer.getAnsBody());
      radioGroup.addView(rdbtn);
    }
    final int exam_questions_count = TestManager.getInstance().getCurrentExam().getExamQuestionsCount();
    final int current_q_position = TestManager.getInstance().getCurrentExam().getPosition();
    long millisUntilFinished = 30000;
    final CountDownTimer QuestionTimer = new CountDownTimer(millisUntilFinished, 1000) {
      @Override
      public void onTick(long millisUntilFinished) {
        updateTimerBox(millisUntilFinished);
      }

      @Override
      public void onFinish() {
        if (exam_questions_count - current_q_position == 1) {
          TestManager.getInstance().getCurrentExam().endExam();
          Fragment finalfragment = new FinishPageFragment();
          navigateToFragment(finalfragment);
        } else {
          TestManager.getInstance().getCurrentExam().skipQuestion();
          Fragment questionFragment = new QuestionFragment();
          navigateToFragment(questionFragment);
        }
      }
    };
    QuestionTimer.start();
    answer_button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final int radioButtonID = radioGroup.getCheckedRadioButtonId();
        final View radioButton = radioGroup.findViewById(radioButtonID);
        final int idx = radioGroup.indexOfChild(radioButton); // here we know the index of answer
        if (idx != -1) {
          if (exam_questions_count - current_q_position == 1) {
            QuestionTimer.cancel();
            TestManager.getInstance().getCurrentExam().answerQuestion(idx);
            Fragment finalfragment = new FinishPageFragment();
            navigateToFragment(finalfragment);
          } else {
            QuestionTimer.cancel();
            TestManager.getInstance().getCurrentExam().answerQuestion(idx);
            Fragment questionFragment = new QuestionFragment();
            navigateToFragment(questionFragment);
          }
        }
      }
    });
    skip_button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (exam_questions_count - current_q_position == 1) {
          QuestionTimer.cancel();
          TestManager.getInstance().getCurrentExam().endExam();
          Fragment finalfragment = new FinishPageFragment();
          navigateToFragment(finalfragment);
        } else {
          QuestionTimer.cancel();
          TestManager.getInstance().getCurrentExam().skipQuestion();
          Fragment questionFragment = new QuestionFragment();
          navigateToFragment(questionFragment);
        }
      }
    });

    return returnView;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

  }

  private void navigateToFragment(Fragment fragment) {
    assert getFragmentManager() != null;
    FragmentTransaction transaction = getFragmentManager().beginTransaction();
    transaction.replace(R.id.fragment_container, fragment);
    transaction.addToBackStack(null);
    transaction.commit();
  }

  private void updateTimerBox(long time) {
    int minutes = (int) (time / 1000) / 60;
    int seconds = (int) (time / 1000) % 60;
    display_time.setText(String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds));
  }
}
