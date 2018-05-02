package info.zhirong.weather.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @Autowired
    private RabbitTemplate template;


    @GetMapping("/weather_speaker")
    public @ResponseBody
    String weatherSpeak(@RequestParam("name") String name) {

        this.send(name);
        return String.format("[x] %s message sent", name);
    }

    public void send(String name) {
        template.convertAndSend("", "weather", name);
    }
}
