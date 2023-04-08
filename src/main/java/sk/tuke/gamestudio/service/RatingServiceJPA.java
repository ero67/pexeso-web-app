package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
public class RatingServiceJPA implements RatingsService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void setRating(Rating rating) {
        try {
            Rating existingRating = (Rating) entityManager.createQuery("SELECT r FROM Rating r WHERE r.player = :player AND r.game = :game")
                    .setParameter("player", rating.getPlayer())
                    .setParameter("game", rating.getGame())
                    .getSingleResult();
            existingRating.setRating(rating.getRating());
            existingRating.setRatedAt(rating.getRatedAtdAt());//ak najdem daneho hraca s danou hrou tak mu zmenim skore....
        } catch (NoResultException e) {
            entityManager.persist(rating);//ak nie tak pridam novy rating
        }
    }

    @Override
    public int getRating(String game, String player) {
        Query query = entityManager.createQuery("SELECT r FROM Rating r WHERE r.game = :game AND r.player = :player");
        query.setParameter("game", game);
        query.setParameter("player", player);

        Rating rating= (Rating) query.getSingleResult();
        if (rating==null) {
            return 0;
        }

        return rating.getRating();
    }

    @Override
    public void reset() {
        entityManager.createNativeQuery("DELETE FROM ratings").executeUpdate();
    }



    @Override
    public int getAverageRating(String game) {
        TypedQuery<Double> query = entityManager.createQuery(
                "SELECT AVG(r.rating) FROM Rating r WHERE r.game = :game", Double.class);
        query.setParameter("game", game);
        Double result = query.getSingleResult();
        return result != null ? result.intValue() : 0;

    }
}

