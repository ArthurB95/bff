package com.example.fake_api_us.api.controller;

import com.example.fake_api_us.api.dto.ProductsDTO;
import com.example.fake_api_us.business.service.FakeApiService;
import com.example.fake_api_us.business.service.FakeApiServiceTest;
import com.example.fake_api_us.business.service.ProdutoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class FakeApiControllerTest {

    @InjectMocks
    FakeApiController controller;

    @Mock
    FakeApiService fakeApiService;

    @Mock
    ProdutoService produtoService;

    private MockMvc mockMvc;
    private String json;
    private ProductsDTO productsDTO;
    private String url;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).alwaysDo(print()).build();
        url = "/produtos";
        productsDTO = ProductsDTO.builder().nome("Jaqueta Vermelha").categoria("Roupas").descricao("Jaqueta vermelha com bolsos laterias").preco(new BigDecimal(500.00)).build();
        json = objectMapper.writeValueAsString(productsDTO);
    }

    @Test
    @DisplayName("Deve buscar produtos FakeApi e salvar com sucesso")
    void deve_buscar_produtos_FakeApi_e_salvar_com_sucesso() throws Exception {

        when(fakeApiService.buscaProdutos()).thenReturn(Collections.singletonList(productsDTO));
        mockMvc.perform(post(url + "/api")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(fakeApiService).buscaProdutos();
        verifyNoMoreInteractions(fakeApiService);
    }

    @Test
    @DisplayName("Deve salvar produtosDTO com sucesso")
    void deve_salvar_produtosDTO_com_sucesso() throws Exception {

        when(produtoService.salvaProdutosDTO(any(ProductsDTO.class))).thenReturn(productsDTO);

        mockMvc.perform(post(url + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(produtoService).salvaProdutosDTO(any(ProductsDTO.class));
        verifyNoMoreInteractions(produtoService);
    }

    @Test
    @DisplayName("Não deve enviar request caso produtosDTO seja null")
    void nao_deve_enviar_request_caso_produtosDTO_seja_null() throws Exception {

        mockMvc.perform(post(url + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(produtoService);
    }

    @Test
    @DisplayName("Deve atualizar produtosDTO com sucesso")
    void deve_atualizar_produtosDTO_com_sucesso() throws Exception {

        String id = "12345";

        when(produtoService.updateProduto(eq(id), any(ProductsDTO.class))).thenReturn(productsDTO);

        mockMvc.perform(put(url + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .param("id", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(produtoService).updateProduto(eq(id), any(ProductsDTO.class));
        verifyNoMoreInteractions(produtoService);
    }

    @Test
    @DisplayName("Não deve enviar request caso id seja null")
    void nao_deve_enviar_request_caso_id_seja_null() throws Exception {

        mockMvc.perform(put(url + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(produtoService);
    }

    @Test
    @DisplayName("Deve deletar produtosDTO com sucesso")
    void deve_deletar_produtosDTO_com_sucesso() throws Exception {

        String nome = "Jaqueta Vermelha";

        doNothing().when(produtoService).deletaProduto(nome);

        mockMvc.perform(delete(url + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("nome", nome)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        verify(produtoService).deletaProduto(nome);
        verifyNoMoreInteractions(produtoService);
    }

    @Test
    @DisplayName("Não deve enviar request delete caso id seja null")
    void nao_deve_enviar_request_delete_caso_id_seja_null() throws Exception {

        mockMvc.perform(delete(url + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(produtoService);
    }

    @Test
    @DisplayName("Deve buscar produtosDTO por nome com sucesso")
    void deve_buscar_produtosDTO_por_nome_com_sucesso() throws Exception {

        String nome = "Jaqueta Vermelha";

        when(produtoService.buscaProdutoPorNome(nome)).thenReturn(productsDTO);

        mockMvc.perform(get(url + "/" + nome)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(produtoService).buscaProdutoPorNome(nome);
        verifyNoMoreInteractions(produtoService);
    }
}
