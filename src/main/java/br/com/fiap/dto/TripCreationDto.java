package br.com.fiap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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

}
