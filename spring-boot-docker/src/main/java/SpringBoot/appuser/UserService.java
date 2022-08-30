package SpringBoot.appuser;

import SpringBoot.Exception.ResourceNotFoundException;
import SpringBoot.Repository.TagRepository;
import SpringBoot.model.*;
import SpringBoot.registration.token.ConfirmationToken;
import SpringBoot.registration.token.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final UserTableRepository userTableRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenService tokenService;

    private final TagRepository tagRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userTableRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser appUser) {
        boolean userExists = userTableRepository
                .findByEmail(appUser.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        userTableRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        tokenService.saveConfirmationToken(
                confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }

    public int enableAppUser(String email) {
        return userTableRepository.enableAppUser(email);
    }

    public AppUser findAppUserById(Long userId) throws ResourceNotFoundException {
        AppUser user = userTableRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("The user not found with userId" + userId));
        return user;
    }

    public List<AppUser> getAllUsers(){
        return this.userTableRepository.findAll();
    }

    public Set<ToDoList> getListsByUser(Long userId) throws ResourceNotFoundException {
        AppUser user = userTableRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("The user not found with userId"+userId));

        return user.getLists();
    }

    public Tag addTag(AddTagRequest addTagRequest) throws ResourceNotFoundException {

        AppUser user = userTableRepository.findById(addTagRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("The user not found with userId"+
                        addTagRequest.getUserId()));

        Tag tag = new Tag();
        tag.setCreated(CurrentTimeRetreiver.getCurrentTime());
        tag.setTappUser(user);
        tag.setName(addTagRequest.getName());

        return this.tagRepository.save(tag);
    }

    public Tag renameTag(Long tagId,
                         String tagName) throws ResourceNotFoundException {

        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("The user not found with userId"+
                        tagId));

        tag.setName(tagName);
        return this.tagRepository.save(tag);
    }

    public Set<Tag> getAllTags(Long userId) throws ResourceNotFoundException {
        AppUser user = userTableRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("The user not found with userId"+userId));
        return user.getTags();
    }

}
