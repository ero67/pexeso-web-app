package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import java.util.List;

public interface RatingsService {
    void addRating(Rating rating);
    List<Rating> getTopRatings(String game);
    void reset();
}
