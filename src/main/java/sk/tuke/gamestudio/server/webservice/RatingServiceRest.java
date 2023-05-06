package sk.tuke.gamestudio.server.webservice;

import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.service.RatingsService;

@RestController
@RequestMapping("/api/rating")
public class RatingServiceRest {
    private final RatingsService ratingsService;

    public RatingServiceRest(RatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }

    @GetMapping("/{game}/{player}")
    public int getRating(@PathVariable String game, @PathVariable String player){
        return ratingsService.getRating(game,player);
    }
    @PostMapping
    public void setRating(@RequestBody Rating rating){
        ratingsService.setRating(rating);
    }

    @GetMapping("/{game}/avg")
    public int getAverageRating(@PathVariable String game){
        return ratingsService.getAverageRating(game);
    }
}


