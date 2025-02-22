package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

@WebServlet("/sendingemail")
public class sendingemail extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Email credentials and settings (replace these with actual values)
    private static final String SMTP_HOST = "smtp.gmail.com"; // For Gmail
    private static final String SMTP_PORT = "587"; // For Gmail SMTP
    private static final String USERNAME = "test.emal@gmail.com"; // Your email address
    private static final String PASSWORD = "sxwm tihp ufrn woqv"; // Your email password (consider using app-specific password)
    
    public sendingemail() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Here, we can just display a form or handle other GET requests
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters (e.g. recipient email, subject, message text)
        String recipientEmail = request.getParameter("recipient");
        String subject = request.getParameter("subject");
        String messageText = request.getParameter("message");

        // Send email using JavaMail API
        try {
            sendEmail(recipientEmail, subject, messageText);
            response.getWriter().println("Email sent successfully.");
        } catch (MessagingException e) {
            response.getWriter().println("Error sending email: " + e.getMessage());
        }
    }

    // Method to send an email using JavaMail
    private void sendEmail(String recipientEmail, String subject, String messageText) throws MessagingException {
        // Set up properties for the email session
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Enable TLS
        
        // Create a session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        // Create a new email message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USERNAME)); // Sender's email
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail)); // Recipient's email
        message.setSubject(subject); // Subject
        message.setText(messageText); // Message text

        // Send the email
        Transport.send(message);
    }
}
