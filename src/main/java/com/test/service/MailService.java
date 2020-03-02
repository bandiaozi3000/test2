package com.test.service;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName:MailService
 * @Description:邮件Service
 * @Author:lm.sun
 * @Date:2019/9/12 14:20
 */
public interface MailService {

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
    void sendSimpleMail(String to, String subject, String content);

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
     void sendAttachmentsMail(String filePath, String to, String subject, String content) throws UnsupportedEncodingException;
}
