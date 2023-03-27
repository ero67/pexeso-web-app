package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
            existingRating.setRatedAt(rating.getRatedAtdAt());
            entityManager.merge(existingRating);
        } catch (NoResultException e) {
            entityManager.persist(rating);
        }
    }

    @Override
    public List<Rating> getTopRatings(String game) {
        return entityManager.createQuery("select s from Rating s where s.game = :game order by s.rating desc")
                .setParameter("game", game)
                .setMaxResults(10)
                .getResultList();
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

