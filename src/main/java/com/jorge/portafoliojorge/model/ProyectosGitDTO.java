package com.jorge.portafoliojorge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProyectosGitDTO (
    String name,
    String description,
    @JsonProperty("html_url")
    String url,
    String language,
    boolean fork,
    List<String> topics
    ){}