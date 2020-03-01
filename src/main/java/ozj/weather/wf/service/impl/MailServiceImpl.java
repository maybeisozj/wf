package ozj.weather.wf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ozj.weather.wf.service.MailService;

/**
 * 邮件服务实现类
 * @author ozj
 * @date 2020-02-28 19:00
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender javaMailSender;

    private final static String sender = "1145790078@qq.com";

    @Override
    public void sendSimpleMail(String to,String cc,String subject,String content){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(to);
        if (cc != null){
            simpleMailMessage.setCc(cc);
        }
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        javaMailSender.send(simpleMailMessage);
    }

    /**
     * 发邮件验证邮箱功能
     *
     * @param to      邮件接收者
     * @param subject 主题
     * @param content 内容
     */
    @Override
    public void sendMailForTest(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        javaMailSender.send(simpleMailMessage);
    }
}
