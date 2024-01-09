package com.example.cryptocurrency_backend.repository;

import com.example.cryptocurrency_backend.Model.CryptocurrencyModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptocurrencyRepository extends JpaRepository<CryptocurrencyModel, Integer> {
}
