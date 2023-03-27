package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RatingsServiceJDBC implements RatingsService {
    public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/gamestudio";
    public static final String JDBC_USER = "erik";
    public static final String JDBC_PASSWORD = "eriklaki";
    public static final String DELETE_STATEMENT = "DELETE FROM ratings";
    public static final String INSERT_STATEMENT = "INSERT INTO ratings(player, game, rating, rated_at) VALUES (?,?,?,?)";
    public static final String SELECT_STATEMENT = " SELECT player, game, rating, rated_at FROM ratings WHERE game= ? ORDER BY rating DESC LIMIT 10";
    private static final  String AVG_RATING_QUERY = "asd";


    @Override
    public void setRating(Rating rating) {
        try (var connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             var insertStatement = connection.prepareStatement(INSERT_STATEMENT);
             var selectStatement = connection.prepareStatement("SELECT COUNT(*) FROM ratings WHERE player = ? AND game = ?");
             var updateStatement = connection.prepareStatement("UPDATE ratings SET rating = ?, rated_at = ? WHERE player = ? AND game = ?")
        ) {
            selectStatement.setString(1, rating.getPlayer());
            selectStatement.setString(2, rating.getGame());
            var resultSet = selectStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count > 0) {
                updateStatement.setInt(1, rating.getRating());
                updateStatement.setTimestamp(2, new Timestamp(rating.getRatedAtdAt().getTime()));
                updateStatement.setString(3, rating.getPlayer());
                updateStatement.setString(4, rating.getGame());
                updateStatement.executeUpdate();
            } else {
                insertStatement.setString(1, rating.getPlayer());
                insertStatement.setString(2, rating.getGame());
                insertStatement.setInt(3, rating.getRating());
                insertStatement.setTimestamp(4, new Timestamp(rating.getRatedAtdAt().getTime()));
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new GameStudioException(e);
        }
    }

    @Override
    public List<Rating> getTopRatings(String game) {
        try (var connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             var statement = connection.prepareStatement(SELECT_STATEMENT);
        ) {
            statement.setString(1, game);
            try (var rs = statement.executeQuery()) {
                var ratings = new ArrayList<Rating>();
                while (rs.next()) {
                    ratings.add(new Rating(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getTimestamp(4)));
                }
                return ratings;
                // statement.executeUpdate(INSERT_STATEMENT);
            }
        } catch (SQLException e) {
            throw new GameStudioException(e);
        }
    }

    @Override
    public void reset() {
        try (var connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             var statement = connection.createStatement();

        ) {
            statement.executeUpdate(DELETE_STATEMENT);
        } catch (SQLException e) {
            throw new GameStudioException(e);
        }

    }

    @Override
    public int getAverageRating(String game) throws GameStudioException {
        try (var connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             var statement = connection.prepareStatement(AVG_RATING_QUERY)) {
            statement.setString(1, game);
            try (var rs = statement.executeQuery()) {
                if (rs.next()) {
                    double avgRating = rs.getDouble(1);
                    return (int) Math.round(avgRating);
                } else {
                    throw new GameStudioException("No ratings for game " + game);
                }
            }
        } catch (SQLException e) {
            throw new GameStudioException(e);
        }
    }

}
