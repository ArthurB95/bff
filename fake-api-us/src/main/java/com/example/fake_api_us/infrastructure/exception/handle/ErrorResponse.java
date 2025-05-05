package com.example.fake_api_us.infrastructure.exception.handle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String mensagem;
    private LocalDateTime data;
    private int status;
    private String path;
}
