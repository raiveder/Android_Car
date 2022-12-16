package com.example.car;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

@SuppressLint("ViewHolder")
public class AdapterServices extends BaseAdapter {

    private Context ThisContext;
    private List<Services_ListValue> ServiceList;

    public AdapterServices(Context context, List<Services_ListValue> serviceList) {
        ThisContext = context;
        ServiceList = serviceList;
    }

    @Override
    public int getCount() {
        return ServiceList.size();
    }

    @Override
    public Object getItem(int i) {
        return ServiceList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ServiceList.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(ThisContext, R.layout.item_services, null);

        TextView Auto = v.findViewById(R.id.Auto);
        TextView Work = v.findViewById(R.id.Work);
        TextView Date = v.findViewById(R.id.Date);
        TextView Cost = v.findViewById(R.id.Cost);

        Services_ListValue service = ServiceList.get(position);
        Auto.setText(service.getCar());
        Work.setText(service.getWork());
        Date.setText(service.getDate_visit());
        Cost.setText(service.getCost());

        return v;
    }
}