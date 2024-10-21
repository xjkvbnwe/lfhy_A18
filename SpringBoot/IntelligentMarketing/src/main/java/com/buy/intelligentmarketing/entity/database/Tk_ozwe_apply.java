package com.buy.intelligentmarketing.entity.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class Tk_ozwe_apply {
    private long fid;
    private long fkOzweOrg;
    private String fbillno;
    private String fbillstatus;
    private long fcreatorid;
    private long fmodifierid;
    private long fauditorid = 0;
    private String fkOzweCreatedate;
    private String fkOzweApplier;
    private double fkOzwePay;
}
