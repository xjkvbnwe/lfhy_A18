package com.buy.intelligentmarketing.entity.database;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class Tk_ozwe_goodlist {
    private long fid;
    private String fnumber;
    private String fname;
    private String fstatus;
    private long fcreatorid;
    private int fenable;
    private long fkOzweOrgfield;
    private Date fmodifytime;
    private String fmasterid;
    private String fkOzwePicturefield;
    private String fkOzweGoodcompany;
    private String fkOzweGoodintroduction;
    private String fkOzweGoodnum;
    private String fkOzweGoodcategory;
    private String fkOzweIsoutput;
    private String fkOzweGoodamount;
    private String fkOzweTextareafield;
    private String fkOzweIsadequate;
    private Integer fkOzweOutput;
    private Integer fkOzweTotal;
    private Integer fkOzweInput;
}
