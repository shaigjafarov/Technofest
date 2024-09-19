package az.technofest.schedule;

import java.time.LocalTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class AutopaymentSchedule {


    @Scheduled(cron = "0/5 * * * * *")
    void pay()
    {

        System.out.println(LocalTime.now());
    }



}
