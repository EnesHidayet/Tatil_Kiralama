package org.enes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {
    private final JavaMailSender javaMailSender;

    public void sendMail(String activationCode){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("eneshidayet@gmail.com");
        mailMessage.setTo("eneshidayet@gmail.com"); // burasi development tamamlandiginda kullanici mailiyle degistirilecek.
        mailMessage.setSubject("AKTIVASYON KODUNUZ...");
        mailMessage.setText("Merhaba aramıza hoşgeldin!\n"+"Holidaydeki hesabınızı doğrulamak için aktivasyon kodunuz: "+activationCode);
        javaMailSender.send(mailMessage);
    }


}
