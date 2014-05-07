package com.entidades;

import android.graphics.Bitmap;

public class Denuncia {

    //----------------------------------------------
    //	Atributos
    //----------------------------------------------
	
	private Bitmap image;
    private String placa;
    private String idDenuncia;
    //----------------------------------------------
    //	Constructor
    //----------------------------------------------
    
    public Denuncia() {
        super();
    }
 
    public Denuncia(String idDenuncia,Bitmap image, String placa) {
        super();
        this.image = image;
        this.placa = placa;
        this.idDenuncia=idDenuncia;
    }

    //----------------------------------------------
    //	Getters and Setters
    //----------------------------------------------
	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
    
    
}
