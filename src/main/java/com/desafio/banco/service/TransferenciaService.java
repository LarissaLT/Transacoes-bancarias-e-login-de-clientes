package com.desafio.banco.service;

import com.desafio.banco.model.Conta;
import com.desafio.banco.model.TipoTransferencia;
import com.desafio.banco.request.TransferenciaRequestDto;
import com.desafio.banco.repository.ContaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class TransferenciaService {

    private final ContaRepository contaRepository;

    public TransferenciaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public void transferir(TransferenciaRequestDto transferencia){
        Conta emissor = contaRepository.findById(transferencia.getEmissorId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        Conta recebedor = contaRepository.findById(transferencia.getRecebedorId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(transferencia.getEmissorId() == transferencia.getRecebedorId()){
            throw new ResponseStatusException((HttpStatus.CONFLICT));
        }

        if(transferencia.getValor() <= 0){
            throw new ResponseStatusException((HttpStatus.CONFLICT));
        }

        if(transferencia.getValor() > emissor.getSaldo()){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        if(TipoTransferencia.PIX.equals(transferencia.getTipoTransferencia())){
            if(transferencia.getValor() > 5000){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Transações do tipo 'PIX' só podem ser realizadas com valores até R$ 5.000,00");
            }
        }

        if(TipoTransferencia.TED.equals(transferencia.getTipoTransferencia())){
            if(transferencia.getValor() <= 5000 ||
                    transferencia.getValor() > 10000){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Transações do tipo 'TED' só podem ser realizadas com valores maiores que R$ 5.000,00 e menores que R$ 10.000,00");
            }
        }

        if(TipoTransferencia.DOC.equals(transferencia.getTipoTransferencia())){
            if(transferencia.getValor() <= 10000){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Transações do tipo 'DOC' só podem ser realizadas com valores acima de R$ 10.000,00");
            }
        }

        double pagamento = emissor.getSaldo() - transferencia.getValor();
        emissor.setSaldo(pagamento);

        double recebimento = recebedor.getSaldo() + transferencia.getValor();
        recebedor.setSaldo(recebimento);


        contaRepository.save(emissor);
        contaRepository.save(recebedor);

//        nao mexer daqui pra baixo

        String respostaFake = String.format
                ("Valor tranferencia: %s \n Saldo de origem: %s \n Saldo de destino %s \n Tipo de tranferencia: %s",
                transferencia.getValor(), emissor.getSaldo(), recebedor.getSaldo(), transferencia.getTipoTransferencia());
        System.out.println(respostaFake);

    }


}
