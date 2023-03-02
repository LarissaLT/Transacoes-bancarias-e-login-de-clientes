package com.desafio.banco.controller;

import com.desafio.banco.model.Cliente;
import com.desafio.banco.model.ShowTag;
import com.desafio.banco.security.DadosTokenJWT;
import com.desafio.banco.security.TokenService;
import com.desafio.banco.security.DadosAutenticacao;
import com.desafio.banco.service.ClienteService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final AuthenticationManager manager;
    private final TokenService tokenService;

    public ClienteController(ClienteService clienteService, AuthenticationManager manager, TokenService tokenService) {
        this.clienteService = clienteService;
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.cpfNumber(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Cliente) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView({ShowTag.Cadastrar.class})
    private Cliente cadastrar(@RequestBody Cliente cliente) {
        return clienteService.cadastrar(cliente);
    }

    @GetMapping
    @ResponseBody
    @JsonView(ShowTag.Listar.class)
    private List<Cliente> listar(){
        return clienteService.listar();
    }

    @GetMapping("/{id}")
    @ResponseBody
    @JsonView(ShowTag.Buscar.class)
    private Cliente buscar(@PathVariable Long id){
        return clienteService.buscar(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    private Cliente atualizar(@RequestBody Cliente cliente, @PathVariable Long id){
        return clienteService.atualizar(cliente,id);
    }

}
