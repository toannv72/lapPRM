package com.example.lab09.PersonDao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab09.MainActivity;
import com.example.lab09.R;
import com.example.lab09.model.CongViec;

import java.util.List;

public class CongViecAdapter extends BaseAdapter {
    private final MainActivity context;
    private final int layout;
    private final List<CongViec> congViecList;

    public CongViecAdapter(MainActivity context, int layout, List<CongViec> congViecList) {
        this.context = context;
        this.layout = layout;
        this.congViecList = congViecList;
    }

    @Override
    public int getCount() {
        return congViecList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtTen = view.findViewById(R.id.textViewTen);
            holder.imgDelete = view.findViewById(R.id.imageViewDelete);
            holder.imgEdit = view.findViewById(R.id.imageViewEdit);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        CongViec congViec = congViecList.get(i);
        holder.txtTen.setText(congViec.getTenCV());

        holder.imgEdit.setOnClickListener(v -> {
            context.DialogSuaCongViec(congViec.getTenCV(), congViec.getIdCV());
        });

        holder.imgDelete.setOnClickListener(a -> {
            context.DialogxoaCongViec(congViec.getTenCV(), congViec.getIdCV());
        });
        return view;
    }

    private class ViewHolder {
        TextView txtTen;
        ImageView imgDelete, imgEdit;
    }
}
