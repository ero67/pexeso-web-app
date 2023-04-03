package tuke.game.pexeso.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import sk.tuke.gamestudio.SpringClient;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.service.RatingServiceJPA;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringClient.class)
public class RatingServiceJPAtest {

    @Autowired
    private RatingServiceJPA ratingService;

    @Test
    public void testReset() {
        ratingService.reset();
        assertEquals(null,ratingService.getRating("pexeso","Erik"));
    }

    @Test
    public void addRating() {
        ratingService.reset();
        var date = new Date();

        ratingService.setRating(new Rating("Erik", "pexeso", 5,date));

        int rating = ratingService.getRating("pexeso", "Erik");

        assertEquals(5, rating);
    }


}