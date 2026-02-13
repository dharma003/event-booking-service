package com.org.event.book.dto;

import java.time.LocalDateTime;

public record ErrorResponse(

        int errorCode,
        String errorDescription,
        String path,
        LocalDateTime timestamp

) {}
