package com.jorge.portafoliojorge.service;

import com.jorge.portafoliojorge.model.ProyectosGitDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceGit {
    public List<ProyectosGitDTO> obtenerProyectos() {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api.github.com/users/jorgeglicona-lang/repos";
        ProyectosGitDTO[] pA = restTemplate.getForObject(url, ProyectosGitDTO[].class);
        assert pA != null;

        List<ProyectosGitDTO> tlp = Arrays.asList(pA);

        return tlp.stream()
                .filter(p -> !p.isFork())
                .filter(p -> p.getTopics() != null && p.getTopics().contains("destacado"))
                .collect(Collectors.toList());
    }
}
