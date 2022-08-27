package SpringBoot.controller;

import SpringBoot.Exception.ResourceNotFoundException;
import SpringBoot.Repository.ListRepository;
import SpringBoot.Repository.UserRepository;
import SpringBoot.model.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v1/list")
@ApiOperation("List creation/manipulation api's")
public class ListController {

    @Autowired
    private ListRepository listRepository;
    @Autowired
    private UserRepository userRepository;


    @ApiOperation(value = "Create a list", notes = "Creates a list for the user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved the list"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @PostMapping("/")
    public ToDoList addList(@RequestBody AddListRequest addListRequest) throws ResourceNotFoundException {

        UserTable user = userRepository.findById(addListRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("The user not found with userId"+
                        addListRequest.getUserId()));

        ToDoList list = new ToDoList();
        list.setGuser(user);
        list.setListName(addListRequest.getListName());
        list.setCreated(CurrentTimeRetreiver.getCurrentTime());
        list.setUpdated(CurrentTimeRetreiver.getCurrentTime());

        return this.listRepository.save(list);
    }

    @ApiOperation(value = "Update a list name", notes = "Updates a list name for the user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated the list"),
            @ApiResponse(code = 404, message = "List with list Id not found")
    })
    @PutMapping("/update/list/{listId}/name/{listName}")
    public ResponseEntity<ToDoList> updateList(@PathVariable(value = "listId") Long listId,
                                               @PathVariable(value = "listName") String listName
        ) throws ResourceNotFoundException {

        ToDoList toDoList = listRepository.findById(listId)
                .orElseThrow(() -> new ResourceNotFoundException("The list not found with listId"+listId));

        toDoList.setListName(listName);
        toDoList.setUpdated(CurrentTimeRetreiver.getCurrentTime());
        return ResponseEntity.ok(this.listRepository.save(toDoList));
    }

    @ApiOperation(value = "Get all lists", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "")
    })
    @GetMapping("/allLists")
    public java.util.List<ToDoList> getAllLists(){
        return this.listRepository.findAll();
    }

    @ApiOperation(value = "Get lists by listId", notes = "Retreives a list by the listId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The list was not found")
    })
    @GetMapping("/{listId}")
    public ResponseEntity<ToDoList> getList(@PathVariable(value = "listId") Long listId)
            throws ResourceNotFoundException {

        ToDoList toDoList = listRepository.findById(listId)
                .orElseThrow(() -> new ResourceNotFoundException("The list not found with listId"+listId));

        return ResponseEntity.ok().body(toDoList);

    }

    @ApiOperation(value = "Get tasks of a list", notes = "Returns all the tasks for this list in the DB")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The list was not found")
    })
    @GetMapping("/getTasks/{listId}")
    public Set<Task> getTasksByList(@PathVariable(value = "listId") Long listId) throws ResourceNotFoundException {
        ToDoList list = listRepository.findById(listId)
                .orElseThrow(() -> new ResourceNotFoundException("The list not found with listId"+listId));

        return list.getTasks();
    }


}
