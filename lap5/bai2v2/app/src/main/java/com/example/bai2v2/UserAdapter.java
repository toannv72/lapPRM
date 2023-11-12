package com.example.bai2v2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private ArrayList<Bao> subjectList;
    private Context context;
    private int selectedPosition = RecyclerView.NO_POSITION;

    public UserAdapter(ArrayList<Bao> userList, Context context) {
        this.subjectList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.item_user, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bao user = subjectList.get(position);

        holder.title.setText(user.getTitle());
        holder.desTitle.setText(user.getDestile());
        holder.demuc.setText(user.getDemuc());
        Glide.with(context)
                .load(user.getImg())
                .into(holder.anh);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition(); // Retrieve the current position
                setSelectedPosition(clickedPosition);
                notifyDataSetChanged();
                ((baoActivity) context).displayUserDetails(user, clickedPosition);
            }
        });

        if (position == selectedPosition) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.toan));
        } else {
            holder.itemView.setBackgroundColor(context.getResources().getColor(android.R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, desTitle, demuc;
        ImageView anh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            desTitle = itemView.findViewById(R.id.des_title);
            demuc = itemView.findViewById(R.id.demuc);
            anh = itemView.findViewById(R.id.anh);
        }
    }
}
