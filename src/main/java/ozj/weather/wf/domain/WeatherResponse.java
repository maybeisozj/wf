package ozj.weather.wf.domain;

/**
 * weather接口返回信息接收类
 * @author ozj
 * @date 2020-02-21 20:15
 */
public class WeatherResponse{

    /**
     * 消息数据
     */
    private Weather data;

    /**
     * 消息状态
     */
    private String status;

    /**
     * 消息描述
     */
    private String desc;

    public WeatherResponse(){ }

    public WeatherResponse(Weather data, String status, String desc) {
        this.data = data;
        this.status = status;
        this.desc = desc;
    }

    public Weather getData() {
        return data;
    }

    public void setData(Weather data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}