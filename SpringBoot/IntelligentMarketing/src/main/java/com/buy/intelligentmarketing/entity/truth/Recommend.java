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
public class Recommend {
    String userId;
    String goodName;
    double purchaseRate;
    double profitRate;
}
