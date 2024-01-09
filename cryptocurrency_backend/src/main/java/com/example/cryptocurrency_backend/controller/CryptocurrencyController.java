package com.example.cryptocurrency_backend.controller;

import com.example.cryptocurrency_backend.Exception.ResourceNotFoundException;
import com.example.cryptocurrency_backend.Model.CryptocurrencyModel;
import com.example.cryptocurrency_backend.repository.CryptocurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CryptocurrencyController {

    @Autowired
    private CryptocurrencyRepository cryptocurrencyRepository;

    @GetMapping("/cryptocurrency")
    public List<CryptocurrencyModel> getCryptocurrency() {
       return cryptocurrencyRepository.findAll();
    }

    @GetMapping("/cryptocurrency/{currencyId}")
    public ResponseEntity<CryptocurrencyModel> getCryptocurrencyById(@PathVariable int currencyId){
        CryptocurrencyModel model = cryptocurrencyRepository.findById(currencyId)
                .orElseThrow(()-> new ResourceNotFoundException("Cryptocurrency is not found for currency: " + currencyId));
        return ResponseEntity.ok(model);
    }

    @PostMapping("/cryptocurrency")
    public CryptocurrencyModel createCryptocurrency(@RequestBody CryptocurrencyModel model) {
        return cryptocurrencyRepository.save(model);
    }

    @PutMapping("/cryptocurrency/{currencyId}")
    public ResponseEntity<CryptocurrencyModel> updateCryptocurrency(@PathVariable int currencyId, @RequestBody CryptocurrencyModel cryptocurrencyModelDetails){
        CryptocurrencyModel model = cryptocurrencyRepository.findById(currencyId)
                .orElseThrow(()-> new ResourceNotFoundException("Cryptocurrency is not found for currency: " + currencyId));
        model.setCurrencyName(cryptocurrencyModelDetails.getCurrencyName());
        model.setChange_24h(cryptocurrencyModelDetails.getChange_24h());
        model.setPriceCurrency(cryptocurrencyModelDetails.getPriceCurrency());
        model.setMarket_cap(cryptocurrencyModelDetails.getMarket_cap());
        CryptocurrencyModel updatedCryptocurrency = cryptocurrencyRepository.save(model);
        return ResponseEntity.ok(updatedCryptocurrency);
    }

    @DeleteMapping("/cryptocurrency/{currencyId}")
    public ResponseEntity <Map<String, Boolean>> deleteCryptocurrency(@PathVariable int currencyId){
        CryptocurrencyModel model = cryptocurrencyRepository.findById(currencyId)
                .orElseThrow(()-> new ResourceNotFoundException("Cryptocurrency is not found for currency: " + currencyId));
        cryptocurrencyRepository.delete(model);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
