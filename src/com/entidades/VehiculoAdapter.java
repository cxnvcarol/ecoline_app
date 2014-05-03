package com.entidades;

import java.util.List;

import com.ecoline.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class VehiculoAdapter extends BaseAdapter{

	private Context context;
    private List<Vehiculo> listaTOP;
 
    public VehiculoAdapter(Context context, List<Vehiculo> items) {
        this.context = context;
        this.listaTOP = items;
    }
 
    @Override
    public int getCount() {
        return this.listaTOP.size();
    }
 
    @Override
    public Object getItem(int position) {
        return this.listaTOP.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
 
        View rowView = convertView;
 
        if (convertView == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_vehiculo_contaminante, parent, false);
        }
 
        // Set data into the view.
        ImageView ivItem = (ImageView) rowView.findViewById(R.id.ivItem);
        TextView tvTitle = (TextView) rowView.findViewById(R.id.tvTitle);
        TextView posicion = (TextView) rowView.findViewById(R.id.pos);
 
        Vehiculo item = this.listaTOP.get(position);
        posicion.setText(((position)+1)+"");
        tvTitle.setText(item.getPlaca());
        ivItem.setImageResource(item.getImage());
 
        return rowView;
    }
}
