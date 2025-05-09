package com.example.loja_virtual_bff.api.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompraRequestDTO {

    private String produto;
    private String email;
    private String numeroCartao;
    private String mes;
    private String ano;
    private String cvv;
}
