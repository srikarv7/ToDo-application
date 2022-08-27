package SpringBoot.controller;

import SpringBoot.Exception.ResourceNotFoundException;
import SpringBoot.Repository.ListRepository;
import SpringBoot.Repository.TagRepository;
import SpringBoot.Repository.UserRepository;
import SpringBoot.model.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1/user")
@ApiOperation("User/Tag registration/access API")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ListRepository listRepository;

    @Autowired
    private TagRepository tagRepository;

    @ApiOperation(value = "Register a user", notes = "Registers a user to the application")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registration Successfull")
//            @ApiResponse(code = 404, message = "")
    })
    @PostMapping("/")
    public UserTable addUser(@RequestBody AddUserRequest addUserRequest) {

        UserTable user = new UserTable();
        user.setFirstName(addUserRequest.getFirstName());
        user.setMiddleName(addUserRequest.getMiddleName());
        user.setLastName(addUserRequest.getLastName());
        user.setEmail(addUserRequest.getEmail());

        this.userRepository.save(user);

        ToDoList list = new ToDoList();
        list.setGuser(user);
        list.setListName("Default list");
        list.setCreated(CurrentTimeRetreiver.getCurrentTime());
        list.setUpdated(CurrentTimeRetreiver.getCurrentTime());

        this.listRepository.save(list);

        return user;
    }

    @ApiOperation(value = "DEPRECATED validate the email id of the user", notes = "***AISH IS DOING THIS*** This api takes the token string and validates the email id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully validated email id"),
            @ApiResponse(code = 404, message = "Unsucessfull validation")
    })
    @GetMapping("/validate/userId/{userId}/email/{emailId}")
    public String validateEmail(@PathVariable(value = "emailId") String email,
                                 @PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {

        UserTable user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("The user not found with userId"+userId));

        if(user.getEmail().equals(email)){

            ToDoList toDoList = new ToDoList();
            toDoList.setGuser(user);
            toDoList.setListName("Default List");
            listRepository.save(toDoList);
            return "Email successfully validated";
        }else{
            return "Email not found/validated";
        }

    }

    @ApiOperation(value = "Update the email of the user", notes = "Update the email address of the user based on token validation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated")
//            ,@ApiResponse(code = 404, message = "")
    })
    @PutMapping("/changeEmail/userid/{userId}/newEmail/{newEmail}")
    public ResponseEntity<UserTable> changeEmail(@PathVariable(value = "userId") Long userId,
                                                 @PathVariable(value = "newEmail") String newEmail)
            throws ResourceNotFoundException {

        UserTable user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("The user not found with userId"+userId));

        user.setEmail(newEmail);
        return ResponseEntity.ok(this.userRepository.save(user));
    }

    @ApiOperation(value = "Get a user by id", notes = "Returns the user as per the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The user was not found")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<UserTable> getUser(@PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {

        UserTable user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("The user not found with userId"+userId));

        return ResponseEntity.ok().body(user);
    }

    //Below api is internal and hidden from documentation
    @ApiOperation(value = "Get all users", notes = "Returns the list of users in the DB")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - unable to fetch users")
    })
    @GetMapping("/allUsers")
    public List<UserTable> getAllUsers(){
        return this.userRepository.findAll();
    }

    @ApiOperation(value = "Get all list of this user", notes = "Returns the lists for this userId in the DB")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The user was not found")
    })
    @GetMapping("/getLists/user/{userId}")
    public Set<ToDoList> getListsByUser(@PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {
        UserTable user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("The user not found with userId"+userId));

        return user.getLists();
    }

    @ApiOperation(value = "Create a tag", notes = "Creates a tag for the user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tag creation Successfull"),
            @ApiResponse(code = 404, message = "Unable to retreive the user id")
    })
    @PostMapping("/tag")
    public Tag addTag(@RequestBody AddTagRequest addTagRequest) throws ResourceNotFoundException {

        UserTable user = userRepository.findById(addTagRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("The user not found with userId"+
                        addTagRequest.getUserId()));

        Tag tag = new Tag();
        tag.setCreated(CurrentTimeRetreiver.getCurrentTime());
        tag.setTuser(user);
        tag.setName(addTagRequest.getName());

        return this.tagRepository.save(tag);
    }

    @ApiOperation(value = "Rename a tag", notes = "Renames a tag for to the user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tag renaming Successfull"),
            @ApiResponse(code = 404, message = "Unable to retreive the tagId")
    })
    @PutMapping("/renameTag/{tagId}/tagName/{name}")
    public Tag renameTag(@PathVariable(value = "tagId") Long tagId,
                         @PathVariable(value = "name") String tagName) throws ResourceNotFoundException {

        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("The user not found with userId"+
                        tagId));

        tag.setName(tagName);
        return this.tagRepository.save(tag);
    }

    @ApiOperation(value = "Get all tags for user", notes = "Returns the list of all tags created by this user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - unable to fetch user")
    })
    @GetMapping("/Tags/{userId}")
    public Set<Tag> getAllTags(@PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {
        UserTable user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("The user not found with userId"+userId));
        return user.getTags();
    }

}
