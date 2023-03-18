package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Comment;

import java.util.List;

public interface CommentsService {
        void addComment(Comment comment);
        List<Comment> getComments(String game);
        void reset();


}
