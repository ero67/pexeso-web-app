package tuke.game.pexeso.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.service.RatingServiceJPA;
import sk.tuke.gamestudio.service.RatingsService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
@SpringBootTest
public class RatingServiceJPAtest {

    @Autowired
    private RatingsService ratingService;



    @Test
    public void testReset() {
        ratingService.reset();
        assertEquals(0, ratingService.getTopRatings("pexeso").size());
    }

    @org.junit.jupiter.api.Test
    public void addRating() {
        ratingService.reset();
        var date = new Date();

        ratingService.setRating(new Rating("Erik", "pexeso", 5,date));

        var ratings = ratingService.getTopRatings("pexeso");
        Assertions.assertEquals(1, ratings.size());
        Assertions.assertEquals("pexeso", ratings.get(0).getGame());
        Assertions.assertEquals("Erik", ratings.get(0).getPlayer());
        Assertions.assertEquals(5, ratings.get(0).getRating());
        Assertions.assertEquals(date,ratings.get(0).getRatedAtdAt());

    }

    @Test
    public void testGetTopRatings() {
        ratingService.reset();
        Date date = new Date();
        ratingService.setRating(new Rating("Jaro", "pexeso", 5, date));
        ratingService.setRating(new Rating("Peter", "pexeso", 4, date));
        ratingService.setRating(new Rating("Samo", "pexeso", 3, date));

        List<Rating> ratings = ratingService.getTopRatings("pexeso");

        assertEquals(3, ratings.size());

        assertEquals("pexeso", ratings.get(0).getGame());
        assertEquals("Jaro", ratings.get(0).getPlayer());
        assertEquals(5, ratings.get(0).getRating());
    }
}