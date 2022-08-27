package SpringBoot.controller;

import SpringBoot.Exception.ResourceNotFoundException;
import SpringBoot.Repository.CommentRepository;
import SpringBoot.Repository.TaskRepository;
import SpringBoot.model.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/comment")
@ApiOperation("Comment related api's")
public class CommentController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CommentRepository commentRepository;

    @ApiOperation(value = "Create a comment", notes = "Creates a comment for a task")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved the comment"),
            @ApiResponse(code = 404, message = "Task not found")
    })
    @PostMapping("/")
    public Comment addComment(@RequestBody AddCommentRequest addCommentRequest) throws ResourceNotFoundException {

        Task task = taskRepository.findById(addCommentRequest.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("The list not found with listId"
                        +addCommentRequest.getTaskId()));
        Comment comment = new Comment();
        comment.setComment(addCommentRequest.getComment());
        comment.setCreated(CurrentTimeRetreiver.getCurrentTime());
        comment.setUtask(task);

        return this.commentRepository.save(comment);
    }

    @ApiOperation(value = "Delete a comment", notes = "Deletes a comment by it's Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted the comment"),
            @ApiResponse(code = 404, message = "Comment not found")
    })
    @DeleteMapping("/comment/{commentId}")
    public void deleteComment(@PathVariable(value = "commentId") Long commentId) throws ResourceNotFoundException {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("The comment not found with commentId"
                        +commentId));

        commentRepository.delete(comment);
    }

    @ApiOperation(value = "Update a comment", notes = "Updates a comment for the task by the commentId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated the comment"),
            @ApiResponse(code = 404, message = "Comment with commentId not found")
    })
    @PutMapping("/update")
    public ResponseEntity<Comment> updateComment(@RequestBody UpdateCommentRequest updateCommentRequest)
            throws ResourceNotFoundException {

        Comment comment = commentRepository.findById(updateCommentRequest.getCommentId())
                .orElseThrow(() -> new ResourceNotFoundException("The Comment not found with commentId"+updateCommentRequest.getCommentId()));

        comment.setUpdated(CurrentTimeRetreiver.getCurrentTime());
        comment.setComment(updateCommentRequest.getNewComment());

        return ResponseEntity.ok(this.commentRepository.save(comment));
    }

    @ApiOperation(value = "Get a comment by Id", notes = "Gets a comment by comment Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retreived the comment"),
            @ApiResponse(code = 404, message = "Comment with commentId not found")
    })
    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getComment(@PathVariable(value = "commentId") Long commentId)
            throws ResourceNotFoundException {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("The Comment not found with commentId"+commentId));

        return ResponseEntity.ok().body(comment);
    }

}
