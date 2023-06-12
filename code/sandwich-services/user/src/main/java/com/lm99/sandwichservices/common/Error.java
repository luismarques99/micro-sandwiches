package com.lm99.sandwichservices.common;

import java.time.LocalDateTime;
import java.util.List;

public record Error(

        LocalDateTime timestamp,

        Integer status,

        String error,

        String message,

        List<ErrorField> fields

) {
}
