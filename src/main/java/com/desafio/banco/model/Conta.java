package com.desafio.banco.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Data
@Table(name = "conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ShowTag.CadastrarConta.class, ShowTag.Listar.class, ShowTag.Buscar.class, ShowTag.Transferir.class})
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonView({ShowTag.Cadastrar.class, ShowTag.Listar.class, ShowTag.Buscar.class})
    private Cliente cliente;

    @NotNull
    @Column(name = "saldo")
    @JsonView({ShowTag.Buscar.class, ShowTag.CadastrarConta.class, ShowTag.Buscar.class})
    private double saldo;

}
