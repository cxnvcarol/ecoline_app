package com.ecoline;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;



/**
 * A placeholder fragment containing a simple view.
 */
public class PerfilFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PerfilFragment newInstance(int sectionNumber) {
    	PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PerfilFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_perfil, container, false);
        // // Ejemplo para editar texto desde el mundo:
        //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        //textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
        
        SharedPreferences sharedPref = getActivity().getSharedPreferences(
		        getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        //int defaultValue = getResources().getInteger(R.string.saved_high_score_default);
        //long highScore = sharedPref.getInt(getString(R.string.saved_high_score), defaultValue);
        String username=sharedPref.getString(LoginActivity.USERNAME, getString(R.string.default_username));
        String email=sharedPref.getString(LoginActivity.EMAIL, getString(R.string.default_empty));
        //TODO completar de acuerdo a información de medallas, imagen de perfil (si se incluye), carros denunciados, etc.
        
        TextView un=(TextView)rootView.findViewById(R.id.username);
        un.setText(username);
        TextView em=(TextView)rootView.findViewById(R.id.email);
        em.setText(email);
        
        RatingBar rating= (RatingBar) rootView.findViewById(R.id.ratingUser);
        rating.setRating(4);
        return rootView;
    }
    
    
}
