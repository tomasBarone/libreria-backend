package com.libreria.event;

import com.libreria.model.Orden;

/**
 * Evento inmutable que transporta la orden guardada y el email del comprador.
 */

public class OrdenCreadaEvent {
	
	private final Orden orden;
	private final String emailCliente;
	
	public OrdenCreadaEvent(Orden orden, String emailCliente) {
		super();
		this.orden = orden;
		this.emailCliente = emailCliente;
	}

	public Orden getOrden() {
		return orden;
	}

	public String getEmailCliente() {
		return emailCliente;
	}
	
	

}
