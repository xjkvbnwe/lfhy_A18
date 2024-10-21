package com.buy.intelligentmarketing.controller;

import com.buy.intelligentmarketing.service.AdvertisementService;
import com.buy.intelligentmarketing.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Org")
public class OrgController {

    @Autowired
    private OrgService service;

    @RequestMapping("getOrgs")
    @ResponseBody
    public String getOrgs() {
        return service.selectAll();
    }

    @RequestMapping("getOrgByOneFilter/{filter}&{content}")
    @ResponseBody
    public String selectByOneFilter(@PathVariable String filter, @PathVariable String content) {
        return service.selectByOneFilter(filter, content);
    }

}
