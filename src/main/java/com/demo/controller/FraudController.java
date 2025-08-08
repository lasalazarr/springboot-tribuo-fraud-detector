package com.demo.controller;

import com.demo.model.TransactionInput;
import com.demo.service.FraudDetectionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fraud")
public class FraudController {

    private final FraudDetectionService service;

    public FraudController(FraudDetectionService service) {
        this.service = service;
    }

    @PostMapping("/predict")
    public String predict(@RequestBody TransactionInput input) {
        return service.predict(input);
    }
}