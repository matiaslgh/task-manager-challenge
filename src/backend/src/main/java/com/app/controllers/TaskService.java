package com.app.controllers;

import com.app.tasks.models.TaskData;
import com.app.tasks.TaskManager;
import com.app.tasks.models.TaskStatusView;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.controllers.TaskInputValidator.validateId;
import static com.app.controllers.TaskInputValidator.validateNumber;

/**
 * Provides the endpoints to handle the app via api REST
 */
@RestController
@CrossOrigin
@RequestMapping("/tasks")
public class TaskService {

    @Autowired
    private TaskManager taskManager;

    /**
     * Creates a {@link TaskData} via http POST request
     *
     * @param body json similar to {"number": 10}
     * @return {@link TaskData} as Json
     */
    @PostMapping
    public TaskData createTask(@RequestBody JsonNode body) {
        validateNumber(body);
        int number = body.get("number").asInt();
        return taskManager.createTask(number);
    }

    /**
     * Executes the task that matches with the provided id
     *
     * @param body json similar to {"id": "valid_id"}
     * @return http 200 if it is valid
     */
    @PostMapping(value="/run")
    public ResponseEntity<Object> runTask(@RequestBody JsonNode body) {
        validateId(body);
        String id = body.get("id").asText();
        taskManager.runTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * @return created tasks via http GET request
     */
    @GetMapping
    public List<TaskData> getTasks() {
        return taskManager.getTasks();
    }

    /**
     * @param body json similar to {"id": "valid_id"}
     * @return the {@link TaskData} that matches with the provided id
     */
    @GetMapping(value="/{id}")
    public TaskData getTask(@RequestBody JsonNode body) {
        validateId(body);
        String id = body.get("id").asText();
        return taskManager.getTask(id);
    }

    /**
     * @return a list with all the existing task statuses
     */
    @GetMapping(value="/statuses")
    public List<TaskStatusView> getStatuses() {
        return taskManager.getStatuses();
    }
}
