package com.example.beneson.idrugs.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import java.util.List;

/**
 * Created by Beneson on 24/06/2016.
 */
public class FilterAdapterCategoria extends BaseAdapter {

    private Context context;
    private List<String> list;

    public FilterAdapterCategoria(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CheckBox checkBox = new CheckBox(context);
        checkBox.setText(list.get(position));
        checkBox.setChecked(true);

        return checkBox;
    }
}
