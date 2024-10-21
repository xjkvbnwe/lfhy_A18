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
public class Owner_goods {
    private long fid;
    private String ownerId;
    private String goodsId;
    private String goodsNumber;
    private String goodsName;
    private long goodsTotal;
    private String goodsCreatedate;
    private String goodsImg;
    private double goodsPrice;
}
