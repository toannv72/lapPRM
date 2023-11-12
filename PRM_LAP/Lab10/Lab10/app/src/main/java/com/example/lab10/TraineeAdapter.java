package com.example.lab10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import model.Trainee;

public class TraineeAdapter extends BaseAdapter {

    private XemActivity context;
    private int layout;
    private List<Trainee> traineeList;

    public TraineeAdapter(XemActivity context, int layout, List<Trainee> traineeList) {
        this.context = context;
        this.layout = layout;
        this.traineeList = traineeList;
    }

    @Override
    public int getCount() {
        return traineeList.size();
    }

    @Override
    public Object getItem(int position) {
        return traineeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView txtName, txtEmail, txtPhone, txtGender;
        Button btnUpdate;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            viewHolder.txtName = (TextView) view.findViewById(R.id.textViewName);
            viewHolder.txtEmail = (TextView) view.findViewById(R.id.textViewEmail);
            viewHolder.txtPhone = (TextView) view.findViewById(R.id.textViewPhone);
            viewHolder.txtGender = (TextView) view.findViewById(R.id.textViewGender);
            viewHolder.btnUpdate = (Button) view.findViewById(R.id.buttonUpdate);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Trainee trainee = traineeList.get(position);
        viewHolder.txtName.setText("Name: " + trainee.getName());
        viewHolder.txtEmail.setText("Email: " + trainee.getEmail());
        viewHolder.txtPhone.setText("Phone: " + trainee.getPhone());
        viewHolder.txtGender.setText("Gender: " + trainee.getGender());

        //Sua
        viewHolder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogSuaNhanVien(trainee.getId(), trainee.getName(), trainee.getEmail(), trainee.getPhone(), trainee.getGender());
            }
        });
        return view;
    }
}
