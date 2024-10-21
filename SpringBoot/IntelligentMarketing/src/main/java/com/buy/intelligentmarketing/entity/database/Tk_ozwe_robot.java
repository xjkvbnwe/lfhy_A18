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
public class Tk_ozwe_robot {
    private long fid;
    private String fnumber;
    private String fname;
    private String fstatus;
    private String fkOzweEngine;
    private int fkOzweCheckbox;
    private String fkOzweReply;
}
