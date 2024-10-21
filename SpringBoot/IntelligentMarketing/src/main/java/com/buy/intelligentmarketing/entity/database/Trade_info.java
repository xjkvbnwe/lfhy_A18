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
public class Trade_info {
    private long fid;
    private long fentryid;
    private int fseq;
    private String ownerId;
    private String buyTradeno;
    private String goodsId;
    private long buyTotal;
}
