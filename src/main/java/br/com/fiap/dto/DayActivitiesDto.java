package br.com.fiap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayActivitiesDto {

    @JsonProperty("dia")
    private int day;
    @JsonProperty("atividades")
    private List<ActivityDto> activities;
}
