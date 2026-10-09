package com.jorge.portafoliojorge.controller;

import com.jorge.portafoliojorge.model.ContactoDTO;
import com.jorge.portafoliojorge.repository.mensajeRepo;
import com.jorge.portafoliojorge.service.EmailResendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
public class ContactController {

    @Autowired
    private mensajeRepo repositorio;

    @Autowired
    private EmailResendService emailService;

    @PostMapping("/enviar-mensaje")
    public String procesarFormulario(@ModelAttribute ContactoDTO contactoDTO, RedirectAttributes redirectAttributes) {

        repositorio.save(contactoDTO);

        CompletableFuture.runAsync(() -> {
            try {
                emailService.enviarCorreo(contactoDTO);
            } catch (Exception e) {
                System.out.println("Error asíncrono al enviar correo: " + e.getMessage());
            }
        });
        redirectAttributes.addFlashAttribute("mensajeExito",
                "¡Mensaje enviado con éxito! Me pondré en contacto muy pronto.");
        return "redirect:/#contactoDTO";
    }

    @GetMapping("/ver-mensajes-secretos")
    @ResponseBody
    public List<ContactoDTO> verMensajes() {
        return repositorio.findAll();
    }
}