package br.com.fiap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class TripDto {

    @JsonProperty("destino")
    private String destination;
    @JsonProperty("pais")
    private String country;
    @JsonProperty("atividadesPorDia")
    private List<DayActivitiesDto> activities;
    @JsonProperty("hospedagem")
    private String accommodation;
    @JsonProperty("duracaoViagem")
    private String travelDuration;
    @JsonProperty("custo")
    private BigDecimal cost;
}
