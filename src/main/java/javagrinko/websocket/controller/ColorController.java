package javagrinko.websocket.controller;

import javafx.scene.paint.Color;
import javagrinko.websocket.dom.ColorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.Random;

@Controller
public class ColorController {

    private Logger log = LoggerFactory.getLogger(ColorController.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/color")
    public void receiveColor(ColorMessage message){
        log.info("message.getColorString() = " + message.getColorString());
    }


    @Scheduled(fixedDelay = 1000)
    private void bgColor(){
        Random r = new Random();
        Color rgb = Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255), r.nextDouble());
        String m = rgb.toString();
        String color = m.replace("0x", "#");
        simpMessagingTemplate.convertAndSend("/topic/color", new ColorMessage(color));
        log.info("Send color: " + color);
    }
}