package com.desafio.banco.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Cliente implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ShowTag.Cadastrar.class, ShowTag.CadastrarConta.class, ShowTag.Buscar.class, ShowTag.Listar.class, ShowTag.Transferir.class})
    private Long id;

    @Column(name = "cpf_number")
    @JsonView({ShowTag.Cadastrar.class, ShowTag.CadastrarConta.class, ShowTag.Listar.class, ShowTag.Buscar.class, ShowTag.Atualizar.class})
    @NotNull
    @NotBlank
    @NotEmpty
    @CPF
    private String cpfNumber;

    @NotNull(message = "A senha não pode ser nula")
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$",
            message = "A senha deve conter pelo menos um número, uma letra maiúscula e uma letra minúscula")
    private String senha;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "cliente_id")
    private List<Conta> contas = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return cpfNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
