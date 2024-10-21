package com.buy.intelligentmarketing.entity.truth;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class Good implements Comparable<Good>{
    private long fid;
    private String img;
    private String number;
    private String goodName;
    private String category;
    private String isOutput;
    private Integer allAmount;
    private Integer outputAmount;
    private Integer inputAmount;
    private long orgid;
    private long creatorId;
    private String introduction;
    private String size;
    private double singleAmount;
    private String company;
    private String kcSituation;
    private String inputSituation;
    private String outputSituation;
    private String other;

    @Override
    public int compareTo(Good o) {
        return Integer.compare(this.inputAmount,o.inputAmount);
    }
}
