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
    @NotEmpty(message = "clime cannot be empty")
    @NotNull(message = "clime cannot be null")
    private String clime;
    @JsonProperty("transporte")
    @NotEmpty(message = "transport cannot be empty")
    @NotNull(message = "transport cannot be null")
    private String transport;
    @JsonProperty("tempoMaximo")
    @NotEmpty(message = "maxTime cannot be empty")
    @NotNull(message = "maxTime cannot be null")
    private Integer maxTime;
    @JsonProperty("custoMaximo")
    @NotEmpty(message = "maxCost cannot be empty")
    @NotNull(message = "maxCost cannot be null")
    private BigDecimal maxCost;

}
