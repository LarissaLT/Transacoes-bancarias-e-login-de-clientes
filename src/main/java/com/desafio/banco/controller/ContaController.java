package com.desafio.banco.controller;

import com.desafio.banco.model.Conta;
import com.desafio.banco.model.ShowTag;
import com.desafio.banco.service.ContaService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView({ShowTag.CadastrarConta.class})
    public Conta cadastrar(@RequestBody @Valid Conta conta){
        return contaService.cadastrar(conta);
    }

    @GetMapping
    @ResponseBody
    @JsonView(ShowTag.Listar.class)
    public List<Conta> listar(){
        return contaService.listar();
    }

    @GetMapping("/{id}")
    @ResponseBody
    @JsonView(ShowTag.Buscar.class)
    public Conta buscar(@PathVariable Long id){
        return contaService.buscar(id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deletar(@PathVariable Long id){
         contaService.deletar(id);
    }
}
