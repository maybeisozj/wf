package ozj.weather.wf.core;

import ozj.weather.wf.config.ApplicationContextProvider;
import ozj.weather.wf.service.MailService;


/**
 * 邮件发送线程类
 * @author ozj
 * @date 2020-02-28
 */
public class MailThread implements  Runnable {

    /**
     * 获取bean
     */
    private MailService mailService = (MailService) ApplicationContextProvider.getBean(MailService.class);

    String to;
    String subject;
    String content;
    public MailThread( String to, String subject, String content) {
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    @Override
    public void run() {
        mailService.sendSimpleMail(to,null,subject,content);
    }
}
