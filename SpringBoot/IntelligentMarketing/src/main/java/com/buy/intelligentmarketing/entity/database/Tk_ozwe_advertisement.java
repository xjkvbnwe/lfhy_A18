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
public class Tk_ozwe_advertisement {
    private long fid;
    private String fnumber;
    private String fname;
    private String fstatus;
    private String fcreatorid;
    private String fcreatetime;
    private String fkOzweVideoimg;
    private double fkOzweVideoamount;
    private long fkOzweVideohot;
    private String fkOzweVideosign;
    private String fkOzweVideoaddress;
    private String fkOzweFilename;
}
