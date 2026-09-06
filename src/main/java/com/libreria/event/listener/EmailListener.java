package com.libreria.event.listener;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.libreria.event.OrdenCreadaEvent;
import com.libreria.service.EmailService;

@Component
public class EmailListener {
	
	private final EmailService emailService;
	
	public EmailListener(EmailService emailService) {
		this.emailService = emailService;
	}
	
	
	/**
     * Escucha el evento OrdenCreadaEvent ÚNICAMENTE después de que la transacción de la BD
     * se haya comiteado con éxito (AFTER_COMMIT).
     */
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void procesarEnvioEmail(OrdenCreadaEvent event) {
    	System.out.println("👉 1. [EVENTO RECIBIDO] Transacción comiteada. Intentando enviar mail a: " + event.getEmailCliente());
        try {
            emailService.enviarConfirmacionCompra(event.getOrden());
            System.out.println("✅ 2. [MAIL ENVIADO EXITOSAMENTE]");
        } catch (Exception e) {
        	
            System.err.println("Error al enviar el email de la orden #" + event.getOrden().getId() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

}
