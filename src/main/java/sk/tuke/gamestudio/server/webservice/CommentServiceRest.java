package sk.tuke.gamestudio.server.webservice;

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

    //POST -> http://localhost:8080/api/score
    @PostMapping
    public void addScore(@RequestBody Comment comment) {
        commentsService.addComment(comment);
    }
}
