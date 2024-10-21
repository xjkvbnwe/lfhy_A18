package com.buy.intelligentmarketing.entity.database;

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
public class Owner_selllog {
    private long fid;
    private String ownerId;
    private String buyOpenid;
    private double buyAmount;
    private String buyDate;
    private String buyTradeno;
    private String state;
}
