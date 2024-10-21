package com.buy.intelligentmarketing.function;

import java.util.*;

public class ReplenishmentRecommend {
    private Map<String, Map<String, Double[]>> train;
    private Map<String, Map<String, Double>> C;

    public ReplenishmentRecommend(List<String> trainData) {
        train = new HashMap<>();
        for (String line : trainData) {
            String[] fields = line.split(",");
            String user = fields[0];
            String item = fields[1];
            double sellRate = Double.parseDouble(fields[2]);
            double moneyRate = Double.parseDouble(fields[3]);
            train.computeIfAbsent(user, k -> new HashMap<>())
                    .put(item, new Double[]{sellRate, moneyRate});
        }
    }

    public void itemWeight() {
        C = new HashMap<>();
        for (String user : train.keySet()) {
            for (String item : train.get(user).keySet()) {
                double sellRate = train.get(user).get(item)[0];
                double moneyRate = train.get(user).get(item)[1];
                double total = sellRate * moneyRate;
                C.computeIfAbsent(user, k -> new HashMap<>())
                        .put(item, total);
            }
        }
    }

    public Map<String, Double> recommend(String user, int N) {
        Map<String, Double> L = C.get(user);
        Map<String, Double> result = new LinkedHashMap<>();
        List<Map.Entry<String, Double>> sortedList = new ArrayList<>(L.entrySet());
        sortedList.sort((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()));
        int count = 0;
        for (Map.Entry<String, Double> entry : sortedList) {
            if (count >= N) break;
            result.put(entry.getKey(), entry.getValue());
            count++;
        }
        return result;
    }
}
