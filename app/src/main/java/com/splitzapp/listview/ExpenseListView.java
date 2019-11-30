package com.splitzapp.listview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.splitzapp.R;

import java.util.List;

public class ExpenseListView extends ArrayAdapter<String> {
    private List<String> val;
    private List<String> lab;
    private Context context;

    public ExpenseListView(Context context, List<String> value, List<String> label) {
        super(context, R.layout.expenselistview_layout, value);

        this.context = context;
        this.val = value;
        this.lab = label;
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

        viewHolder.et1.setText(val.get(position));
        viewHolder.et2.setText(lab.get(position));

        return view;
    }

    class ViewHolder {
        TextView et1;
        TextView et2;
        TextView et3;

        ViewHolder(View v) {
            et1 = (TextView) v.findViewById(R.id.entry1);
            et2 = (TextView) v.findViewById(R.id.entry2);
        }
    }
}
