package br.com.fiap.dto;

import lombok.Data;

@Data
public class UserReqDto {
    private String name;
    private String nickname;
    private String email;
    private String password;
    private String cpf;
    private String cellphone;
    private String description;
}
