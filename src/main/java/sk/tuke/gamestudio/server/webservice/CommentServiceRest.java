package sk.tuke.gamestudio.server.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.CommentsService;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentServiceRest {
    private final CommentsService commentsService;

    public CommentServiceRest(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/{game}")
    public List<Comment> getComments(@PathVariable String game) {
        return commentsService.getComments(game);
    }


    @PostMapping
    public void addComment(@RequestBody Comment comment) {
        commentsService.addComment(comment);
    }


}



