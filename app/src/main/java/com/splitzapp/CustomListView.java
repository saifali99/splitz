package com.splitzapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomListView extends ArrayAdapter {
    private List<String> val;
    private List<String> lab;
    private List<String> desc;
    private Context context;

    public CustomListView(Context context, List<String>val, List<String>lab, List<String>desc) {
        super(context, R.layout.listview_layout, val);

        this.context = context;
        this.val = val;
        this.lab = lab;
        this.desc = desc;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = super.getView(position, convertView, parent);

        ViewHolder holder;
//        if (convertView == null) {
            holder = new ViewHolder();
//            convertView = LayoutInflater.from(context).inflate(R.layout.listview_layout, parent, false);

            holder.et1 = (EditText) v.findViewById(R.id.etValue);
            holder.et2 = (EditText) v.findViewById(R.id.etLabel);
            holder.et3 = (EditText) v.findViewById(R.id.etDescription);
//        }
//        else {
//            holder = (ViewHolder) convertView.getTag();
//        }

        holder.et1.setText(val.get(position));
        holder.et2.setText(lab.get(position));
        holder.et3.setText(desc.get(position));


//        View v=((Activity)getContext()).getLayoutInflater().inflate(R.layout.listview_layout,null);
//        EditText et1 = convertView.findViewById(R.id.etValue);
//        EditText et2 = convertView.findViewById(R.id.etLabel);
//        EditText et3 = convertView.findViewById(R.id.etDescription);
//
//        et1.setText(val.get(position));
//        et2.setText(lab.get(position));
//        et3.setText(desc.get(position));

        return convertView;
/*        View r = convertView;
        ViewHolder viewHolder = null;
        if (r == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.listview_layout, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) r.getTag();
        }

        viewHolder.tv1.setText(val.get(position));
        viewHolder.tv2.setText(lab.get(position));
        viewHolder.tv3.setText(desc.get(position));


        return super.getView(position, convertView, parent);*/
    }

    class ViewHolder {
        EditText et1;
        EditText et2;
        EditText et3;
    }

}
