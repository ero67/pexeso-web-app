package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Comment;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CommentsServiceJDBC implements CommentsService{
    public static final String JDBC_URL="jdbc:postgresql://localhost:5432/gamestudio";
    public static final String JDBC_USER="erik";
    public static final String JDBC_PASSWORD="eriklaki";
    public static final String DELETE_STATEMENT = "DELETE FROM comments";
    public static final String INSERT_STATEMENT = "INSERT INTO comments(player, game, commentar, commented_at) VALUES (?,?,?,?)";
    public static final String SELECT_STATEMENT=" SELECT player, game, commentar, commented_at FROM comments WHERE game= ? ";
    @Override
    public void addComment(Comment comment) {
        try (var connection= DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
             var statement=connection.prepareStatement(INSERT_STATEMENT);

        ){
            statement.setString(1,comment.getPlayer());
            statement.setString(2, comment.getGame());
            statement.setString(3,comment.getComment());
            statement.setTimestamp(4, new Timestamp(comment.getCommentedAt().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new GameStudioException(e);
        }
    }

    @Override
    public List<Comment> getComments(String game) {
        try (var connection=DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
             var statement=connection.prepareStatement(SELECT_STATEMENT);
        ) {
            statement.setString(1, game);
            try (var rs = statement.executeQuery()) {
                var comments = new ArrayList<Comment>();
                while (rs.next()) {
                    comments.add(new Comment(rs.getString(1), rs.getString(2),rs.getString(3),rs.getTimestamp(4)));
                }
                return comments;
                // statement.executeUpdate(INSERT_STATEMENT);
            }
        }
        catch (SQLException e) {
            throw new GameStudioException(e);
        }

    }

    @Override
    public void reset() {
        try (var connection=DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
             var statement=connection.createStatement();

        ){
            statement.executeUpdate(DELETE_STATEMENT);
        } catch (SQLException e) {
            throw new GameStudioException(e);
        }

    }
}
