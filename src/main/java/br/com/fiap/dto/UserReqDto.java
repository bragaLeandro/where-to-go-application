package br.com.fiap.dto;

import lombok.Data;

@Data
public class UserReqDto {
    private String name;
    private String email;
    private String password;
}
