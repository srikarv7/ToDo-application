package SpringBoot.registration;

import SpringBoot.Exception.ResourceNotFoundException;
import SpringBoot.appuser.AppUser;
import SpringBoot.appuser.UserService;
import SpringBoot.model.ToDoList;
import SpringBoot.model.UserTable;
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
        UserTable user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("The user not found with userId"+userId));

        return user.getLists();
    }



}
