package com.buy.intelligentmarketing.controller;

import com.buy.intelligentmarketing.service.AdvertisementService;
import com.buy.intelligentmarketing.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Invitation")
public class InvitationController {

    @Autowired
    private InvitationService service;

    @RequestMapping("selectInvitationByOneFilter/{filter}&{content}")
    @ResponseBody
    public String selectInvitationByOneFilter(@PathVariable String filter , @PathVariable String content) {
        return service.selectInvitationByOneFilter(filter,content);
    }

    @RequestMapping("updateStatus/{ownerId}&{oldStatus}&{newStatus}")
    @ResponseBody
    public String updateStatus(@PathVariable String ownerId , @PathVariable String oldStatus, @PathVariable String newStatus) {
        return service.updateStatus(ownerId,oldStatus,newStatus);
    }

}
