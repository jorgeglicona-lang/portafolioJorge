package com.jorge.portafoliojorge.service;

import com.jorge.portafoliojorge.model.ContactoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailResendService {

    @Value("${RESEND_API_KEY:}")
    private String resendApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public void enviarCorreo(ContactoDTO contactoDTO) {
        if (resendApiKey == null || resendApiKey.isBlank()) {
            System.out.println("ADVERTENCIA: RESEND_API_KEY no configurada.");
            return;
        }

        String url = "https://api.resend.com/emails";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(resendApiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("from", "Portafolio <onboarding@resend.dev>");
        body.put("to", Collections.singletonList("jorgeglicona@gmail.com"));
        body.put("subject", "Mensaje desde Portafolio: " + contactoDTO.getNombre());
        body.put("text", "Jefe, tiene un nuevo mensaje de contacto desde el portafolio.\n\n"
                + "👤 Nombre: " + contactoDTO.getNombre() + "\n"
                + "📧 Correo: " + contactoDTO.getCorreo() + "\n\n"
                + "💬 Mensaje:\n" + contactoDTO.getMensaje());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            restTemplate.postForEntity(url, request, String.class);
            System.out.println("Correo enviado exitosamente vía Resend HTTP.");
        } catch (Exception e) {
            System.out.println("Error al enviar con Resend: " + e.getMessage());
        }
    }
}