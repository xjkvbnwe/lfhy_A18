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
public class Tk_ozwe_profit_entry {
    private long fid;
    private long fentryid;
    private int fseq;
    private String fkOzweAdvertisementid;
    private String fkOzweAdname;
    private double fkOzweAdtime;
    private double fkOzweAdamount;
    private double fkOzweAmountfield;
}
