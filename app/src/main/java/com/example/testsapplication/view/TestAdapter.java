package com.example.testsapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testsapplication.ExamActivity;
import com.example.testsapplication.R;
import com.example.testsapplication.model.Test;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder>{
    private List<Test> mTests;
    public TestAdapter(List<Test> tests) {
        mTests = tests;
    }
    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.test_list_item,parent,false);
        TestViewHolder viewHolder = new TestViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
           holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mTests.size();
    }

    class TestViewHolder extends RecyclerView.ViewHolder {

        TextView viewHolderIndex;
        public TestViewHolder(View itemView) {
            super(itemView);

            viewHolderIndex = itemView.findViewById(R.id.tv_view_holder_number);
            viewHolderIndex.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent activity = new Intent(v.getContext(), ExamActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("AdapterPosition",getAdapterPosition());
                    activity.putExtras(bundle);
                    activity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    v.getContext().startActivity(activity);
                }
              }
            );
        }
        public void bind(int listIndex) {
            viewHolderIndex.setText(mTests.get(listIndex).getTestName());
        }
    }

}
