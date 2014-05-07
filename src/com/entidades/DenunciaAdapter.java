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

public class DenunciaAdapter extends BaseAdapter{

	private Context context;
    private List<Denuncia> listaDenuncias;
 
    public DenunciaAdapter(Context context, List<Denuncia> items) {
        this.context = context;
        this.listaDenuncias = items;
    }
 
    @Override
    public int getCount() {
        return this.listaDenuncias.size();
    }
 
    @Override
    public Object getItem(int position) {
        return this.listaDenuncias.get(position);
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
            rowView = inflater.inflate(R.layout.list_denuncia, parent, false);
        }
 
        // Set data into the view.
        ImageView ivItem = (ImageView) rowView.findViewById(R.id.idItem);
        TextView tvTitle = (TextView) rowView.findViewById(R.id.tdTitle);
        TextView posicion = (TextView) rowView.findViewById(R.id.posDen);
 
        Denuncia item = this.listaDenuncias.get(position);
        posicion.setText(((position)+1)+"");
        tvTitle.setText(item.getPlaca());
        //ivItem.setImageResource(item.getImage());
        if(item.getImage()!=null)
        	ivItem.setImageBitmap(item.getImage());
 
        return rowView;
    }
}
