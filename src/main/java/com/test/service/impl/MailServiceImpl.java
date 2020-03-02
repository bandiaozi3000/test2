package com.test.service.impl;


import com.test.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * @ClassName:MailServiceImpl
 * @Description:邮件ServiceImpl
 * @Author:lm.sun
 * @Date:2019/9/12 14:20
 */
@Service
public class MailServiceImpl implements MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    /**
     * @param to
     * @param subject
     * @param content
     * @return void
     * @Description: 发送简单文本附件
     * @author f.hu@i-vpoints.com
     * @version
     * @date 2019-08-12 11:44:23
     * @throw
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("bcpay@i-vpoints.com");
        message.setTo(to.split(","));
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
        } catch (Exception e) {
            logger.error("邮件发送失败 : ", e);
        }
    }

    /**
     * @param filePath 附件路径
     * @param to
     * @param subject
     * @param content
     * @return void
     * @Description: 发送带附件的邮件
     * @author f.hu@i-vpoints.com
     * @version
     * @date 2019-08-12 11:45:43
     * @throw
     */
    @Override
    public void sendAttachmentsMail(String filePath, String to, String subject, String content) throws UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("bcpay@i-vpoints.com");
            helper.setTo(to.split(","));
            helper.setSubject(subject);
            helper.setText(content);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            helper.addAttachment("2020-02-23麦菲退款申请订单数据.xlsx", file);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error("", e);
        }

    }
}
