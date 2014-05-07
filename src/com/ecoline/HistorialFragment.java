package com.ecoline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import org.json.simple.JSONArray;

import com.entidades.Denuncia;
import com.entidades.DenunciaAdapter;
import com.entidades.Vehiculo;
import com.entidades.VehiculoAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ListView;
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

	private ListView listaDenuncias;

	private ArrayList<Denuncia> items;

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
        
        listaDenuncias = (ListView) rootView.findViewById(R.id.listOtrasDenuncias);
        
        cargarDenuncias();
        TextView mensaje = (TextView) rootView.findViewById(R.id.lblOtrasDenuncias);
        mensaje.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                if(listaDenuncias.getVisibility()==View.VISIBLE)
                {
                	listaDenuncias.setVisibility(View.GONE);
                }
                else
                {
                	if(items.size()==0)
                	{
                		GetTask gt=new GetTask();
                		gt.execute();
                	}
                	listaDenuncias.setVisibility(View.VISIBLE);
                }
            }
        });
        
        return rootView;
    }
    
    
    private void cargarDenuncias() {
    	items=new ArrayList<Denuncia>();
    	
    	GetTask task=new GetTask();
    	task.execute();
		//items.add(new Denuncia(R.drawable.bus1,"ASR395"));
		
		this.listaDenuncias.setAdapter(new DenunciaAdapter(rootView.getContext(), items));
	}

	/**
     * MÃ©todo que actualiza la pantalla del histortial de una denuncia
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
	
public class GetTask extends AsyncTask<Void, Void, Boolean> {


		

		@Override
		protected Boolean doInBackground(Void... params) {
			if(getDenuncias())
			{
				return true;
			}
			return false;
			
			
		}
		 public Bitmap StringToBitMap(String image){
		        try{
		            byte [] encodeByte=Base64.decode(image,Base64.DEFAULT);
		            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
		            return bitmap;
		          }catch(Exception e){
		           return null;
		          }
		  }
		private boolean getDenuncias() {
		    // Create a new HttpClient and Post Header
		    HttpClient httpclient = new DefaultHttpClient();
		    HttpGet httpget = new HttpGet(LoginActivity.SERVER_URL+"/denuncias");
		    

		    try {
		        // Execute HTTP Post Request
		        HttpResponse response = httpclient.execute(httpget);
		        BufferedReader br=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		        
		        
		        Object obj = JSONValue.parse(br.readLine());
		        JSONArray respArray = (JSONArray)obj;
		        if(respArray==null)
		        {
		        	//problemas con el internet
		        	return false;
		        }
		        for (int i = 0; i < respArray.size(); i++) {
					try {
						JSONObject resp = (JSONObject) respArray.get(i);
						//TODO Get object properties!!
						String id= (String) resp.get("id");
						String placa= (String) resp.get("placa");
						String preFoto=(String) resp.get("photo");
						Denuncia dAdd=new Denuncia(id,StringToBitMap(preFoto),placa);
						items.add(dAdd);
						
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
		        return true;
		        
				

		    } catch (ClientProtocolException e) {
		    	e.printStackTrace();
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }
		    
		    return false;
		
	}

		
		@Override
		protected void onPostExecute(final Boolean success) {
			
		}

		@Override
		protected void onCancelled() {
		}
	}
    
}
