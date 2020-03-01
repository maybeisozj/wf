package ozj.weather.wf.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ozj.weather.wf.domain.WeatherResponse;
import ozj.weather.wf.service.MailService;
import ozj.weather.wf.service.WeatherDataService;

import java.util.Date;

/**
 * 定时任务类，这个是开始做的时候的小实验，如果想要体验一下，加上注解@component即可
 * @author ozj
 * @date 2020-02-22 14:33
 */
public class MySchedule {

    @Autowired
    private WeatherDataService weatherDataService;

    @Autowired
    private MailService mailService;

    @Scheduled(fixedDelay = 1000)
    public void fixedDelay(){
        System.out.println("fixedDelay: " + new Date());
    }

    @Scheduled(fixedRate = 2000)
    public void fixedRate(){
        System.out.println("fixedRate: " + new Date());
    }

    @Scheduled(initialDelay = 1000,fixedRate = 2000)
    public void initialDelay(){
        System.out.println("initialDelay: " + new Date());
    }

    @Scheduled(cron = "0 0/5 14,18 * * ?")
    public void corn(){
        String cityName = "长沙";
        String sendto = "111111111@qq.com";
        WeatherResponse weatherResponse =  weatherDataService.getDataByCityName(cityName);
        mailService.sendSimpleMail(sendto,sendto,"主题",weatherResponse.getData().getForecast().get(0).getType());
    }

}
