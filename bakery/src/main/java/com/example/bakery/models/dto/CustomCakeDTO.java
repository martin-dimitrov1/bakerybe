package com.example.bakery.models.dto;

import com.example.bakery.models.EventDetails;
import com.example.bakery.models.PersonalInformation;
import com.example.bakery.models.enums.PhotoSimilarityOptions;

import java.util.Date;

public record CustomCakeDTO(Long spongeId,
                            Long sauceId,
                            Long icingId,
                            Long topingsId,
                            EventDetails eventDetails,
                            PhotoSimilarityOptions similarityOption,
                            PersonalInformation personalInformation,
                            Date toDate) {
}
