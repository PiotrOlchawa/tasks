package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.MailMessageType;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTest {
    @InjectMocks
    private EmailScheduler scheduler;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AdminConfig adminConfig;


    @Test
    public void sendInformationEmail() {
        //Given
        Mail expected = new Mail("test@test.com",
                "Tasks: Once a day email",
                "Currently in database you got: 1 task");

        when(taskRepository.count()).thenReturn(1L);
        when(adminConfig.getAdminMail()).thenReturn(expected.getMailTo());

        //When
        scheduler.sendInformationEmail();

        System.out.println();

        //Then
        verify(simpleEmailService, times(1)).send(expected, MailMessageType.SCHEDULE);
        //verify(simpleEmailService, times(1)).send(expected);
    }

}