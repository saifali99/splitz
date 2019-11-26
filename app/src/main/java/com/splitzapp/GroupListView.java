
package com.splitzapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class GroupListView extends ArrayAdapter<String> {
    private List<String> groupname;
    private Context context;

    public GroupListView(Context context, List<String> group) {
        super(context, R.layout.expenselistview_layout, group);

        this.context = context;
        this.groupname = group;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null) {
            LayoutInflater layoutInflater = ((Activity) getContext()).getLayoutInflater();
            view = layoutInflater.inflate(R.layout.expenselistview_layout, null, true);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.et1.setText(groupname.get(position));

        return view;
    }

    class ViewHolder {
        TextView et1;

        ViewHolder(View v) {
            et1 = (TextView) v.findViewById(R.id.entry1);
        }
    }
}
