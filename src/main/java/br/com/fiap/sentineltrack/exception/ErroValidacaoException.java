package br.com.fiap.sentineltrack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErroValidacaoException extends RuntimeException {

    public ErroValidacaoException(String mensagem) {
        super(mensagem);
    }

    public ErroValidacaoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
