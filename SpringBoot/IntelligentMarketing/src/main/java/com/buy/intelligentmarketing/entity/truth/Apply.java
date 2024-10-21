package com.buy.intelligentmarketing.entity.truth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class Apply {
    private long rank;
    private double singleAmount;
    private double totalAmount;
    private int sum;
    private String size;
    private String goodNumber;
    private String goodName;
    private String goodImg;
}
