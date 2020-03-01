package ozj.weather.wf.core;

/**
 * 返回类
 * @author ozj
 * @date 2020-02-29 11:06
 */
public class Response {

    String status ;
    String description;
    Object date;

    public Response(){}

    public Response(String status, String description) {
        this.status = status;
        this.description = description;
    }

    public Response(String status, String description, Object date) {
        this.status = status;
        this.description = description;
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }
}
