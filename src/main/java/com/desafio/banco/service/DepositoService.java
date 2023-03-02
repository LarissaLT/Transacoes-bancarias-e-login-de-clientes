package com.desafio.banco.service;

import com.desafio.banco.model.Conta;
import com.desafio.banco.request.DepositoRequestDto;
import com.desafio.banco.repository.ContaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DepositoService {

    private final ContaRepository contaRepository;

    public DepositoService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public void depositar(DepositoRequestDto deposito){
        Conta conta = contaRepository.findById(deposito.getContaId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(deposito.getValor() <= 0){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        double recebimento = conta.getSaldo() + deposito.getValor();
        conta.setSaldo(recebimento);

        contaRepository.save(conta);

        String respostaFake = String.format("Valor deposito: %s \n  Saldo atual %s",
                deposito.getValor(), conta.getSaldo());
        System.out.println(respostaFake);

    }


}
