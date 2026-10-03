package com.jorge.portafoliojorge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProyectosGitDTO {

    private String name;

    private String description;

    @JsonProperty("html_url")
    private String url;

    private String language;
}
