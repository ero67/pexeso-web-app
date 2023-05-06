package sk.tuke.gamestudio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;

public class RatingServiceRestClient implements RatingsService {

    @Value("${remote.server.api}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public void setRating(Rating rating) {
        restTemplate.postForEntity(url+"/rating",rating,Rating.class);
    }



    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported via web service");
    }
    /*
     @Override
     public int getAverageRating(String game) throws GameStudioException {
         Rating[] ratings = restTemplate.getForEntity(url + "/rating/" + game, Rating[].class).getBody();

         if (ratings == null || ratings.length == 0) {
             throw new GameStudioException("No ratings found for the game " + game);
         }

         int totalRating = 0;
         for (Rating rating : ratings) {
             totalRating += restTemplate.getForEntity(url + "/rating/" + game + "/" + rating.getPlayer(), Rating.class).getBody().getRating();
         }

         return totalRating / ratings.length;
     }
 */
    public int getAverageRating(String game){
        return restTemplate.getForEntity(url+"/rating/"+game+"/avg",Integer.class).getBody();
    }
    @Override
    public int getRating(String game, String player) {
        return restTemplate.getForEntity(url + "/rating/" + game + "/" + player, Rating.class).getBody().getRating();
    }
}
