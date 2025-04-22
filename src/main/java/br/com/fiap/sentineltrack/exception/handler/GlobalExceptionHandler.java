package br.com.fiap.sentineltrack.exception.handler;

import br.com.fiap.sentineltrack.exception.ErroValidacaoException;
import br.com.fiap.sentineltrack.exception.RecursoNaoEncontradoException;
import br.com.fiap.sentineltrack.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleRecursoNaoEncontradoException(
            RecursoNaoEncontradoException ex, WebRequest request) {
        ErroResponse erro = new ErroResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<ErroResponse> handleRegraDeNegocioException(
            RegraDeNegocioException ex, WebRequest request) {
        ErroResponse erro = new ErroResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErroValidacaoException.class)
    public ResponseEntity<ErroResponse> handleErroValidacaoException(
            ErroValidacaoException ex, WebRequest request) {
        ErroResponse erro = new ErroResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacaoResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        ErroValidacaoResponse response = new ErroValidacaoResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                LocalDateTime.now(),
                request.getDescription(false),
                errors
        );
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleGenericException(
            Exception ex, WebRequest request) {
        ErroResponse erro = new ErroResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro interno no servidor: " + ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Schema(description = "Resposta padrão de erro da API")
    public static class ErroResponse {
        @Schema(description = "Código HTTP do erro", example = "404")
        private int status;

        @Schema(description = "Mensagem de erro", example = "Recurso não encontrado")
        private String mensagem;

        @Schema(description = "Data e hora do erro", example = "2024-03-20T10:30:00")
        private LocalDateTime timestamp;

        @Schema(description = "Detalhes adicionais do erro", example = "uri=/api/incidentes/999")
        private String detalhes;

        public ErroResponse(int status, String mensagem, LocalDateTime timestamp, String detalhes) {
            this.status = status;
            this.mensagem = mensagem;
            this.timestamp = timestamp;
            this.detalhes = detalhes;
        }

        // Getters e Setters
        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMensagem() {
            return mensagem;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public String getDetalhes() {
            return detalhes;
        }

        public void setDetalhes(String detalhes) {
            this.detalhes = detalhes;
        }
    }

    @Schema(description = "Resposta de erro de validação da API")
    public static class ErroValidacaoResponse extends ErroResponse {
        @Schema(description = "Mapa de erros de validação por campo")
        private Map<String, String> erros;

        public ErroValidacaoResponse(int status, String mensagem, LocalDateTime timestamp, 
                                   String detalhes, Map<String, String> erros) {
            super(status, mensagem, timestamp, detalhes);
            this.erros = erros;
        }

        public Map<String, String> getErros() {
            return erros;
        }

        public void setErros(Map<String, String> erros) {
            this.erros = erros;
        }
    }
}
