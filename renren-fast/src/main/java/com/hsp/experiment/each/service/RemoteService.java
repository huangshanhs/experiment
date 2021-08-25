package com.hsp.experiment.each.service;

import com.hsp.experiment.each.remote.RemoteAssistance;
import org.springframework.stereotype.Service;

@Service
public class RemoteService {


    public void start() {

        RemoteAssistance rAssistance = new RemoteAssistance();
        rAssistance.setTitle("远程协助");
        rAssistance.setDefaultCloseOperation(3);
        rAssistance.setSize(320, 240);
        rAssistance.setVisible(true);
    }
}
