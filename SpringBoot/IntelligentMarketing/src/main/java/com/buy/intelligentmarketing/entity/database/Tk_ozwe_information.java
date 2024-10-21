package com.buy.intelligentmarketing.entity.database;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class Tk_ozwe_information {
    private long fid;
    private long fentryid;
    private int fseq;
    private double fkOzweSingleamount;
    private String fkOzweOther;
    private String fkOzweOtherTag;
    private String fkOzweCompany;
    private String fkOzweSize;
    private String fkOzweKcsituation;
    private String fkOzweInputsituation;
    private String fkOzweOutputsituation;
}
