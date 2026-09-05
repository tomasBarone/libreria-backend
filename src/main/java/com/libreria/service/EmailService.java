package com.libreria.service;

import com.libreria.model.Orden;
import com.resend.Resend;
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

    public void enviarConfirmacionCompra(String destinatario, Orden orden) {
        // 1. Preparamos las variables para la plantilla HTML
        Context context = new Context();
        context.setVariable("orden", orden);

        // 2. Renderizamos el archivo HTML
        String contenidoHtml = templateEngine.process("mail/confirmacion-compra", context);

        // 3. Construimos los parámetros usando CreateEmailOptions
        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Libreria Shamata <onboarding@resend.dev>")
                .to(destinatario)
                .subject("¡Confirmación de Compra - Orden #" + orden.getId() + "!")
                .html(contenidoHtml)
                .build();

        try {
            CreateEmailResponse data = resend.emails().send(params);
            System.out.println("Email enviado con éxito. ID: " + data.getId());
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar email mediante Resend: " + e.getMessage(), e);
        }
    }
}