package com.ecoline;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



/**
 * A placeholder fragment containing a simple view.
 */
public class HistorialFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private View rootView;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HistorialFragment newInstance(int sectionNumber) {
    	HistorialFragment fragment = new HistorialFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public HistorialFragment() {
    	
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_historial, container, false);
        
       
        return rootView;
    }
    
    
    /**
     * Método que actualiza la pantalla del histortial de una denuncia
     */
	public void abrirDenuncia()
	{
		TextView text = (TextView)rootView.findViewById(R.id.descripcion1);
		if(text.getText().equals(""))
		{			
			text.setText("Esta denuncia se realizó el dia 14 del mes de abril del año 2014, donde un bus parqueado se atrevió a contaminar nuestro ambiente; quemenlos a todos!");			
		}
		else
		{
			text.setText("");
		}
	}
    
}
