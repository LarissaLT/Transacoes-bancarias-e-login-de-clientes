package com.desafio.banco.request;

import com.desafio.banco.model.ShowTag;
import com.desafio.banco.model.TipoTransferencia;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class TransferenciaRequestDto {

    private Long emissorId;
    private Long recebedorId;
    private double valor;
    private TipoTransferencia tipoTransferencia;

}
