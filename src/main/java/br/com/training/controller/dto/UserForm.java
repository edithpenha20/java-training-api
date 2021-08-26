package br.com.training.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Objects;

public class UserForm {


    private String name;

    @Email
    private String email;

    @CPF
    private String cpf;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    public UserForm() {
    }

    public UserForm(String name, String email, String cpf, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserForm userForm = (UserForm) o;
        return Objects.equals(name, userForm.name) && Objects.equals(email, userForm.email) && Objects.equals(cpf, userForm.cpf) && Objects.equals(birthDate, userForm.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, cpf, birthDate);
    }
}
