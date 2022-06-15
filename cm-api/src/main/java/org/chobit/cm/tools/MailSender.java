package org.chobit.cm.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author zhangrui137
 */
public final class MailSender {


    private static final Logger logger = LoggerFactory.getLogger(MailSender.class);


    private static final JavaMailSenderImpl SENDER = createSender();


    /**
     * 支持一次发送多个附件
     *
     * @param subject  标题
     * @param text     内容
     * @param receiver 邮件接收人
     * @param files    <文件名，文件>集合
     */
    public static void send(String subject, String text, String[] receiver, Map<String, Resource> files) {
        MimeMessage message = SENDER.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
            helper.setFrom(FROM_EMAIL);
            helper.setTo(receiver);
            helper.setText(text, false);
            helper.setSubject(subject);
            for (Map.Entry<String, Resource> entry : files.entrySet()) {
                helper.addAttachment(entry.getKey(), entry.getValue());
            }

            SENDER.send(message);
        } catch (Exception e) {
            logger.error("邮件发送异常! subject:{},receiver：{}", subject, receiver, e);
        }
    }


    public static void send(String subject, String text, String receiver, String fileName, Resource file) {
        HashMap<String, Resource> files = new HashMap<>(2);
        files.put(fileName, file);
        send(subject, text, new String[]{receiver}, files);
    }


    public static void send(String subject, String text, String[] receivers, String fileName, Resource file) {
        HashMap<String, Resource> files = new HashMap<>(2);
        files.put(fileName, file);
        send(subject, text, receivers, files);
    }


    public static void send(String subject, String text, String... receiver) {
        //send(subject, text, receiver, new HashMap<>(0));
    }


    private static final String USERNAME = "robinZhang";
    private static final String PASSWORD = "thisIsPassword";
    private static final String FROM_EMAIL = "admin@chobit.org";
    private static final String HOST = "smtp.chobit.org";

    private static JavaMailSenderImpl createSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(HOST);
        sender.setPort(27);
        sender.setUsername(USERNAME);
        sender.setPassword(PASSWORD);

        Properties config = new Properties();
        config.put("mail.smtp.auth", "true");
        config.put("mail.smtp.starttls.enable", "false");
        config.put("mail.smtp.starttls.required", "false");
        config.put("mail.smtp.timeout", 5000);
        sender.setJavaMailProperties(config);
        return sender;
    }


    private MailSender() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed.");
    }

}
