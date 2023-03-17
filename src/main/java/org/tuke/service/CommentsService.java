package org.tuke.service;

import org.tuke.entity.Comment;
import org.tuke.entity.Rating;

import java.util.List;

public interface CommentsService {
        void addComment(Comment comment);
        List<Comment> getComments(String game);
        void reset();


}
