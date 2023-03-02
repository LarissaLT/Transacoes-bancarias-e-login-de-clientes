package com.desafio.banco.service;

import com.desafio.banco.model.Cliente;
import com.desafio.banco.model.Conta;
import com.desafio.banco.repository.ClienteRepository;
import com.desafio.banco.repository.ContaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;

    public ContaService(ContaRepository contaRepository,
                        ClienteRepository clienteRepository) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }

    public Conta cadastrar(Conta contaImput){
        Conta conta = new Conta();
        BeanUtils.copyProperties(contaImput, conta,  "senha");
        return contaRepository.save(contaImput);
    }

    public List<Conta> listar(){
        return contaRepository.findAll();
    }

    public Conta buscar(Long id) {
        Optional<Conta> op = contaRepository.findById(id);
        if(op.isPresent()){
            Conta conta = op.get();
            return conta;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public Conta atualizar(Conta conta, Long id){
        if(!contaRepository.existsById(id)){
            throw new ResponseStatusException((HttpStatus.NOT_FOUND));
        }
        return conta;
    }

    public void deletar(Long id){
        Conta conta = contaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));

         if(conta.getSaldo() > 0) {
             throw new ResponseStatusException(HttpStatus.CONFLICT);
         } contaRepository.deleteById(id);
    }

}
