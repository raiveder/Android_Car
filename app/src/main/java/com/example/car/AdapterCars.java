package com.example.car;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterCars extends BaseAdapter {

    private Context ThisContext;
    private List<Cars> CarsList;

    public AdapterCars(Context context, List<Cars> carsList) {
        ThisContext = context;
        CarsList = carsList;
    }

    @Override
    public int getCount() {
        return CarsList.size();
    }

    @Override
    public Object getItem(int i) {
        return CarsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return CarsList.get(i).getId();
    }

    private Bitmap getUserImage(String image) {
        byte[] bytes = Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(ThisContext, R.layout.item_cars, null);

        TextView Model = v.findViewById(R.id.Model);
        TextView Mileage = v.findViewById(R.id.Mileage);
        TextView Horsepower = v.findViewById(R.id.Horsepower);
        ImageView Image = v.findViewById(R.id.imageView);

        Cars cars = CarsList.get(position);
        Model.setText(cars.getModel());
        Mileage.setText(Integer.toString(cars.getMileage()));
        Horsepower.setText(Integer.toString(cars.getHorsepower()));

        if (!cars.getImage().equals("null")) {
            Image.setImageBitmap(getUserImage(cars.getImage())); // ?
        }

        return v;
    }
}