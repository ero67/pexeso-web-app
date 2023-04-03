package tuke.game.pexeso.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sk.tuke.gamestudio.SpringClient;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.ScoreService;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringClient.class)
public class ScoreServicesTest {

    @Autowired
    private ScoreService scoreService;

    @Test
    public void reset() {
        scoreService.reset();
        assertEquals(0, scoreService.getTopScores("pexeso").size());
    }

    @Test
    public void addScore() {
        scoreService.reset();
        var date = new Date();

        scoreService.addScore(new Score("Erik", "pexeso", 100, date));

        var scores = scoreService.getTopScores("pexeso");
        assertEquals(1, scores.size());
        assertEquals("pexeso", scores.get(0).getGame());
        assertEquals("Erik", scores.get(0).getPlayer());
        assertEquals(100, scores.get(0).getPoints());
        assertEquals(date, scores.get(0).getPlayedAt());
    }

    @Test
    public void getTopScores() {
        scoreService.reset();
        var date = new Date();
        scoreService.addScore(new Score("Jaro", "pexeso", 4, date));
        scoreService.addScore(new Score("Katka", "pexeso", 2, date));
        scoreService.addScore(new Score("Zuzka", "fifa", 180, date));
        scoreService.addScore(new Score("Peter", "pexeso", 6, date));

        var scores = scoreService.getTopScores("pexeso");

        assertEquals(3, scores.size());

        assertEquals("pexeso", scores.get(0).getGame());
        assertEquals("Erik", scores.get(0).getPlayer());
        assertEquals(100, scores.get(0).getPoints());
        assertEquals(date, scores.get(0).getPlayedAt());

        assertEquals("pexeso", scores.get(1).getGame());
        assertEquals("Peter", scores.get(1).getPlayer());
        assertEquals(6, scores.get(1).getPoints());
        assertEquals(date, scores.get(1).getPlayedAt());

        assertEquals("pexeso", scores.get(2).getGame());
        assertEquals("Jaro", scores.get(2).getPlayer());
        assertEquals(4, scores.get(2).getPoints());
        assertEquals(date, scores.get(2).getPlayedAt());
    }
}