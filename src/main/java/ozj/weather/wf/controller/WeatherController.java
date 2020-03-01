package ozj.weather.wf.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ozj.weather.wf.domain.Weather;
import ozj.weather.wf.domain.WeatherResponse;
import ozj.weather.wf.service.CornService;
import ozj.weather.wf.service.MailService;
import ozj.weather.wf.service.WeatherDataService;

/**
 * @author ozj
 * @date 2020-02-21 12:21
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherDataService weatherDataService;


    @Autowired
    private CornService cornService;

    @GetMapping("/cityId/{cityId}")
    public WeatherResponse getReportByCityId(@PathVariable("cityId") String cityId) {
        WeatherResponse w =  weatherDataService.getDataByCityId(cityId);
        return w;
    }

    @PostMapping("/cityName")
    public WeatherResponse getReportByCityName(@RequestParam(value = "cityName") String cityName) {
        WeatherResponse weatherResponse =  weatherDataService.getDataByCityName(cityName);
        return weatherResponse;
    }

    @GetMapping(value = "/set")
    public String getReportByCityId(@RequestParam(value = "rate") int rate, @RequestParam(value = "cycle") int cycle) {
        String cron1 = cornService.createLoopCronExpression(rate,cycle);
        return cron1;
    }
}