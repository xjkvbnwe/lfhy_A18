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
public class Profit {
    private long fid;
    private String billNo;
    private String createTime;
    private String ownerId;
    private double allAmount;
    private double playTime;
}
