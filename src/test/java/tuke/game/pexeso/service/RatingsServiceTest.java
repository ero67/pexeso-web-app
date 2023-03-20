package tuke.game.pexeso.service;
import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.RatingsService;
import sk.tuke.gamestudio.service.RatingsServiceJDBC;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class RatingsServiceTest {
    RatingsService ratingsService=new RatingsServiceJDBC();

    @Test
    public void reset() {
        ratingsService.reset();

        assertEquals(0, ratingsService.getTopRatings("pexeso").size());
    }

    @Test
    public void addRating() {
        ratingsService.reset();
        var date = new Date();

        ratingsService.addRating(new Rating("Erik", "pexeso", 5));

        var ratings = ratingsService.getTopRatings("pexeso");
        assertEquals(1, ratings.size());
        assertEquals("pexeso", ratings.get(0).getGame());
        assertEquals("Erik", ratings.get(0).getPlayer());
        assertEquals(5, ratings.get(0).getRating());

    }

    @Test
    public void getTopRatings() {
        ratingsService.reset();
        var date = new Date();
        ratingsService.addRating(new Rating("Jaro", "pexeso", 5));
        ratingsService.addRating(new Rating("Peter", "pexeso", 4));
        ratingsService.addRating(new Rating("Samo", "pexeso", 3));

        var ratings = ratingsService.getTopRatings("pexeso");

        assertEquals(3, ratings.size());

        assertEquals("pexeso", ratings.get(0).getGame());
        assertEquals("Jaro", ratings.get(0).getPlayer());
        assertEquals(5, ratings.get(0).getRating());


        assertEquals("pexeso", ratings.get(1).getGame());
        assertEquals("Peter", ratings.get(1).getPlayer());
        assertEquals(4, ratings.get(1).getRating());


        assertEquals("pexeso", ratings.get(2).getGame());
        assertEquals("Samo", ratings.get(2).getPlayer());
        assertEquals(3, ratings.get(2).getRating());

    }



}
