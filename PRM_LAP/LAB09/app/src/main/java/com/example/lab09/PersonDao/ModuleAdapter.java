package com.example.lab09.PersonDao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab09.MainActivity2;
import com.example.lab09.R;
import com.example.lab09.model.Module;

import java.util.List;

public class ModuleAdapter extends BaseAdapter {
    private final MainActivity2 context;
    private final int layout;
    private final List<Module> moduleList;

    public ModuleAdapter(MainActivity2 context, int layout, List<Module> moduleList) {
        this.context = context;
        this.layout = layout;
        this.moduleList = moduleList;
    }

    @Override
    public int getCount() {
        return moduleList.size();
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
            holder.txtTitle = view.findViewById(R.id.txtTitle);
            holder.txtDescription = view.findViewById(R.id.txtDescription);
            holder.txtCategory = view.findViewById(R.id.txtCategory);

            holder.imgView = view.findViewById(R.id.imgView);
            holder.imgView.setImageResource(R.drawable.ic_launcher_foreground);

            holder.btnEdit = view.findViewById(R.id.btnEdit);
            holder.btnDelete = view.findViewById(R.id.btnDelete);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Module module = moduleList.get(i);
        holder.txtTitle.setText(module.getTitle());
        holder.txtDescription.setText(module.getDescription());
        holder.txtCategory.setText(module.getCategory());

        holder.btnEdit.setOnClickListener(v -> {
            context.DialogSuaModule(module.getId(), module.getTitle(), module.getDescription(), module.getCategory());
        });

        holder.btnDelete.setOnClickListener(a -> {
            context.DialogXoaModule(module.getId(), module.getTitle());
        });
        return view;
    }

    private class ViewHolder {
        TextView txtTitle, txtDescription, txtCategory;
        ImageView btnDelete, btnEdit, imgView;
    }
}
