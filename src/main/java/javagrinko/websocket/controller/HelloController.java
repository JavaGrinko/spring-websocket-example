package javagrinko.websocket.controller;

import javagrinko.websocket.dom.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public HelloMessage sayHello(HelloMessage message) throws Throwable {
        Thread.sleep(2000);
        return new HelloMessage("+" + message.getMessage());
    }
}
