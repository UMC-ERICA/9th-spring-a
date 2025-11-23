package umc.server.global.apiPayload.handler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import umc.server.domain.test.service.DiscordClient;
import umc.server.domain.test.service.DiscordEmbedMessage;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseErrorCode;
import umc.server.global.apiPayload.code.GeneralErrorCode;
import umc.server.global.apiPayload.code.GeneralSuccessCode;
import umc.server.global.apiPayload.exception.GeneralException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionAdvice {
    @Value("${discord.webhook.url}")
    private String webhookUrl;
    private final DiscordClient discordClient;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleConstraintViolationException(ConstraintViolationException ex){
        // 검사에 실패한 필드와 그에 대한 메시지를 저장하는 map
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(error -> {
            errors.put(error.getPropertyPath().toString(), error.getMessage());
        });
        GeneralErrorCode code = GeneralErrorCode._VALID_FAIL;
        ApiResponse<Map<String, String>> errorResponse = ApiResponse.onFailure(
                code,
                errors
        );
        return ResponseEntity.status(code.getStatus()).body(errorResponse);
    }

    // 컨트롤러 메소드에서 @Valid 어노테이션을 사용하여 DTO의 유효성 검사
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        // 검사에 실패한 필드와 그에 대한 메시지를 저장하는 map
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        GeneralErrorCode code = GeneralErrorCode._VALID_FAIL;
        ApiResponse<Map<String, String>> errorResponse = ApiResponse.onFailure(
                code,
                errors
        );
        return ResponseEntity.status(code.getStatus()).body(errorResponse);
    }

    public GeneralExceptionAdvice(DiscordClient discordClient) {
        this.discordClient = discordClient;
    }
    // 애플리케이션에서 발생하는 커스텀 예외를 처리.
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handleException(
            GeneralException ex
    ){
        if (ex.getCode().getStatus() == HttpStatus.INTERNAL_SERVER_ERROR) {
            return handleInternalServerError(ex);
        }
        return ResponseEntity.status(ex.getCode().getStatus())
                .body(ApiResponse.onFailure(
                                ex.getCode(),
                                null
                        )
                );
    }

    private ResponseEntity<ApiResponse<Void>> handleInternalServerError(GeneralException ex) {
        // Discord Webhook
        DiscordEmbedMessage message = buildDiscordEmbedMessage(ex);
        discordClient.send(webhookUrl, message);

        return ResponseEntity.status(ex.getCode().getStatus())
                .body(ApiResponse.onFailure(
                                ex.getCode(),
                                null
                        )
                );
    }

    private DiscordEmbedMessage buildDiscordEmbedMessage(GeneralException ex){
        String content = String.format(
                " **500에러 발생**\n\n" +
                        "**시각:** %S\n" +
                        "**에러 코드:** %s\n" +
                        "**에러 메시지:** %s",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                ex.getCode(),
                ex.getMessage()
        );

        return new DiscordEmbedMessage(content);
    }

    // 그 외의 정의되지 않은 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(
            Exception ex
    ){
        BaseErrorCode code = GeneralErrorCode._INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(
                                code,
                                ex.getMessage()
                        )
                );
    }
}