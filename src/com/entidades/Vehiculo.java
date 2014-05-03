package com.entidades;

public class Vehiculo {

    //----------------------------------------------
    //	Atributos
    //----------------------------------------------
	
	private int image;
    private String placa;
    
    //----------------------------------------------
    //	Constructor
    //----------------------------------------------
    
    public Vehiculo() {
        super();
    }
 
    public Vehiculo(int image, String placa) {
        super();
        this.image = image;
        this.placa = placa;
    }

    //----------------------------------------------
    //	Getters and Setters
    //----------------------------------------------
	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
    
    
}
