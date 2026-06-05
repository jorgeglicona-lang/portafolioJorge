package com.jorge.portafoliojorge.controller;

import com.jorge.portafoliojorge.model.ProyectosGitDTO;
import com.jorge.portafoliojorge.service.ServiceGit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GitController {
    @Autowired
    private ServiceGit gitS;

    @GetMapping("/")
    public String MostrarP(Model taP){
        List<ProyectosGitDTO> Lis = gitS.obtenerProyectos();
        taP.addAttribute("proyectos",Lis);

        return "index";
    }
}
