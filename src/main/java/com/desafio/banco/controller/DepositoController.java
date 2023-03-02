package com.desafio.banco.controller;

import com.desafio.banco.request.DepositoRequestDto;
import com.desafio.banco.model.ShowTag;
import com.desafio.banco.repository.ContaRepository;
import com.desafio.banco.service.DepositoService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/depositos")
public class DepositoController {

    private final ContaRepository contaRepository;
    private final DepositoService depositoService;

    public DepositoController(ContaRepository contaRepository, DepositoService depositoService) {
        this.contaRepository = contaRepository;
        this.depositoService = depositoService;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @JsonView(ShowTag.Transferir.class)
    public void depositar(@RequestBody DepositoRequestDto deposito) {
        depositoService.depositar(deposito);
    }
}
