package com.buy.intelligentmarketing.function;
import java.util.*;

public class InputRecommend {
    private Map<String, Map<String, Float>> trainData; // 存储训练数据集
    private Map<String, Map<String, Float>> W; // 物品间相似度矩阵

    public InputRecommend(List<String> trainList) {
        this.trainData = new HashMap<>();
        for (String line : trainList) {
            String[] fields = line.split(",");
            String user = fields[0];
            String item = fields[2];
            float score = Float.parseFloat(fields[1]);
            if (!trainData.containsKey(user)) {
                trainData.put(user, new HashMap<>());
            }
            trainData.get(user).put(item, score);
        }
    }

    // 计算物品间的相似度矩阵
    public void itemSimilarity() {
        Map<String, Map<String, Integer>> C = new HashMap<>();
        Map<String, Integer> N = new HashMap<>();
        for (Map.Entry<String, Map<String, Float>> userEntry : trainData.entrySet()) {
            Map<String, Float> items = userEntry.getValue();
            for (String i : items.keySet()) {
                N.put(i, N.getOrDefault(i, 0) + 1);
                if (!C.containsKey(i)) {
                    C.put(i, new HashMap<>());
                }
                for (String j : items.keySet()) {
                    if (i.equals(j)) {
                        continue;
                    }
                    C.get(i).put(j, C.get(i).getOrDefault(j, 0) + 1);
                }
            }
        }

        W = new HashMap<>();
        for (Map.Entry<String, Map<String, Integer>> itemEntry : C.entrySet()) {
            String i = itemEntry.getKey();
            Map<String, Integer> relatedItems = itemEntry.getValue();
            W.put(i, new HashMap<>());
            for (Map.Entry<String, Integer> relatedItemEntry : relatedItems.entrySet()) {
                String j = relatedItemEntry.getKey();
                int cij = relatedItemEntry.getValue();
                W.get(i).put(j, (float) cij / (float) (Math.sqrt(N.get(i) * N.get(j))));
            }
        }
    }

    // 给指定用户推荐前N个最感兴趣的物品
    public Map<String, Float> recommend(String user, int N) {
        Map<String, Float> rank = new HashMap<>();
        Map<String, Float> actionItems = trainData.get(user);
        for (Map.Entry<String, Float> actionItemEntry : actionItems.entrySet()) {
            String item = actionItemEntry.getKey();
            float score = actionItemEntry.getValue();
            for (Map.Entry<String, Float> relatedItemEntry : W.get(item).entrySet()) {
                String j = relatedItemEntry.getKey();
                float wj = relatedItemEntry.getValue();
                if (actionItems.containsKey(j)) {
                    continue;
                }
                rank.put(j, rank.getOrDefault(j, 0f) + score * wj);
            }
        }
        Map<String, Float> result = new LinkedHashMap<>();
        rank.entrySet().stream()
                .sorted(Map.Entry.<String, Float>comparingByValue().reversed())
                .limit(N)
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
        return result;
    }

    /*public static void main(String[] args) {
        String[] strings = {"A,1,a", "A,1,b", "A,1,d", "B,1,b", "B,1,c", "B,1,e", "C,1,c", "C,1,d", "D,1,b", "D,1,c", "D,1,d",
                "E,1,a", "E,1,d"};
        List<String> target = Arrays.asList(strings);
        InputRecommend recommendFunction = new InputRecommend(target);
        recommendFunction.itemSimilarity();
        Map<String , Float> result = recommendFunction.recommend("A",10);
        System.out.println(result);
    }*/

}
