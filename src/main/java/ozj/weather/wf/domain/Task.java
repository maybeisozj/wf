package ozj.weather.wf.domain;

/**
 * 
 * @author ozj
 * @date 2020-02-28
 */
public class Task {

    private int id;
    
    private User own;

    private String triggerName;

    private String triggerGroup;

    private String cityId;

    private String cityName;

    public Task(){}

    public Task(User own, String triggerName, String triggerGroup) {
        this.own = own;
        this.triggerName = triggerName;
        this.triggerGroup = triggerGroup;
    }

    public Task(User own, String triggerName, String triggerGroup, String cityName) {
        this.own = own;
        this.triggerName = triggerName;
        this.triggerGroup = triggerGroup;
        this.cityName = cityName;
    }

    public Task(User own, String triggerName, String triggerGroup, String cityId, String cityName) {
        this.own = own;
        this.triggerName = triggerName;
        this.triggerGroup = triggerGroup;
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public Task(int id, User own, String triggerName, String triggerGroup, String cityId, String cityName) {
        this.id = id;
        this.own = own;
        this.triggerName = triggerName;
        this.triggerGroup = triggerGroup;
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwn() {
        return own;
    }

    public void setOwn(User own) {
        this.own = own;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }
}
