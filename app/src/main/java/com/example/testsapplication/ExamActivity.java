package com.example.testsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.testsapplication.testmanager.TestManager;

public class ExamActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_start);

    Bundle bundle = getIntent().getExtras();
    TestManager.getInstance().createExamFor(bundle.getInt("AdapterPosition"));
    Fragment startTestPageFragment = new StartExamPageFragment();
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.add(R.id.fragment_container, startTestPageFragment);
    transaction.commit();

  }
}
