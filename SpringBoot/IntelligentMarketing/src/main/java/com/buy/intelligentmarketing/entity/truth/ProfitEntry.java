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
public class ProfitEntry {
    private long fid;
    private String AdvertisementId;
    private String advertisementName;
    private double advertisementPlayTime;
    private double amountSingle;
    private double allAmount;
}
