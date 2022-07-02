package com.example.bakery.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class EventDetails {
    private String lifeCelebration;
    private String partyTheme;
    private Integer tierCount = 1;
    private Integer sliceCount = 8;
}
