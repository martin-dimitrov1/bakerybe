package com.example.bakery.models;

import java.util.Date;

public record CartForm(String address, Date toDate, String time, String token) {
}
