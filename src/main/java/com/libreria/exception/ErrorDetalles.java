package com.libreria.exception;

public class ErrorDetalles {
	
	private String mensaje;
	private int codigo;
	
	public ErrorDetalles(String mensaje , int codigo) {
		this.mensaje = mensaje;
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	

}
