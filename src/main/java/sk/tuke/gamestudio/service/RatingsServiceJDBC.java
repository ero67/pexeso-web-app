package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RatingsServiceJDBC implements RatingsService{
    public static final String JDBC_URL="jdbc:postgresql://localhost:5432/gamestudio";
    public static final String JDBC_USER="erik";
    public static final String JDBC_PASSWORD="eriklaki";
    public static final String DELETE_STATEMENT = "DELETE FROM ratings";
    public static final String INSERT_STATEMENT = "INSERT INTO ratings(player, game, rating) VALUES (?,?,?)";
    public static final String SELECT_STATEMENT=" SELECT player, game, rating FROM ratings WHERE game= ? ORDER BY rating DESC LIMIT 10";


    @Override
    public void addRating(Rating rating) {
        try (var connection= DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
             var statement=connection.prepareStatement(INSERT_STATEMENT);

        ){
            statement.setString(1,rating.getPlayer());
            statement.setString(2, rating.getGame());
            statement.setInt(3,rating.getRating());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new GameStudioException(e);
        }
    }

    @Override
    public List<Rating> getTopRatings(String game) {
        try (var connection=DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
             var statement=connection.prepareStatement(SELECT_STATEMENT);
        ) {
            statement.setString(1, game);
            try (var rs = statement.executeQuery()) {
                var ratings = new ArrayList<Rating>();
                while (rs.next()) {
                    ratings.add(new Rating(rs.getString(1), rs.getString(2),rs.getInt(3)));
                }
                return ratings;
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
