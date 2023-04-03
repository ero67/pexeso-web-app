package tuke.game.pexeso.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//package tuke.game.pexeso.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import sk.tuke.gamestudio.SpringClient;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.service.CommentsService;
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringClient.class)
public class CommentsServiceTest {

    @Autowired
    private CommentsService commentsService;

    @Test
    public void reset() {
        commentsService.reset();
        assertEquals(0, commentsService.getComments("pexeso").size());
    }

    @Test
    public void addComment() {
        commentsService.reset();
        var date = new Date();

        commentsService.addComment(new Comment("Jaro", "pexeso", "ahoj",date));

        var comments = commentsService.getComments("pexeso");
        assertEquals(1, comments.size());
        assertEquals("pexeso", comments.get(0).getGame());
        assertEquals("Jaro", comments.get(0).getPlayer());
        assertEquals("ahoj", comments.get(0).getComment());
    }

    @Test
    public void getComments(){
        commentsService.reset();
        commentsService.addComment(new Comment("Erik","pexeso","super",new Date()));
        commentsService.addComment(new Comment("Erik","pexeso","super",new Date()));
        commentsService.addComment(new Comment("Erik","pexeso","super",new Date()));

        var comments=commentsService.getComments("pexeso");
        assertEquals(3,comments.size());

    }
}