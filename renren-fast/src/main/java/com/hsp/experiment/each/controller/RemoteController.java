package com.hsp.experiment.each.controller;

import com.hsp.experiment.each.service.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/each/remote")
public class RemoteController {

    @Autowired
    RemoteService remoteService;

    @RequestMapping("/desktop")
    public void remoteDesktop() {
        System.setProperty("java.awt.headless", "false");
        remoteService.start();
    }
}
