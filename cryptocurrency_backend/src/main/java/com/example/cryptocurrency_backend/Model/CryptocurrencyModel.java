package com.example.cryptocurrency_backend.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Currency;

@Entity
@Table(name = "Cryptocurrency")
@Data
public class CryptocurrencyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int currencyId;

    private String currencyName;

    private Currency priceCurrency;

    private float change_24h;

    private float market_cap;
}
