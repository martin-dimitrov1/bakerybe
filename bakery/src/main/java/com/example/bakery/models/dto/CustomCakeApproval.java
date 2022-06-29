package com.example.bakery.models.dto;

import java.time.LocalDate;

public record CustomCakeApproval(Long requestId,
                                 LocalDate expectedDueDate,
                                 Boolean approved,
                                 String message) {
}
