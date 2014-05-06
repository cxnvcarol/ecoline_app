package com.ecoline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.actividades.TopVehiculosContaminantesViewActivity;
import com.entidades.Vehiculo;
import com.entidades.VehiculoAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
    
    private TextView textv;
    
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
        
        TextView top = (TextView) rootView.findViewById(R.id.lblsaludo1);
        
        textv = (TextView) rootView.findViewById(R.id.txtProbando);
        
        top.setOnClickListener(new OnClickListener() {

                    public void onClick(View v) {
                    	
                    	
                        textv.setText("Text Has Been Changed");
                        BufferedReader in = null;
                        String data = null;

                        try{
                               HttpClient httpclient = new DefaultHttpClient();

                               HttpGet request = new HttpGet();
                               URI website = new URI(LoginActivity.SERVER_URL+"/denuncias");
                               request.setURI(website);
                               HttpResponse response = httpclient.execute(request);
                               in = new BufferedReader(new InputStreamReader(
                                       response.getEntity().getContent()));

                               // NEW CODE
                               String line = in.readLine();
                               textv.append(" First line: " + line);
                               // END OF NEW CODE
                               int i=1;
                               while(line!=null)
                               {
                            	   i++;
                            	   line=in.readLine();
                               }
                               textv.append("Numero=" + i);

                               textv.append(" Connected ");
                           }catch(Exception e){
                               Log.e("log_tag", "Error in http connection "+e.toString());
                           }
                    	
                        if(listaTop.getVisibility()==View.VISIBLE)
                        {
                        	listaTop.setVisibility(View.GONE);
                        }
                        else
                        {
                        	listaTop.setVisibility(View.VISIBLE);
                        }
                    }
                });
        
        TextView mensaje = (TextView) rootView.findViewById(R.id.lblsaludo2);
        mensaje.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                if(listaTop.getVisibility()==View.VISIBLE)
                {
                	listaTop.setVisibility(View.GONE);
                }
                else
                {
                	listaTop.setVisibility(View.VISIBLE);
                }
            }
        });
        
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
