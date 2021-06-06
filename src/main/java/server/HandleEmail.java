package server;

import javax.mail.MessagingException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandleEmail {

    private String email;
    private int code;

    public HandleEmail(String email) {
        this.email = email;
    }

    //public boolean isEmailNotUsed() {
        //Database
    //}

    public  boolean isValidEmail() {
        final String emailRegex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    private void codeGenerator() {
        Random random = new Random();
        int tmp = 0;

        for (int i = 0; i < 6; i++) {
            tmp *= 10;
            tmp += random.nextInt(8) + 1;
        }
        this.code = tmp;
    }

    public void sendEmailCode() throws MessagingException {
        String emailMessage1 = "this is your verification code : ";
        String emailMessage2 = ".\n thank you for choosing instagram.";

        codeGenerator();
        GoogleMail.Send(email, "Verification Code",  emailMessage1 + code + emailMessage2);
    }

    public boolean isVerified(String inputCode) throws MessagingException {
        sendEmailCode();

        return (Integer.parseInt(inputCode) == code);
    }

}
