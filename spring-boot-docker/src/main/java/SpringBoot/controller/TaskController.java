package SpringBoot.controller;

import SpringBoot.Exception.ResourceNotFoundException;
import SpringBoot.Repository.*;
import SpringBoot.model.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/task")
@ApiOperation("task/attachment creation/manipulation api's")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @ApiOperation(value = "Create a task", notes = "Creates a task for the list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved the list"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @PostMapping("/")
    public Task addTask(@RequestBody AddTaskRequest addTaskRequest) throws ResourceNotFoundException {

        ToDoList toDoList = listRepository.findById(addTaskRequest.getListId())
                .orElseThrow(() -> new ResourceNotFoundException("The list not found with listId"+addTaskRequest.getListId()));

        Task task = new Task();
        task.setTaskName(addTaskRequest.getTaskName());
        task.setState("ToDo");
        task.setCreated(CurrentTimeRetreiver.getCurrentTime());
        task.setUpdated(CurrentTimeRetreiver.getCurrentTime());
        task.setDueDate(CurrentTimeRetreiver.getCurrentTime());
        task.setSummary(addTaskRequest.getSummary());
        task.setPriority(addTaskRequest.getPriority());
        task.setToDoList(toDoList);

        return this.taskRepository.save(task);
    }

    @ApiOperation(value = "Moves task to a list", notes = "Moves task from existing list to the given Target ListId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved the list"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @PutMapping("/move/task/{taskId}/list/{targetListId}")
    public ResponseEntity<Task> moveTask(@PathVariable(value = "taskId") Long taskId,
                                                   @PathVariable(value = "targetListId") Long listId)
            throws ResourceNotFoundException {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("The task not found with taskId"+taskId));

        ToDoList toDoList = listRepository.findById(listId)
                .orElseThrow(() -> new ResourceNotFoundException("The list not found with listId"+listId));

        task.setUpdated(CurrentTimeRetreiver.getCurrentTime());
        task.setToDoList(toDoList);

        return ResponseEntity.ok(this.taskRepository.save(task));
    }

    @ApiOperation(value = "Updates the task status to Completed", notes = "Changes the task's status from ToDo to Completed")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved the list"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @PutMapping("/statusUpdate/task/{taskId}")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable(value = "taskId") Long taskId)
            throws ResourceNotFoundException {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("The task not found with taskId"+taskId));

        task.setUpdated(CurrentTimeRetreiver.getCurrentTime());
        task.setState("Completed");

        return ResponseEntity.ok(this.taskRepository.save(task));
    }

    @ApiOperation(value = "Updates the task due date", notes = "This api changes the due date od the task and changes the status accordingly")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully changed the due date"),
            @ApiResponse(code = 404, message = "Task not found")
    })
    @PutMapping("/dueUpdate/")
    public ResponseEntity<Task> updateTaskDue(@RequestBody UpdateTaskDueRequest request)
            throws ResourceNotFoundException {

        Task task = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("The task not found with taskId"+request.getTaskId()));

        task.setUpdated(CurrentTimeRetreiver.getCurrentTime());
        task.setDueDate(request.getNewDue());

        return ResponseEntity.ok(this.taskRepository.save(task));
    }

    @ApiOperation(value = "Get all tasks", notes = "Returns the list of tasks in the DB")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - unable to fetch tasks")
    })
    @GetMapping("/allTasks")
    public List<Task> getAllTasks(){
        return this.taskRepository.findAll();
    }

    @ApiOperation(value = "Create an attachment for a task", notes = "Creates a task for the list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved the list"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @PostMapping("/addAttachment")
    public Attachment addAttachment(@RequestBody AddAttachmentRequest addAttachmentRequest) throws ResourceNotFoundException {

        Task task = taskRepository.findById(addAttachmentRequest.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("The task not found with taskId"+addAttachmentRequest.getTaskId()));

        Attachment attachment = new Attachment();
        attachment.setAttachmentName(addAttachmentRequest.getAttachmentName());
        attachment.setCreated(CurrentTimeRetreiver.getCurrentTime());
        attachment.setGtask(task);

        return this.attachmentRepository.save(attachment);
    }

    @ApiOperation(value = "delete an attachment for a task", notes = "Deletes the attachment given attachment id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted the attachment"),
            @ApiResponse(code = 404, message = "Attachment not found")
    })
    @DeleteMapping("/deleteAttachment/{attachmentId}")
    public void deleteAttachment(@PathVariable(value = "attachmentId") Long attachmentId) throws ResourceNotFoundException {

        Attachment attachment = attachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new ResourceNotFoundException("The attachment not found with attachmentId"+attachmentId));

        attachmentRepository.delete(attachment);
    }

    @ApiOperation(value = "get attachment by id", notes = "Gets the attachment for the given attachment id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retreived the attachment"),
            @ApiResponse(code = 404, message = "Attachment not found")
    })
    @GetMapping("/attachment/{attachmentId}")
    public ResponseEntity<Attachment> getAttachment(@PathVariable(value = "attachmentId") Long attachmentId) throws ResourceNotFoundException {

        Attachment attachment = attachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new ResourceNotFoundException("The attachment not found with attachmentId"+attachmentId));


        return ResponseEntity.ok().body(attachment);
    }

}
