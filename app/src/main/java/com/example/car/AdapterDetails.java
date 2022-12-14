package com.example.car;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

@SuppressLint("ViewHolder")
public class AdapterDetails extends BaseAdapter {

    private Context ThisContext;
    private List<Details> DetailsList;

    public AdapterDetails(Context context, List<Details> detailsList) {
        ThisContext = context;
        DetailsList = detailsList;
    }

    @Override
    public int getCount() {
        return DetailsList.size();
    }

    @Override
    public Object getItem(int position) {
        return DetailsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return DetailsList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(ThisContext, R.layout.item_details, null);

        TextView tvDetail = v.findViewById(R.id.tvDetail);

        Details detail = DetailsList.get(position);
        tvDetail.setText(detail.getDetail());

        return v;
    }
}