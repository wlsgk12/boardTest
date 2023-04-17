package org.koreait.schedulers;

import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log
public class LoggingScheduler {

    @Scheduled(cron="* * 1 * * *")
    public void logging(){
        log.info("새벽 1시마다 실행");
    }
}
