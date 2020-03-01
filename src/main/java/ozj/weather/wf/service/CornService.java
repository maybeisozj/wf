package ozj.weather.wf.service;

import org.springframework.stereotype.Component;

/**
 * Corn服务类
 * @author ozj
 * @date 2020-02-25 13:55
 */
@Component
public class CornService {

    /**
     *
     * @param type 秒/分的类型，1 为 每秒/分，2 为 周期，3 为 从 temp 开始每cyclic秒/分执行一次，4 为指定
     * @param cyclic 循环时间
     * @param temp 开始时间
     * @param alls 所有的指定的值
     * @return
     */
    public String newSecondOrMinute(int type, int cyclic, int temp, int[] alls){
        StringBuilder sb = new StringBuilder();
        switch (type){
            case 2 :
                sb.append(temp + '-' + cyclic);
                break;
            case 3 :
                sb.append(temp + '/' + cyclic);
                break;
            case 4 :
                for (int t = 0;t < alls.length - 1 ;t++){
                    sb.append(alls[t] + ',');
                }
                sb.append(alls[alls.length - 1]);
                break;
            case 5 :
                sb.append(temp + 'W');
                break;
            case 6 :
                sb.append('L');
                break;
            case 1 :
            default:
                sb.append('*');
        }
        return sb.toString();
    }

    /**
     *
     * 方法摘要：构建Cron表达式
     *
     * @param rate  频率 0秒；1分；2小时；3日；4月
     * @param cycle 周期
     * @return String
     */
    public String createLoopCronExpression(int rate, int cycle) {
        String cron = "";
        switch (rate) {
            case 0:
                // 每cycle秒执行一次
                cron = "0/" + cycle + " * * * * ?";
                break;
            case 1:
                // 每cycle分钟执行一次
                cron = "0 0/" + cycle + " * * * ?";
                break;
            case 2:
                // 每cycle小时执行一次
                cron = "0 0 0/" + cycle + " * * ?";
                break;
            case 3:
                // 每cycle天的0点执行一次
                cron = "0 0 0 1/" + cycle + " * ?";
                break;
            case 4:
                // 每cycle月的1号0点执行一次
                cron = "0 0 0 1 1/" + cycle + " ? ";
                break;
            case 5:
                //  每天cycle点执行一次
                cron = "0 0 " + cycle+ "  * * ?";
                break;
            default:
                // 默认每cycle分执行一次
                cron = "0 0/1 * * * ?";
                break;
        }
        return cron;
    }



}
