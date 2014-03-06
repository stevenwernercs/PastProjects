package team2awesome.subsystem.profile;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	private static String USER_NAME = "senqualsystem"; // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "senqualsystem!"; // GMail password
    //private static String RECIPIENT = "pepperplume@gmail.com";

    
    public Mail()
    {}
    
    public void send(String recipient, String subject, String body) 
	{
		class MyEmailTread implements Runnable 
		{
			String recipient;
			String subject;
			String body;
			
			public MyEmailTread(String recipient, String subject, String body)
			{
				this.recipient = recipient;
				this.subject = subject;
				this.body = body;
			}
			
			public void run() 
			{
				System.out.println("SENDING...");
				sendOff(recipient, subject, body);
			}
		}
		
		Runnable r = new MyEmailTread(recipient, subject, body);
		new Thread(r).start();
	}
    
    
    
    
    
    public void sendOff(String recipient, String subject, String body) {
        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { recipient }; // list of recipient email addresses
        //String subject = "Java send mail example";
        //String body = "Welcome to JavaMail!";

        System.out.println("SENDING.........");
        sendFromGMail(from, pass, to, subject, body);
    }

    private void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (Exception e) {
        	System.out.println("############ Exception");
            e.printStackTrace();
        }
    }

}
