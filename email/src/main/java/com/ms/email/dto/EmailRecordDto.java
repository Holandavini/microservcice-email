package com.ms.email.dto;

import java.util.UUID;

public record EmailRecordDto(UUID id,
                             String emailTo,
                             String subject,
                             String text) {
}
