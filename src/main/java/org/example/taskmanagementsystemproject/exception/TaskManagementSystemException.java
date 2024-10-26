package org.example.taskmanagementsystemproject.exception;

import lombok.Getter;

/**
 * bu exception'ı globalexception sınıfı ile handle etmediğimiz zaman
 * istek attığımızda hatayı konsol ekranına basıyor ve kullanıcıya dönen response kısmında 500 internal server hatası dönüyor
 * Bu hatayı almak çok anlaşılır olmadığı için globalException sınıfında handle edip kullanıcıya bir mesaj sunabilyoruz.
 * Böylelikle daha anlamlı oluyor.
 * Buraya eklediğimiz errorType sınıfı bir enum alandır.
 * Burada projede aldığımız hataları tanımlayıp böylelikle handle etmek daha kolay olacaktır.
 */
@Getter
public class TaskManagementSystemException extends RuntimeException {
    private ErrorType errorType;

    public TaskManagementSystemException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
