package com.splitzapp.listview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.splitzapp.R;

import java.util.List;

public class ExpenseListView extends ArrayAdapter<String> {
    private List<String> val;
    private List<String> lab;
    private List<String> cat;
    private Context context;

    public ExpenseListView(Context context, List<String> value, List<String> label, List<String> cat) {
        super(context, R.layout.expenselistview_layout, value);

        this.context = context;
        this.val = value;
        this.lab = label;
        this.cat = cat;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
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
        viewHolder.et3.setText(cat.get(position));

        switch (cat.get(position)) {
            case "Education":
                viewHolder.img1.setImageResource(R.drawable.education);
                break;

            case "Food":
                viewHolder.img1.setImageResource(R.drawable.food);
                break;

            case "Travel":
                viewHolder.img1.setImageResource(R.drawable.travel);
                break;

            case "House":
                viewHolder.img1.setImageResource(R.drawable.home);
                break;

            case "Other":
                viewHolder.img1.setImageResource(R.drawable.uncategorized);
                break;
        }

        return view;
    }

    class ViewHolder {
        TextView et1;
        TextView et2;
        TextView et3;
        ImageView img1;

        ViewHolder(View v) {
            et1 = v.findViewById(R.id.entry1);
            et2 = v.findViewById(R.id.entry2);
            et3 = v.findViewById(R.id.entry3);
            img1 = v.findViewById(R.id.imgIcon);
        }
    }
}
