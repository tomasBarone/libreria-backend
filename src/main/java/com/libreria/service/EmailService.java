package com.libreria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.libreria.model.Orden;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void enviarConfirmacionCompra(String destinatario , Orden orden) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(destinatario);
		message.setSubject("¡Confirmación de Compra - Orden #" + orden.getId() + "!");
		message.setText("Hola!\n\nGracias por tu compra en la librería.\n" +
                "Número de Orden: " + orden.getId() + "\n" +
                "Total pagado: $" + orden.getTotal() + "\n\n" +
                "¡Que disfrutes tu lectura!");

         mailSender.send(message);
	}

}
