package org.example.taskmanagementsystemproject.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Tanımlaması yapılmayan ve projede diğer tüm hataları yakalamak için RuntimeException yakalayalım.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> runtimeExceptionHandler() {
        return  createResponseEntity(ErrorType.INTERNAL_SERVER_ERROR, null);
    }

    /**
     * Girilen username göre kullanıcı bulunamaz ise
     * hatayı bu methoddan yakalayıp kullanıcı istek attığında karşısına,
     * yazdığımız exceptionı çağırdığımız yerdeki mesajı kullanıcıya geri dönüş yapar.
     * Bu method tanımlaması yapılan bir exception metodudur.
     * Birde burda bize geri dönüş tipi object diyoruz, bunu değiştirebiliriz.
     * <p>
     * return ResponseEntity
     * .status(HttpStatus.INTERNAL_SERVER_ERROR)
     * .body(exception.getMessage());
     * <p>
     * ErrorMessage diye bir sınıf oluşturup içerisine özellikleri verip burada onu çağırıp  body kısmını doldurabiliriz.(BaseResponse benzer)
     * ErrorMessage içerisinde de ErrorType adında enum sınıfımızda;
     * projede alacağımız hataları tanımlamış oluruz ve createResponseEntity adında bir methodumuz olur
     * burada tanımlamaları yapıp çoğu yerde kullanabiliriz.
     */
    @ExceptionHandler(TaskManagementSystemException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> taskManagementSystemExceptionHandler(TaskManagementSystemException exception) {
        return createResponseEntity(exception.getErrorType(), null);
    }

    /**
     * swagger üzerinden istek atılırken ordaki ifade string olarak kaldığı için
     * execute dediğimizde o veriyi direk veritabanına kaydediyor. Bu yüzden böyle bir exception kullandım.
     */
  /*  @ExceptionHandler(UserNotFoundStringException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> userNotStringFoundExceptionHandler(UserNotFoundStringException exception) {
        return createResponseEntity(exception.getErrorType(), null);
    }*/

    /**
     * kullanıcı kayıt edilirken username alanı unique olarak ayarladığım için 500 hatası alıyordum
     * Bu yüzden aynı username adından kayıt varsa kullanıcıya 400 hatası dönmüş olduk.
     * @param exception
     * @return
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        if (exception.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
            // Özellikle username alanında unique constraint hatasını yakalıyoruz
            return createResponseEntity(ErrorType.DUPLICATE_KEY, List.of("Kullanıcı adı zaten mevcut."));
        }
        return createResponseEntity(ErrorType.INTERNAL_SERVER_ERROR, null);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        List<String> fieldErrors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(f -> {
                    fieldErrors.add("Değişken adı..: " + f.getField() + " - " + f.getDefaultMessage());
                });
        return createResponseEntity(ErrorType.VALIDATION_ERROR, fieldErrors);
    }

    public ResponseEntity<ErrorMessage> createResponseEntity(ErrorType errorType, List<String> fields) {
        log.error("Exception.....: " + errorType.getMessage() + " " + (fields != null ? fields : "No field errors"));
        return new ResponseEntity<>(ErrorMessage.builder()
                .success(false)
                .message(errorType.getMessage())
                .code(errorType.getCode())
                .fields(fields)
                .build(), errorType.getHttpStatus());
    }

}
