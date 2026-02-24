package com.alloy.Service;

import com.alloy.Model.AlloyRequest;
import com.alloy.Model.AlloyResponse;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlloyService {

    private final InteractionMatrixService matrixService;

    public AlloyService(InteractionMatrixService matrixService) {
        this.matrixService = matrixService;
    }

    public AlloyResponse calculate(AlloyRequest request) {

        Map<String, Double> elements = request.getElements();

        double total = elements.values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        Map<String, Double> fractions = new HashMap<>();

        for (Map.Entry<String, Double> entry : elements.entrySet()) {
            fractions.put(
                    entry.getKey(),
                    entry.getValue() / total
            );
        }

        // ΔHmix
        double deltaHmix = 0.0;

        List<String> elementList =
                new ArrayList<>(fractions.keySet());

        for (int i = 0; i < elementList.size(); i++) {
            for (int j = i + 1; j < elementList.size(); j++) {

                String e1 = elementList.get(i);
                String e2 = elementList.get(j);

                Double omega =
                        matrixService.getInteractionValue(e1, e2);

                if (omega != null) {

                    double c1 = fractions.get(e1);
                    double c2 = fractions.get(e2);
                    //deltamix += 4 * delta * c1 * c2;
                    deltaHmix += 4 * omega * c1 * c2;
                }
            }
        }

        // ΔSmix
        double R = 8.314;
        double deltaSmix = 0;

        for (double ci : fractions.values()) {
            deltaSmix += -R * ci * Math.log(ci);
        }

        return new AlloyResponse(deltaHmix, deltaSmix);
    }
}