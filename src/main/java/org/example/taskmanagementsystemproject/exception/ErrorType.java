package org.example.taskmanagementsystemproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    INTERNAL_SERVER_ERROR(500,"Sunucuda beklenmeyen hata oluştu. Lütfen tekrar deneyin...",HttpStatus.INTERNAL_SERVER_ERROR),
    VALIDATION_ERROR(400,"Girilen parametreler hatalıdır. Lütfen kontrol ederek tekrar deneyiniz...", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(5000, "Aranılan Kullanıcı Bulunamadı...", HttpStatus.NOT_FOUND),
    DUPLICATE_KEY(400, "Bu kullanıcı adı zaten mevcut.", HttpStatus.BAD_REQUEST),
    USER_NOTSTRING(5001,"Adınız ve soyadınız string olarak kalamaz...", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME_OR_PASSWORD(5002,"Kullanıcı adınız ya da şifreniz hatalı...", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(5003,"Geçersiz assignedByUserId...", HttpStatus.BAD_REQUEST),
    ASSIGNED_TO_USER_NOTFOUND(5004,"Görev atadığınız kullanıcı bulunamadı...", HttpStatus.NOT_FOUND),
    TASK_LIST_EMPTY(5005,"Listenin içerisi boş. Gösterilecek birşey yok...", HttpStatus.BAD_REQUEST);

    int code;
    String message;
    HttpStatus httpStatus;
}
