package ozj.weather.wf.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 城市天气类
 * @author ozj
 * @date 2020-02-21 20:15
 */
public class Weather implements Serializable {

    private static final long serialVersionUID = 1L;

    private String city;
    private String aqi;
    private String wendu;
    private String ganmao;
    private Yesterday yesterday;
    private List<Forecast> forecast;

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getAqi() {
        return aqi;
    }
    public void setAqi(String aqi) {
        this.aqi = aqi;
    }
    public String getWendu() {
        return wendu;
    }
    public void setWendu(String wendu) {
        this.wendu = wendu;
    }
    public String getGanmao() {
        return ganmao;
    }
    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }
    public Yesterday getYesterday() {
        return yesterday;
    }
    public void setYesterday(Yesterday yesterday) {
        this.yesterday = yesterday;
    }
    public List<Forecast> getForecast() {
        return forecast;
    }
    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(city + yesterday.toString() + "今日的平均温度为" + wendu  +
                ", " + ganmao
        );
        sb.append("未来天气为:");
        for (Forecast forecast1 : forecast){
            sb.append(forecast1.toString());
        }
        return  sb.toString();
    }
}