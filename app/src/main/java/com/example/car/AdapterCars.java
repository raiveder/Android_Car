package com.example.car;

import android.annotation.SuppressLint;
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

@SuppressLint({"SetTextI18n", "ViewHolder"})
public class AdapterCars extends BaseAdapter {

    private Context ThisContext;
    private List<CarsValue> CarsList;

    public AdapterCars(Context context, List<CarsValue> carsList) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(ThisContext, R.layout.item_cars, null);

        TextView Model = v.findViewById(R.id.Model);
        TextView Mileage = v.findViewById(R.id.Mileage);
        TextView Horsepower = v.findViewById(R.id.Horsepower);
        ImageView Image = v.findViewById(R.id.imageView);

        CarsValue car = CarsList.get(position);
        Model.setText(car.getBrand() + "\n" + car.getModel() + " " + car.getGeneration());
        Mileage.setText(car.getMileage() + " км");
        Horsepower.setText(car.getHorsepower() + " л.с.");

        if (!car.getImage().equals("null")) {
            Image.setImageBitmap(getImage(car.getImage()));
        }

        return v;
    }

    private Bitmap getImage(String image) {
        byte[] bytes = Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}