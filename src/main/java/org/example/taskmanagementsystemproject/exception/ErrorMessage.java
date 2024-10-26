package org.example.taskmanagementsystemproject.exception;


import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * code: aldığımız hatanın kodu
 * message: aldığımız hatanın mesajı
 * success: işlem başarılı ise true, değilse false yazabiliriz.
 */

@Data
@Builder

public class ErrorMessage {

    Integer code;
    String message;
    Boolean success;
    List<String> fields;
}
