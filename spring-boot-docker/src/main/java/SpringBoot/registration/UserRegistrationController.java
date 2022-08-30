package SpringBoot.registration;

import SpringBoot.Exception.ResourceNotFoundException;
import SpringBoot.appuser.AppUser;
import SpringBoot.appuser.UserService;
import SpringBoot.model.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;
    private final UserService userService;

    @PostMapping
    public String register(@RequestBody UserRegistrationRequest request) {
        return userRegistrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return userRegistrationService.confirmToken(token);
    }

    @ApiOperation(value = "Get a user by id", notes = "Returns the user as per the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The user was not found")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<AppUser> getUser(@PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(userService.findAppUserById(userId));
    }

    @ApiOperation(value = "Get all users", notes = "Returns the list of users in the DB")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - unable to fetch users")
    })
    @GetMapping("/allUsers")
    public List<AppUser> getAllUsers(){
        return this.userService.getAllUsers();
    }

    @ApiOperation(value = "Get all list of this user", notes = "Returns the lists for this userId in the DB")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The user was not found")
    })
    @GetMapping("/getLists/user/{userId}")
    public Set<ToDoList> getListsByUser(@PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {
        return this.userService.getListsByUser(userId);
    }

    @ApiOperation(value = "Create a tag", notes = "Creates a tag for the user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tag creation Successfull"),
            @ApiResponse(code = 404, message = "Unable to retreive the user id")
    })
    @PostMapping("/tag")
    public Tag addTag(@RequestBody AddTagRequest addTagRequest) throws ResourceNotFoundException {

        return this.userService.addTag(addTagRequest);
    }

    @ApiOperation(value = "Rename a tag", notes = "Renames a tag for to the user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tag renaming Successfull"),
            @ApiResponse(code = 404, message = "Unable to retreive the tagId")
    })
    @PutMapping("/renameTag/{tagId}/tagName/{name}")
    public Tag renameTag(@PathVariable(value = "tagId") Long tagId,
                         @PathVariable(value = "name") String tagName) throws ResourceNotFoundException {

        return this.userService.renameTag(tagId,tagName);
    }

    @ApiOperation(value = "Get all tags for user", notes = "Returns the list of all tags created by this user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - unable to fetch user")
    })
    @GetMapping("/Tags/{userId}")
    public Set<Tag> getAllTags(@PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {
        return this.userService.getAllTags(userId);
    }

}
