package com.libreria.service;

import com.libreria.model.Orden;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

	private final Resend resend;
    private final TemplateEngine templateEngine;

    public EmailService(@Value("${resend.api.key}") String apiKey, TemplateEngine templateEngine) {
        this.resend = new Resend(apiKey);
        this.templateEngine = templateEngine;
    }

    public void enviarConfirmacionCompra(Orden orden) {
        // 1. Preparar el contexto de Thymeleaf con los datos de la orden
        Context context = new Context();
        context.setVariable("orden", orden);
        context.setVariable("detalles", orden.getDetalles()); // Asumiendo que tu entidad Orden tiene getDetalles()

        // 2. Procesar la plantilla HTML localizada en src/main/resources/templates/mail/confirmacion-compra.html
        String htmlContent = templateEngine.process("mail/confirmacion-compra", context);

        // 3. Forzar el destinatario a tu correo registrado en Resend (Sandbox)
        // En producción real con dominio verificado se usaría: orden.getUsuario().getEmail()
        String destinatarioPruebas = "tomas.barone.dev@gmail.com";

        // 4. Armar los parámetros del email
        CreateEmailOptions params = CreateEmailOptions.builder()
            .from("onboarding@resend.dev") // Remitente por defecto en Sandbox
            .to(destinatarioPruebas)
            .subject("Confirmación de Compra #" + orden.getId())
            .html(htmlContent)
            .build();

        // 5. Realizar el envío mediante el SDK de Resend
        try {
            resend.emails().send(params);
        } catch (ResendException e) {
            throw new RuntimeException("Error al enviar email mediante Resend: " + e.getMessage(), e);
        }
    }
}