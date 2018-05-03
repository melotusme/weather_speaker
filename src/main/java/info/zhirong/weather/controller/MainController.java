package info.zhirong.weather.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @Autowired
    private RabbitTemplate template;

    // 从application.properties 获取,${}与#{}不同
    @Value("${tmp}")
    private String tmp;


    @GetMapping("/weather_speaker")
    public @ResponseBody
    String weatherSpeak(@RequestParam("name") String name) {

//        this.send(name);
//        return String.format("[x] %s message sent", name);
        return String.format("[x] %s message sent", tmp);
    }

    public void send(String name) {
        template.convertAndSend("", "weather", name);
    }
}
