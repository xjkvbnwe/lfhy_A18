package com.buy.intelligentmarketing.controller;

import com.buy.intelligentmarketing.service.AdvertisementService;
import com.buy.intelligentmarketing.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Machine")
public class MachineController {

    @Autowired
    private MachineService service;

    @RequestMapping("getMachineAll")
    @ResponseBody
    public String getMachineAll() {
        return service.selectAll();
    }

    @RequestMapping("getMachine/{filter}&{content}")
    @ResponseBody
    public String getMachine(@PathVariable String filter , @PathVariable String content) {
        return service.selectByOneFilter(filter,content);
    }

    @RequestMapping("updateMachine/{number}&{ownerId}")
    @ResponseBody
    public int updateMachine(@PathVariable String number , @PathVariable String ownerId) {
        return service.updateOwnerId(number,ownerId);
    }

    @RequestMapping("bindMachine/{number}&{ownerId}")
    @ResponseBody
    public int bindMachine(@PathVariable String number , @PathVariable String ownerId) {
        return service.bindMachine(number,ownerId);
    }

}
