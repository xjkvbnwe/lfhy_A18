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
public class Tk_ozwe_applyentryentity {
    private long fid;
    private long fentryid;
    private long fseq;
    private double fkOzweSingleamount;
    private double fkOzweTotalamount;
    private int fkOzweIntegerfield;
    private String fkOzweSize;
    private String fkOzweGoodnumber;
    private String fkOzweGoodname;
    private String fkOzweGoodimg;
}
