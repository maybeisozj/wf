package ozj.weather.wf.service;

import org.springframework.stereotype.Service;

/**
 * 邮件发送服务类
 * @author ozj
 * @date 2020-02-21 21:40
 */
public interface MailService {


    /**
     * 发邮件功能
     * @param to 邮件接收者
     * @param cc 抄送人
     * @param subject 主题
     * @param content 内容
     */
    public void sendSimpleMail(String to,String cc,String subject,String content);

    /**
     * 发邮件验证邮箱功能
     * @param to 邮件接收者
     * @param subject 主题
     * @param content 内容
     */
    public void sendMailForTest(String to,String subject,String content);
}
