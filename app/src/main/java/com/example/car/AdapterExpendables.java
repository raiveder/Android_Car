package com.example.car;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

@SuppressLint({"ViewHolder", "UseCompatLoadingForDrawables"})
public class AdapterExpendables extends BaseAdapter {

    private Context ThisContext;
    private List<Expendables> ExpendablesList;
    private int[] CountExpendables;

    public AdapterExpendables(Context context, List<Expendables> expendablesList, int[] countExpendables) {

        ThisContext = context;
        ExpendablesList = expendablesList;
        CountExpendables = countExpendables;
    }

    @Override
    public int getCount() {
        return ExpendablesList.size();
    }

    @Override
    public Object getItem(int position) {
        return ExpendablesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ExpendablesList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(ThisContext, R.layout.item_details, null);

        TextView tvDetail = v.findViewById(R.id.tvDetail);
        tvDetail.setText(ExpendablesList.get(position).getExpendable());

        if (CountExpendables != null) {
            if (CountExpendables[position] != 0) {

                TextView tv = v.findViewById(R.id.tvCount);
                tv.setText(String.valueOf(CountExpendables[position]));
                v.setBackground(ThisContext.getDrawable(R.drawable.selected_item));
            }
        }

        return v;
    }
}