package com.splitzapp.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;

import com.splitzapp.R;

import java.util.List;

public class GroupExpenseListView extends ArrayAdapter<String> {
    List<String> user;
    List<String> userAmount;
    private Context context;

    public  GroupExpenseListView(Context context, List<String> u, List<String> a) {
        super(context, R.layout.groupexpenselistview_layout, u);

        this.context = context;
        this.user = u;
        this.userAmount = a;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.groupexpenselistview_layout, null, true);

        ViewHolder viewHolder = new GroupExpenseListView.ViewHolder(view);
        view.setTag(viewHolder);

        viewHolder.cb1.setText(user.get(position));

        return view;
    }

    class ViewHolder {
        CheckBox cb1;
        EditText et1;

        ViewHolder(View v) {
            cb1 = v.findViewById(R.id.cbUser);
            et1 = v.findViewById(R.id.etExpenseAmount);
        }
    }

}
