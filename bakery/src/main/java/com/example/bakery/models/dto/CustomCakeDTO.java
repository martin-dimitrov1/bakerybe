package com.example.bakery.models.dto;

import com.example.bakery.models.enums.PhotoSimilarityOptions;

import java.util.Date;

public record CustomCakeDTO(Long spongeId,
                            Long sauceId,
                            Long icingId,
                            Long topingsId,
                            String celebration,
                            String theme,
                            Integer tiers,
                            Integer guests,
                            PhotoSimilarityOptions similarityOption,
                            String firstName,
                            String lastName,
                            String email,
                            String phone,
                            Date toDate) {
    public CustomCakeDTO(CustomCakeDTO dto) {
        this(1L,10L,25L, 33L, dto.celebration, dto.theme,
                dto.tiers, dto.guests, dto.similarityOption, dto.firstName,
                dto.lastName, dto.email, dto.phone, dto.toDate);
    }
}
