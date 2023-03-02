package com.desafio.banco.controller;

import com.desafio.banco.model.ShowTag;
import com.desafio.banco.request.TransferenciaRequestDto;
import com.desafio.banco.repository.ContaRepository;
import com.desafio.banco.service.TransferenciaService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final ContaRepository contaRepository;
    private final TransferenciaService transferenciaService;

    public TransferenciaController(ContaRepository contaRepository, TransferenciaService transferenciaService) {
        this.contaRepository = contaRepository;
        this.transferenciaService = transferenciaService;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @JsonView(ShowTag.Transferir.class)
    public void transferir(@RequestBody TransferenciaRequestDto transferencia) {
        transferenciaService.transferir(transferencia);
    }

}
