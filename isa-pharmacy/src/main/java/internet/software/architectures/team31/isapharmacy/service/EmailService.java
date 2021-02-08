package internet.software.architectures.team31.isapharmacy.service;

public interface EmailService {

	void sendEmail(String to, String subject, String text);
}
