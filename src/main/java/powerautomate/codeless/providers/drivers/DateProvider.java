package powerautomate.codeless.providers.drivers;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateProvider {
    private static DateProvider dateProvider;
    private DateProvider(){

    }
    public String getCurrentDate(SimpleDateFormat formatter){
        Date date = new Date();
        return formatter.format(date);
    }
    public static DateProvider getInstance(){
        if (dateProvider==null){
            synchronized (DateProvider.class){
                if (dateProvider==null){
                    return new DateProvider();
                }
            }
        }
        return dateProvider;
    }
}
