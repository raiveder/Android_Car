package com.example.car;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

@SuppressLint({"ViewHolder", "UseCompatLoadingForDrawables"})
public class AdapterDetails extends BaseAdapter {

    private Context ThisContext;
    private List<Details> DetailsList;
    private int[] CountDetails;

    public AdapterDetails(Context context, List<Details> detailsList, int[] countDetails) {
        ThisContext = context;
        DetailsList = detailsList;
        CountDetails = countDetails;
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
        tvDetail.setText(DetailsList.get(position).getDetail());

        if (CountDetails != null) {
            if (CountDetails[position] != 0) {

                TextView tv = v.findViewById(R.id.tvCount);
                tv.setText(String.valueOf(CountDetails[position]));
                v.setBackground(ThisContext.getDrawable(R.drawable.selected_item));
            }
        }

        return v;
    }
}