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
    private int tierCount = 1;
    private int sliceCount = 8;
}
