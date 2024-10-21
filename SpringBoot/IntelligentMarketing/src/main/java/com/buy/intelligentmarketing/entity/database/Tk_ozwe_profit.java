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
public class Tk_ozwe_profit {
    private long fid;
    private String fbillno;
    private String fbillstatus;
    private String fcreatetime;
    private String fkOzweOwnerid;
    private double fkOzweAllamount;
    private double fkOzwePlaytime;
}
