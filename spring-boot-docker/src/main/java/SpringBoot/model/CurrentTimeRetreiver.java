package SpringBoot.model;

import java.sql.Timestamp;

public class CurrentTimeRetreiver {

    public static Timestamp getCurrentTime(){
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        return timestamp;
    }

}
