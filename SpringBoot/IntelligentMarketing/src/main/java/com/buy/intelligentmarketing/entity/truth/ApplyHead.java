package com.buy.intelligentmarketing.entity.truth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class ApplyHead {
    private String createDate;
    private String applier;
    private double payAmount;
    private long orgId;
}
