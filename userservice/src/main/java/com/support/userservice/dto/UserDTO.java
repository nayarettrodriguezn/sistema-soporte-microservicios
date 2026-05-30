package com.support.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDTO {

    @NotBlank(message = "El nombre del usuario es obligatorio")
    private String name;

    @Email(message = "El formato del correo electrónico no es válido")
    @NotBlank(message = "El correo es obligatorio")
    private String email;

    public UserDTO() {}
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}