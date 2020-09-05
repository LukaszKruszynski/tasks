package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService service;

    @Mock
    private JavaMailSender sender;

    @Test
    public void shouldSendMail() {
        //Given
        Mail mail = new Mail("test@mail.pl", "test", "test", "test2@mail.pl");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setText(mail.getMessage());
        simpleMailMessage.setTo(mail.getMailTo());
        simpleMailMessage.setSubject(mail.getSubject());
        simpleMailMessage.setCc(mail.getToCc());

        //When
        service.send(mail);

        //Then
        Mockito.verify(sender, Mockito.times(1)).send(simpleMailMessage);
    }
}