package SpringBoot.controller;

import SpringBoot.Exception.ResourceNotFoundException;
import SpringBoot.Repository.ReminderRepository;
import SpringBoot.Repository.TaskRepository;
import SpringBoot.model.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/Reminder")
@ApiOperation("Reminder creation/manipulation api's")
public class ReminderController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ReminderRepository reminderRepository;

    @ApiOperation(value = "Create a reminder", notes = "Creates a reminder for the task")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved the list"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @PostMapping("/")
    public Reminder addReminder(@RequestBody AddReminderRequest addReminderRequest) throws ResourceNotFoundException {

        Task task = taskRepository.findById(addReminderRequest.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("The task not found with taskId"+
                        addReminderRequest.getTaskId()));

        Reminder reminder = new Reminder();
        reminder.setCreated(CurrentTimeRetreiver.getCurrentTime());
        reminder.setReminderTime(addReminderRequest.getReminderTime());
        reminder.setRtask(task);

        return this.reminderRepository.save(reminder);
    }

    @ApiOperation(value = "Delete a reminder", notes = "Deletes a reminder by it's Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted the reminder"),
            @ApiResponse(code = 404, message = "Reminder with reminderId not found")
    })
    @DeleteMapping("/{reminderId}")
    public void deleteComment(@PathVariable(value = "reminderId") Long reminderId) throws ResourceNotFoundException {

        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(() -> new ResourceNotFoundException("The reminder not found with reminderId"
                        +reminderId));

        reminderRepository.delete(reminder);
    }

}
