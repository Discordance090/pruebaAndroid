package com.Holass.chuteapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import org.w3c.dom.Text;

public class StoreBaseAdapter extends BaseAdapter {
    Context context;
    String[] itemList;
    int[] listImages;
    int[] listPrices;
    LayoutInflater inflater;

    public StoreBaseAdapter(Context context, String[] itemList, int[] prices){
        this.context = context;
        this.itemList = itemList;
        this.listPrices = prices;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return itemList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        convertView = inflater.inflate(R.layout.activity_store_view, null);
        TextView txtTextDescription = convertView.findViewById(R.id.textDesctription);
        TextView txtPrice = convertView.findViewById(R.id.textPrice);
        //ImageView itemImage = convertView.findViewById(R.id.imageIcon);
        txtTextDescription.setText(itemList[i]);
        txtPrice.setText(listPrices[i]);
        //itemImage.setImageResource(listImages[i]);

        return null;
    }
}