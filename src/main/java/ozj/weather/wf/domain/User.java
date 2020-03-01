package ozj.weather.wf.domain;

/**
 * 用户信息类
 * @author ozj
 * @date 2020-02-21 21:34
 */
public class User {

    private int id;

    private String no;

    private String name;

    private String email;

    private String password;

    public User(){}

    public User(String no, String name, String email, String password) {
        this.no = no;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(int id, String no, String name, String email, String password) {
        this.id = id;
        this.no = no;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
