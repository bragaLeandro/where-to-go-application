package br.com.fiap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripCreationDto {

    @JsonProperty("clima")
    private String clime;
    @JsonProperty("transporte")
    private String transport;
    @JsonProperty("tempoMaximo")
    private Integer maxTime;
    @JsonProperty("custoMaximo")
    private BigDecimal maxCost;
    private String destino;
    @JsonProperty("dataInicio")
    private LocalDate startDate;
    @JsonProperty("dataFim")
    private LocalDate endDate;


}
