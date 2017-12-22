package Pages.Settings;

import org.openqa.selenium.WebDriver;

import javax.activation.DataHandler;
import javax.mail.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadMail {
    private WebDriver driver;
    public static String verify;

    public ReadMail(WebDriver driver) {
        this.driver = driver;
    }

    public void displayAllMessagesFromGmail(String email, String password) {
        Properties props = new Properties();

        try {
            props.load(new FileInputStream(new File("/Users/workrocksQA/BB_AutoTests/One2Print/src/main/resources/smtp.properties")));

            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect("smtp.gmail.com", email, password);

            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);
            int messageCount = inbox.getMessageCount();

            Message[] messages = inbox.getMessages();
            System.out.println("------------------------------");

            for (int i = 0; i < messageCount; i++) {
                System.out.println("ReadMail Subject:- " + messages[i].getSubject());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openRegisterMessageAndGotoLink(String email, String password){
        Properties props = new Properties();

        try {
            props.load(new FileInputStream(new File("/Users/workrocksQA/BB_AutoTests/One2Print/src/main/resources/smtp.properties")));

            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect("smtp.gmail.com", email, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();

            verify = messages[0].getContent().toString();

            driver.get(extractUrlsFromString(verify).get(0));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openRecoveryMessageAndGotoLink(String email, String password){
        Properties props = new Properties();

        try {
            props.load(new FileInputStream(new File("/Users/workrocksQA/BB_AutoTests/One2Print/src/main/resources/smtp.properties")));

            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect("smtp.gmail.com", email, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();

            Multipart mp = (Multipart) messages[0].getContent();
            BodyPart bp = mp.getBodyPart(0);

            driver.get(String.valueOf(extractUrlsFromObject(bp.getContent()).get(1)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearAllMessages(String email, String password){
        Properties props = new Properties();

        try {
            props.load(new FileInputStream(new File("/Users/workrocksQA/BB_AutoTests/One2Print/src/main/resources/smtp.properties")));

            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect("smtp.gmail.com", email, password);

            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_WRITE);
            int messageCount = inbox.getMessageCount();

            Message[] messages = inbox.getMessages();

            for(int i = 0; i < messageCount; i++){
                messages[i].setFlag(Flags.Flag.DELETED, true);

            }

            inbox.close(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static List<String> extractUrlsFromString(String input) {
        List<String> result = new ArrayList<String>();

        Pattern pattern = Pattern.compile(
                "\\b(((ht|f)tp(s?)\\:\\/\\/|~\\/|\\/)|www.)" +
                        "(\\w+:\\w+@)?(([-\\w]+\\.)+(com|org|net|gov" +
                        "|mil|biz|info|mobi|name|aero|jobs|museum" +
                        "|travel|[a-z]{2}))(:[\\d]{1,5})?" +
                        "(((\\/([-\\w~!$+|.,=]|%[a-f\\d]{2})+)+|\\/)+|\\?|#)?" +
                        "((\\?([-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" +
                        "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)" +
                        "(&(?:[-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" +
                        "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)*)*" +
                        "(#([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)?\\b");

        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }

    public static List<String> extractUrlsFromObject(Object input) {
        List<String> result = new ArrayList<String>();

        Pattern pattern = Pattern.compile(
                "\\b(((ht|f)tp(s?)\\:\\/\\/|~\\/|\\/)|www.)" +
                        "(\\w+:\\w+@)?(([-\\w]+\\.)+(com|org|net|gov" +
                        "|mil|biz|info|mobi|name|aero|jobs|museum" +
                        "|travel|[a-z]{2}))(:[\\d]{1,5})?" +
                        "(((\\/([-\\w~!$+|.,=]|%[a-f\\d]{2})+)+|\\/)+|\\?|#)?" +
                        "((\\?([-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" +
                        "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)" +
                        "(&(?:[-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" +
                        "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)*)*" +
                        "(#([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)?\\b");

        Matcher matcher = pattern.matcher(input.toString());
        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }


}


