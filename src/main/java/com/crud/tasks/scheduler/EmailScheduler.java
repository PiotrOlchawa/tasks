package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.MailMessageType;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    public final static String SUBJECT = "Tasks: Once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
    //@Scheduled(fixedDelay = 10000)
    public void sendInformationEmail() {
        long size = taskRepository.count();

        String correctWord;
        if (size == 1) {
            correctWord = " task";
        } else {
            correctWord = " tasks";
        }

        simpleEmailService.send(new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        "Currently database you got: " + size + correctWord),
                        MailMessageType.SCHEDULE
        );
    }
}
