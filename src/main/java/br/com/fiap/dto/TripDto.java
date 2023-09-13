package br.com.fiap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@RequiredArgsConstructor
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
