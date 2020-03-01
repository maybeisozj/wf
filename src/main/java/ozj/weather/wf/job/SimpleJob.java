package ozj.weather.wf.job;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 简单任务
 * @author ozj
 * @date 2020-02-27
 */
@Component
public class SimpleJob {
    public void sayHello(){
        System.out.println("SimpleJob : hello!" + new Date());
    }
}
