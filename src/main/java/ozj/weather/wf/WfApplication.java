package ozj.weather.wf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ozj
 * @date 2020-02-21 21:21
 * EnableScheduling 开启定时任务
 */
@EnableScheduling
@SpringBootApplication
@MapperScan("ozj.weather.wf.mapper")
public class WfApplication {

    public static void main(String[] args) {
        SpringApplication.run(WfApplication.class, args);
    }

}
