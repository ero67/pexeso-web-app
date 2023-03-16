package org.tuke.service;

import org.tuke.entity.Rating;
import org.tuke.entity.Score;

import java.util.ArrayList;
import java.util.List;

public interface RatingsService {
    void addRating(Rating rating);
    List<Rating> getTopRatings(String game);
    void reset();
}
