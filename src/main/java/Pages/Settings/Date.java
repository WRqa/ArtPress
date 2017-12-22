package Pages.Settings;

import java.text.SimpleDateFormat;

public class Date {

    private java.util.Date today = new java.util.Date();
    public String getDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(dateFormat.format(today));
        return dateFormat.format(today);
    }
}
