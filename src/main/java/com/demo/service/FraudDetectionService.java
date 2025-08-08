package com.demo.service;

import com.demo.model.TransactionInput;
import org.springframework.stereotype.Service;
import org.tribuo.*;
import org.tribuo.classification.*;
import org.tribuo.classification.sgd.linear.LogisticRegressionTrainer;
import org.tribuo.data.csv.CSVLoader;
import org.tribuo.impl.ArrayExample;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Paths;

@Service
public class FraudDetectionService {

    private Model<Label> model;

    @PostConstruct
    public void init() throws IOException {
        var factory = new LabelFactory();
        var loader = new CSVLoader<>(factory);

        // Usar loadDataSource y envolver en MutableDataset
        var dataSource = loader.loadDataSource(Paths.get("src/main/resources/banking_fraud_sample.csv"), "label");
        var dataset = new MutableDataset<>(dataSource);

        // Entrenar el modelo
        this.model = new LogisticRegressionTrainer().train(dataset);
        System.out.println("Modelo entrenado con " + dataset.size() + " registros");
    }

    public String predict(TransactionInput input) {
        ArrayExample<Label> example = new ArrayExample<>(new Label("unknown"));
        example.add(new Feature("amount", input.getAmount()));
        example.add(new Feature("country=" + input.getCountry(), 1.0));
        example.add(new Feature("hour", input.getHour()));
        example.add(new Feature("card_type=" + input.getCardType(), 1.0));

        return model.predict(example).getOutput().getLabel();
    }
}