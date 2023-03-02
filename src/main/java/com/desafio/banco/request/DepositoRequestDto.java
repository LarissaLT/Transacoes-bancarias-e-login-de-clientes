package com.desafio.banco.request;

import lombok.Data;

@Data
public class DepositoRequestDto {

    private Long contaId;
    private double valor;
}
