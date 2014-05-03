package com.ecoline;

import java.util.ArrayList;
import java.util.List;

import com.actividades.TopVehiculosContaminantesViewActivity;
import com.entidades.Vehiculo;
import com.entidades.VehiculoAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;



/**
 * A placeholder fragment containing a simple view.
 */
public class ContenidoFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private ListView listaTop ;  
    
    private TopVehiculosContaminantesViewActivity actividad;
    
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ContenidoFragment newInstance(int sectionNumber) {
    	ContenidoFragment fragment = new ContenidoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ContenidoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contenido, container, false);
        //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        //textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
        
		this.listaTop = (ListView) rootView.findViewById(R.id.listVehiculosContaminantes);
		
		List items=new ArrayList();
		items.add(new Vehiculo(R.drawable.bus1,"ASR395"));
		items.add(new Vehiculo(R.drawable.bus2,"OFW852"));
		items.add(new Vehiculo(R.drawable.bus1,"YWK321"));
		items.add(new Vehiculo(R.drawable.bus2,"KSF212"));
		items.add(new Vehiculo(R.drawable.bus1,"WKS239"));
		items.add(new Vehiculo(R.drawable.bus2,"DFG342"));
		items.add(new Vehiculo(R.drawable.bus1,"GLM083"));
		items.add(new Vehiculo(R.drawable.bus2,"GHS723"));
		items.add(new Vehiculo(R.drawable.bus1,"DFA885"));
		items.add(new Vehiculo(R.drawable.bus2,"LLX001"));
		
		this.listaTop.setAdapter(new VehiculoAdapter(rootView.getContext(), items));
//        actividad=new TopVehiculosContaminantesViewActivity();
//        actividad.onCreate(savedInstanceState);
        
        return rootView;
    }
}
