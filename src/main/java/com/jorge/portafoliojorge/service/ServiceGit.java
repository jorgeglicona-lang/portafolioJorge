package com.jorge.portafoliojorge.service;

import com.jorge.portafoliojorge.model.ProyectosGitDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
public class ServiceGit {

    private static final Logger log = LoggerFactory.getLogger(ServiceGit.class);
    private final RestTemplate restTemplate;

    public ServiceGit(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ProyectosGitDTO> obtenerProyectos() {
        String url = "https://api.github.com/users/jorgeglicona-lang/repos";
        try {
            ProyectosGitDTO[] pA = restTemplate.getForObject(url, ProyectosGitDTO[].class);
            if (pA == null) {
                return List.of();
            }

            return Arrays.stream(pA)
                    .filter(p -> !p.fork())
                    .filter(p -> p.topics() != null && p.topics().contains("destacado"))
                    .toList();
        } catch (RestClientException e) {
            log.error("Error al consultar la API de GitHub: {}", e.getMessage(), e);
            return List.of();
        }
    }
}