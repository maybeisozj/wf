package ozj.weather.wf.service;

import ozj.weather.wf.domain.WeatherResponse;

/**
 * 天气查询接口
 */
public interface WeatherDataService{

    /**
     * 根据城市ID查询天气数据
     * @param cityId
     * @return
     */
    WeatherResponse getDataByCityId(String cityId);

    /**
     * 根据城市名称查询天气数据
     * @param cityName
     * @return
     */
    WeatherResponse getDataByCityName(String cityName);

}