package com.elinext.test.util;

import com.elinext.test.domain.Reservation;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@Data
@Component
public class TimeUtil {

    public boolean compareTime(Time start, Time end, List<Reservation> sortedList) {

        Time[] startArray = new Time[sortedList.size()-1];
        Time[] endArray = new Time[sortedList.size()-1];

        for (int i=1; i<sortedList.size(); i++){
            startArray[i-1] = sortedList.get(i).getStartTime();
            endArray[i-1] = sortedList.get(i-1).getEndTime();
        }

        if(end.after(start) && start.after(Time.valueOf(LocalTime.now()))) {
            if(end.before(sortedList.get(0).getStartTime()) || start.after(sortedList.get(sortedList.size()-1).getEndTime())){
                return true;
            } else {
                for(int i=0; i<startArray.length; i++) {
                    if(start.after(endArray[i]) && end.before(startArray[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
