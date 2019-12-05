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

public class ReportLstView extends ArrayAdapter<String> {
    private List<String> value;
    private List<String> cat;
    private Context context;

    public ReportLstView(Context context, List<String> value, List<String> cat) {
        super(context, R.layout.reportlistview_layout, value);

        this.context = context;
        this.value = value;
        this.cat = cat;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;

        if(view == null) {
            LayoutInflater layoutInflater = ((Activity) getContext()).getLayoutInflater();
            view = layoutInflater.inflate(R.layout.reportlistview_layout, null, true);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else  {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tv1.setText(value.get(position));
        viewHolder.tv2.setText(cat.get(position));

        return view;
    }

    class ViewHolder {
        TextView tv1;
        TextView tv2;

        ViewHolder(View v) {
            tv1 = v.findViewById(R.id.tvReportCat);
            tv2 = v.findViewById(R.id.tvReportval);
        }
    }

}
