package com.example.lab5bai1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
   private ArrayList<User> userList;

   public UserAdapter(ArrayList<User> userList){
       this.userList=userList;
   }


    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater= LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.item_user, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User user = userList.get(position);

        holder.tvUsername.setText("Username "+user.getUsername());
        holder.tvFullname.setText("Fullname "+user.getFullname());
        holder.tvEmail.setText("Email "+user.getEmail());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       TextView tvUsername, tvFullname, tvEmail;

       public ViewHolder(@NonNull View itemView){
           super(itemView);

           tvUsername= itemView.findViewById(R.id.tv_username);
           tvFullname= itemView.findViewById(R.id.tv_fullname);
           tvEmail= itemView.findViewById(R.id.tv_email);

       }
    }
}
