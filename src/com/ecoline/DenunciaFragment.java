package com.ecoline;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



/**
 * A placeholder fragment containing a simple view.
 */
public class DenunciaFragment extends Fragment {
	
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
	private static final String USERNAME = "username";
	private static final String PLACA = "placa";
	private static final String LOCATION = "geolocation";
	
	private DenunciaTask denunciaTask = null;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static DenunciaFragment newInstance(int sectionNumber) {
    	DenunciaFragment fragment = new DenunciaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
	private View rootView;
	private TextView txtLatitud;
	private TextView txtLongitud;
	private ImageView imgDenuncia;
	 private File _file;
	 private File _dir;
	private Bitmap foto;
	private LinearLayout layoutImage;
	private LinearLayout layoutImageButtons;

	//private LocationClient mLocationClient;

    public DenunciaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
//    	mLocationClient = new LocationClient(this, this, this);
//    	 mLocationClient.connect();
        rootView = inflater.inflate(R.layout.fragment_denuncia, container, false);
        
        txtLatitud=(TextView) rootView.findViewById(R.id.txtLatidud);
        txtLongitud=(TextView) rootView.findViewById(R.id.txtLongitud);
        Button btnActivar = (Button) rootView.findViewById(R.id.BtnActivarGPS);
        
        imgDenuncia=(ImageView) rootView.findViewById(R.id.imgDenuncia);
        ImageButton btnImagen=(ImageButton) rootView.findViewById(R.id.BtnSubirFoto);
        layoutImage=(LinearLayout) rootView.findViewById(R.id.layoutImage);
        layoutImageButtons=(LinearLayout) rootView.findViewById(R.id.layoutImageButtons);
        layoutImage.setVisibility(LinearLayout.INVISIBLE);
        
        
        btnImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               open();
            }
         });
        btnActivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comenzarLocalizacion();
            }
        });
         LocationManager handle = (LocationManager)rootView.getContext().getSystemService(Context.LOCATION_SERVICE);
        
         
         if (!handle.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
 //            txtLatitud.setText("El GPS esta desactivado");
   //          txtLongitud.setText("El GPS esta desactivado");
        }
         else
         {
        	 //Location loc= mLocationClient.getLastLocation();
             Location loc=handle.getLastKnownLocation(LocationManager.GPS_PROVIDER);
             if(loc!=null)
             {
//            	 txtLatitud.setText(String.valueOf(loc.getLatitude()));
//                 txtLongitud.setText(String.valueOf(loc.getLongitude()));
             }
             else
             {
//            	 txtLatitud.setText("No fue posible obtener la latitud");
//                 txtLongitud.setText("No fue posible obtener la longitud");
             }
             
         }
         
         
         rootView.findViewById(R.id.btnDenunciar).setOnClickListener(
 				new View.OnClickListener() {
 					@Override
 					public void onClick(View view) {
 						denunciaTask = new DenunciaTask();
 						denunciaTask.execute((Void) null);
 						
 					}

 				});
         comenzarLocalizacion();
        return rootView;
    }
    
    private void comenzarLocalizacion()
    {
        //Obtenemos una referencia al LocationManager
    	  LocationManager handle = (LocationManager)rootView.getContext().getSystemService(Context.LOCATION_SERVICE);
    	  
    	  if (!handle.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
              txtLatitud.setText("El GPS esta desactivado");
         }
     
        //Obtenemos la última posición conocida
        Location loc =handle.getLastKnownLocation(LocationManager.GPS_PROVIDER);
     
        //Mostramos la última posición conocida
        mostrarPosicion(loc);
     
        //Nos registramos para recibir actualizaciones de la posición
        LocationListener locListener = new LocationListener() {
   		 
		    public void onLocationChanged(Location location) {
		        mostrarPosicion(location);
		    }
		    
			public void onProviderDisabled(String provider){
				 txtLatitud.setText("El GPS esta desactivado");
		    }
		 
		    public void onProviderEnabled(String provider){
		       //Proveedor encendido
		    }
		 
		    public void onStatusChanged(String provider, int status, Bundle extras){
		        txtLatitud.setText("Provider Status: " + status);
		    }
		};
     
        handle.requestLocationUpdates(
            LocationManager.GPS_PROVIDER, 30000, 0, locListener);
    }
    private void mostrarPosicion(Location location) {
    	if(location!=null)
    	{
    		txtLatitud.setText(""+location.getLatitude());
    		txtLongitud.setText(""+location.getLongitude());
    	}
    	else
    	{
    		txtLatitud.setText("Latitud: (sin_datos)");
    		txtLongitud.setText("Longitud: (sin_datos)");
    	}
    }
    public void open(){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
     }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	       
    	       super.onActivityResult(requestCode, resultCode, data);

    	       layoutImage.setVisibility(LinearLayout.VISIBLE);
    	       layoutImageButtons.setVisibility(LinearLayout.INVISIBLE);
    	       foto = (Bitmap) data.getExtras().get("data");
    	       imgDenuncia.setImageBitmap(foto);
        }
    
    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] arr=baos.toByteArray();
        String result=Base64.encodeToString(arr, Base64.DEFAULT);
        
        return result;
  }
    public Bitmap StringToBitMap(String image){
        try{
            byte [] encodeByte=Base64.decode(image,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
          }catch(Exception e){
            e.getMessage();
           return null;
          }
  }
    public class DenunciaTask extends AsyncTask<Void, Void, Boolean> {


		

		private EditText txtPlaca;
		private EditText txtDireccion;

		@Override
		protected Boolean doInBackground(Void... params) {
			if(enviarDenuncia())
			{
				//goToHomePage();
				return true;
			}
			return false;
			
			
		}
		private boolean enviarDenuncia()
		{
			txtPlaca=(EditText)rootView.findViewById(R.id.txtPlaca);
	    	txtDireccion=(EditText)rootView.findViewById(R.id.txtDireccion);
	    	//txtEdit.setError(null);
			String placa = txtPlaca.getText().toString();

			return denunciar(placa, txtLatitud+","+txtLongitud);
		}
		private boolean denunciar(String placa,String location) {
		    // Create a new HttpClient and Post Header
		    HttpClient httpclient = new DefaultHttpClient();
		    HttpPost httppost = new HttpPost(LoginActivity.SERVER_URL+"/denuncia");
		    
		    SharedPreferences sharedPref = getActivity().getSharedPreferences(
			        getString(R.string.preference_file_key), Context.MODE_PRIVATE);
	        //int defaultValue = getResources().getInteger(R.string.saved_high_score_default);
	        //long highScore = sharedPref.getInt(getString(R.string.saved_high_score), defaultValue);
	        String username=sharedPref.getString(LoginActivity.USERNAME, getString(R.string.default_username));

		    try {
		        // Add your data
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		        nameValuePairs.add(new BasicNameValuePair(USERNAME, username));
		        nameValuePairs.add(new BasicNameValuePair(PLACA, placa));
		        nameValuePairs.add(new BasicNameValuePair(LOCATION, location));		
		        //TODO
		        
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		        // Execute HTTP Post Request
		        HttpResponse response = httpclient.execute(httppost);
		        BufferedReader br=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		        
		        Object obj = JSONValue.parse(br.readLine());
		        JSONObject resp = (JSONObject)obj;
		        boolean success=((Boolean) resp.get("success")).booleanValue();
				@SuppressWarnings("unused")
				String msg=(String) resp.get("message");
				
				
				
//				if(!success)
//				{
//					((TextView)rootView.findViewById(R.id.infoPost)).setText("No se pudo enviar la denuncia");
//					return false;
//				}
//				else
//				{						
//					((TextView)rootView.findViewById(R.id.infoPost)).setText("Denuncia enviada exitosamente");					
//				}
		        
		        

		    } catch (ClientProtocolException e) {
		    	e.printStackTrace();
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }
		    
		    return true;
		
	}

		
		@Override
		protected void onPostExecute(final Boolean success) {
			txtPlaca.setText("");
			txtDireccion.setText("");
			changeTab(Main.TAB_HISTORIAL);
		}

		@Override
		protected void onCancelled() {
		}
	}
    public void changeTab(int pos)
    {
    	((ActionBarActivity)getActivity()).getSupportActionBar().setSelectedNavigationItem(pos);
    }

}

