package com.example.lab09.PersonDao;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab09.EditPersonActivity;
import com.example.lab09.R;
import com.example.lab09.constants.Constants;
import com.example.lab09.model.Person;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {
    private final Context context;
    private List<Person> mpersonList;

    public PersonAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PersonAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.person_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(mpersonList.get(i).getFirstName());
        myViewHolder.email.setText(mpersonList.get(i).getLastName());
    }

    @Override
    public int getItemCount() {
        if (mpersonList == null) {
            return 0;
        }
        return mpersonList.size();
    }

    public void setTasks(List<Person> personList) {
        mpersonList = personList;
        notifyDataSetChanged();
    }

    public List<Person> getTasks() {
        return mpersonList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, email;
        ImageView editImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.edtFirstName);
            email = itemView.findViewById(R.id.edtLastName);

            editImage = itemView.findViewById(R.id.ivEdit);
            editImage.setOnClickListener(view -> {
                int elementId = mpersonList.get(getAdapterPosition()).getUid();
                Intent i = new Intent(context, EditPersonActivity.class);
                i.putExtra(Constants.UPDATE_Person_Id, elementId);
                context.startActivity(i);
            });
        }
    }
}
