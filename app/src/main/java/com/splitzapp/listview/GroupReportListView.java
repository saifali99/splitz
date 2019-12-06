package com.splitzapp.listview;

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

public class GroupReportListView extends ArrayAdapter<String> {
    private List<String> username;
    private List<String> balance;
    private Context context;

    public GroupReportListView(Context context, List<String>user, List<String>bal) {
        super(context, R.layout.groupreportlistview_layout, user);
        this.context = context;
        this.username = user;
        this.balance = bal;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.userlistview_layout, null, true);

        GroupReportListView.ViewHolder viewHolder = new GroupReportListView.ViewHolder(view);
        view.setTag(viewHolder);

        viewHolder.tv1.setText(username.get(position));
        viewHolder.tv1.setText(balance.get(position));

        return view;
    }

    class ViewHolder {
        TextView tv1;
        TextView tv2;

        ViewHolder(View v) {
            tv1 = v.findViewById(R.id.tvUsername);
            tv2 = v.findViewById(R.id.tvPositive);
        }
    }
}
