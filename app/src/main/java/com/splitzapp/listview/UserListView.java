package com.splitzapp.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.splitzapp.R;

import java.util.List;

public class UserListView extends ArrayAdapter<String> {
    private List<String> personName;
    private Context context;

    public UserListView(Context context, List<String> person) {
        super(context, R.layout.userlistview_layout, person);

        this.context = context;
        this.personName = person;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.userlistview_layout, null, true);

        ViewHolder viewHolder = new UserListView.ViewHolder(view);
        view.setTag(viewHolder);

        viewHolder.tv1.setText(personName.get(position));

        return view;
    }

    class ViewHolder {
        TextView tv1;
        ViewHolder(View v) {
            tv1 = (TextView) v.findViewById(R.id.tvperson);
        }
    }
}
