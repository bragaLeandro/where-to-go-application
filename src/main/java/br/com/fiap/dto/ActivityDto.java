package br.com.fiap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDto {

    @JsonProperty("nome")
    private String name;
    @JsonProperty("duracao")
    private String duration;

}
