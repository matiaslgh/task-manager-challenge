package com.app.controllers;

import com.app.tasks.models.TaskData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Allows to send a {@link TaskData} to all the subscribed clients via WebSockets
 */
@Controller
public class TaskDataController {

    @Autowired
    private SimpMessagingTemplate template;

    public void notifyViaWebSocket(TaskData taskData) {
        this.template.convertAndSend("/topic/taskUpdate", taskData);
    }
}
