package dev.webshop.contact;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final JavaMailSender mailSender;
    private final String emailAddress;

    public ContactService(
            JavaMailSender mailSender,
            @Value("${spring.mail.username}") String emailAddress
    ) {
        this.mailSender = mailSender;
        this.emailAddress = emailAddress;
    }

    public void sendContactMessage(ContactRequest request) throws MessagingException {
        var message = new SimpleMailMessage();
        message.setFrom(emailAddress);
        message.setTo(emailAddress);
        message.setReplyTo(request.email());
        message.setSubject("Nieuw contactformulier");

        message.setText("""
                Naam: %s
                Email: %s

                Bericht:
                %s
                """
                .formatted(
                request.name(),
                request.email(),
                request.message()
        ));
        System.out.println("MAIL USER = " + emailAddress);
        System.out.println("ENV USER = " + System.getenv("SPRING_MAIL_USERNAME"));
        System.out.println("ENV PASS = " + System.getenv("SPRING_MAIL_PASSWORD"));
        mailSender.send(message);
    }
}