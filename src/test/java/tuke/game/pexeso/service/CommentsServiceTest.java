package tuke.game.pexeso.service;

import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.service.CommentsService;
import sk.tuke.gamestudio.service.CommentsServiceJDBC;
import sk.tuke.gamestudio.service.CommentsServiceJPA;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


public class CommentsServiceTest {
    private CommentsService commentsService=new CommentsServiceJPA();

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
