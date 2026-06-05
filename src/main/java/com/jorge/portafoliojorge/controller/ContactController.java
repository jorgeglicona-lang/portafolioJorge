package com.jorge.portafoliojorge.controller;

import com.jorge.portafoliojorge.model.ContactoDTO;
import com.jorge.portafoliojorge.repository.mensajeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List
@Controller
public class ContactController {
    @Autowired
    private mensajeRepo repositorio;
    @Autowired
    private JavaMailSender mailSender;
    @PostMapping("/enviar-mensaje")

    public String procesarFormulario(@ModelAttribute ContactoDTO contactoDTO, RedirectAttributes redirectAttributes){

        repositorio.save(contactoDTO);
        enviarCorreo(contactoDTO);
        redirectAttributes.addFlashAttribute("mensajeExito",
                "¡Mensaje enviado con éxito! Me pondré en contacto muy pronto.");
        return "redirect:/#contacto";
    }

    private void enviarCorreo(ContactoDTO contactoDTO){
        SimpleMailMessage mail=new SimpleMailMessage();

        mail.setTo("jorgeglicona@gmail.com");
        mail.setSubject("Mensaje desde Portafolio:" + contactoDTO.getNombre());
        mail.setText("Jefe, tiene un nuevo mensaje de contacto, lo recibio desde el repositorio.\n\n"
                + "👤 Nombre:" + contactoDTO.getNombre()+ "\n"
                + "📧 Correo de contacto:" + contactoDTO.getCorreo()+ "\n\n"
                + "💬 Mensaje:\n" + contactoDTO.getMensaje());

        mailSender.send(mail);
    }

        @GetMapping("/ver-mensajes-secretos")
    @org.springframework.web.bind.annotation.ResponseBody
    public java.util.List<ContactoDTO> verMensajes() {
        // Esto buscará todos los mensajes en la BD y los mostrará en la pantalla
        return repositorio.findAll();
    }
    
}
