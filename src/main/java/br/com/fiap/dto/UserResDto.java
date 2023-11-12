package br.com.fiap.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserResDto {
    private String id;
    private String name;
    private String nickname;
    private String email;
    private String cpf;
    private String cellphone;
    private String description;
    @JsonIgnore
    private String password;
}
