package com.example.fake_api_us.business.converter;

import com.example.fake_api_us.api.dto.ProductsDTO;
import com.example.fake_api_us.infrastructure.entities.ProdutoEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ProdutoConverterTest {

    @InjectMocks
    ProdutoConverter produtoConverter;

    @Test
    @DisplayName("Deve converter para ProdutoEntity com sucesso")
    void deve_converter_para_ProdutoEntity_com_sucesso() {
        ProductsDTO productsDTO = ProductsDTO.builder().nome("Jaqueta Vermelha").categoria("Roupas").descricao("Jaqueta vermelha com bolsos laterias").preco(new BigDecimal(500.00)).build();

        ProdutoEntity produtoEntityEsperado = ProdutoEntity.builder().id("12345").nome("Jaqueta Vermelha").categoria("Roupas").descricao("Jaqueta vermelha com bolsos laterias").preco(new BigDecimal(500.00)).build();

        ProdutoEntity produtoEntity = produtoConverter.toEntity(productsDTO);

        assertEquals(produtoEntityEsperado.getNome(), produtoEntity.getNome());
        assertEquals(produtoEntityEsperado.getCategoria(), produtoEntity.getCategoria());
        assertEquals(produtoEntityEsperado.getDescricao(), produtoEntity.getDescricao());
        assertEquals(produtoEntityEsperado.getImagem(), produtoEntity.getImagem());

        assertNotNull(produtoEntity.getId());
        assertNotNull(produtoEntity.getDataInclusao());
    }

    @Test
    @DisplayName("Deve converter para ProdutoEntityUpdate com sucesso")
    void deve_converter_para_ProdutoEntityUpdate_com_sucesso() {
        ProductsDTO produtoDTO = ProductsDTO.builder().descricao("Jaqueta Vermelha com bolsos laterais e Listras").preco(new BigDecimal(250.00)).build();
        ProdutoEntity produtoEntityEsperado = ProdutoEntity.builder().id("1245").nome("Jaqueta Vermelha").categoria("Roupas").descricao("Jaqueta Vermelha com bolsos laterais e Listras").preco(new BigDecimal(250.00)).build();
        String id = "1245";
        ProdutoEntity entity = ProdutoEntity.builder().id("1245").nome("Jaqueta Vermelha").categoria("Roupas").descricao("Jaqueta Vermelha com bolsos laterais").preco(new BigDecimal(500.00)).build();

        ProdutoEntity produtoEntity = produtoConverter.toEntityUpdate(entity, produtoDTO, id);

        assertEquals(produtoEntityEsperado.getNome(), produtoEntity.getNome());
        assertEquals(produtoEntityEsperado.getCategoria(), produtoEntity.getCategoria());
        assertEquals(produtoEntityEsperado.getDescricao(), produtoEntity.getDescricao());
        assertEquals(produtoEntityEsperado.getImagem(), produtoEntity.getImagem());
        assertEquals(produtoEntityEsperado.getPreco(), produtoEntity.getPreco());
        assertEquals(produtoEntityEsperado.getId(), produtoEntity.getId());

        assertNotNull(produtoEntity.getDataAtualizacao());
    }

    @Test
    @DisplayName("Deve converter para lista ProdutoDTO com sucesso")
    void deve_converter_para_lista_ProdutoDTO_com_sucesso(){

        List<ProductsDTO> listaProdutosDTO = new ArrayList<>();
        List<ProdutoEntity> listaProdutoEntity = new ArrayList<>();
        ProductsDTO produtoDTO = ProductsDTO.builder().entityId("1245").nome("Jaqueta Vermelha").categoria("Roupas").descricao("Jaqueta Vermelha com bolsos laterais e Listras").preco(new BigDecimal(250.00)).build();
        listaProdutosDTO.add(produtoDTO);
        ProdutoEntity produtoEntityEsperado = ProdutoEntity.builder().id("1245").nome("Jaqueta Vermelha").categoria("Roupas").descricao("Jaqueta Vermelha com bolsos laterais e Listras").preco(new BigDecimal(250.00)).build();
        listaProdutoEntity.add(produtoEntityEsperado);

        List<ProductsDTO> productoDTO = produtoConverter.toListDTO(listaProdutoEntity);

        assertEquals(listaProdutosDTO.get(0).getNome(), productoDTO.get(0).getNome());
        assertEquals(listaProdutosDTO.get(0).getPreco(), productoDTO.get(0).getPreco());
    }
}
