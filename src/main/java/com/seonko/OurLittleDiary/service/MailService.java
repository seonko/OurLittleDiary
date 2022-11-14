package com.seonko.OurLittleDiary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private String eCode;

    private String createCode() {
        StringBuffer code = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) {
            int index = rnd.nextInt(3);

            switch (index) {
                case 0:
                    code.append((char)(rnd.nextInt(26) + 97));
                    break;
                case 1:
                    code.append((char)(rnd.nextInt(26) + 65));
                    break;
                case 2:
                    code.append((rnd.nextInt(10)));
                    break;
            }
        }

        return code.toString();
    }

    public String sendMessage(String to) throws Exception {
        eCode = createCode();
        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);
        message.setSubject("[Our Little Diary] 이메일 인증 코드 발송");

        String msg = "";
        msg += "<div style='margin:100px;'>";
        msg += "<h1> Our Little Diary 이메일 인증 코드 발송 </h1>";
        msg += "<hr>";
        msg += "<br>";
        msg += "안녕하십니까? Our Little Diary입니다.";
        msg += "<br>";
        msg += "회원가입 페이지의 인증 코드 입력란에 아래 6자리 코드를 입력해 주세요.";
        msg += "<br>";
        msg += "<h3>";
        msg += eCode;
        msg += "</h3>";
        msg += "</div>";

        message.setText(msg, "utf-8", "html");

        message.setFrom(new InternetAddress("ourlittlediaryofficial@gmail.com", "Our Little Diary"));

        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }

        return eCode;
    }


}
