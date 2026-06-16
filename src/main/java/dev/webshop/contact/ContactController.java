package dev.webshop.contact;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/contact")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<String> send(@RequestBody @Valid ContactRequest request) throws MessagingException {
        try {
            contactService.sendContactMessage(request);
            return ResponseEntity.ok("Bericht verzonden");
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body("Er ging iets mis bij het verzenden van je bericht");
        }
    }
}