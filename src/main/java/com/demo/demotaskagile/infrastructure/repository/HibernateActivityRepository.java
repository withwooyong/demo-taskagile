package com.demo.demotaskagile.infrastructure.repository;

import com.demo.demotaskagile.domain.model.activity.Activity;
import com.demo.demotaskagile.domain.model.activity.ActivityRepository;
import com.demo.demotaskagile.domain.model.card.CardId;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class HibernateActivityRepository extends HibernateSupport<Activity> implements ActivityRepository {

    HibernateActivityRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Activity> findCardActivities(CardId cardId) {
        String sql = "SELECT a.* FROM activity a WHERE a.card_id = :cardId order by id desc";
        NativeQuery<Activity> query = getSession().createNativeQuery(sql, Activity.class);
        query.setParameter("cardId", cardId.value());
        return query.list();
    }
}
