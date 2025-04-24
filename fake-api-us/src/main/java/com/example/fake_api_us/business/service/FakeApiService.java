package com.example.fake_api_us.business.service;

import com.example.fake_api_us.api.dto.ProductsDTO;
import com.example.fake_api_us.business.converter.ProdutoConverter;
import com.example.fake_api_us.infrastructure.client.FakeApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class FakeApiService {

    private final FakeApiClient cliente;
    private final ProdutoConverter converter;
    private final ProdutoService produtoService;

    public List<ProductsDTO> buscaProdutos() {
        try {
            List<ProductsDTO> dto = cliente.buscaListaProdutos();
            dto.forEach(
                    produto -> {
                        Boolean retorno = produtoService.existsPorNome(produto.getNome());

                        if (retorno.equals(false)) {
                            produtoService.salvaProdutos(converter.toEntity(produto));
                        }
                    }
            );
            return produtoService.buscaTodosOsProdutos();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar e gravar produtos no banco de dados");
        }
    }
}
