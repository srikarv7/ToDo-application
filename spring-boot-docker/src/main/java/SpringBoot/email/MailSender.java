package SpringBoot.email;

public interface MailSender {
    void send(String to, String email);
}
