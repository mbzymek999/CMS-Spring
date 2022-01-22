package cms.external.email.facade;

public interface EmailServiceFacade {
    void sendEmail(String to, String subject, String body);
}
