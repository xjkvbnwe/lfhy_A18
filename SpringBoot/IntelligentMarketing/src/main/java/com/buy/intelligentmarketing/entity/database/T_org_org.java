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
public class T_org_org {
    private String fid;
    private String fnumber;
    private String fname;
    private String fkOzweTelephone;
    private String fkOzweOpentime;
    private String fkOzweAddresstext;
    private double fkOzweLongitude;
    private double fkOzweLatitude;
}
