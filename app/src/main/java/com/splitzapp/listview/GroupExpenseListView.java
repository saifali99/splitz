package com.splitzapp.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.splitzapp.R;

import java.util.List;

public class GroupExpenseListView extends ArrayAdapter<String> {

    private Context context;
    private List<String> involvedUser;
    private List<String> involvedUserAmount;

    public GroupExpenseListView(Context context, List<String> user, List<String> amount) {
        super(context, R.layout.groupexpenselistview_layout, user);
        this.context = context;
        this.involvedUser = user;
        this.involvedUserAmount = amount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.groupexpenselistview_layout, null, true);

        ViewHolder viewHolder = new GroupExpenseListView.ViewHolder(view);
        view.setTag(viewHolder);

        viewHolder.tv1.setText(involvedUser.get(position));
        viewHolder.tv2.setText(involvedUserAmount.get(position));

        return view;
    }

    class ViewHolder {
        TextView tv1;
        TextView tv2;

        ViewHolder(View v) {
            tv1 = v.findViewById(R.id.tvInvolvedUser);
            tv2 = v.findViewById(R.id.tvInvolvedUserAmount);
        }
    }
}
