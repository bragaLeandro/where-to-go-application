package br.com.fiap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
public class TripDto {

    private Long id;
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
    @JsonProperty("dataInicio")
    private LocalDate startDate;
    @JsonProperty("dataFim")
    private LocalDate endDate;

}
