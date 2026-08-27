package vn.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import vn.config.JpaConfig;
import vn.dao.UserDao;
import vn.entity.User;

public class UserDaoImpl implements UserDao {

    @Override
    public User findByUsername(String username) {

        EntityManager em =
                JpaConfig.getEntityManager();

        try {

            String jpql =
                    "SELECT u FROM User u WHERE u.userName = :username";

            return em.createQuery(
                    jpql,
                    User.class
            )
            .setParameter("username", username)
            .getSingleResult();

        } catch (NoResultException e) {

            return null;

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {

            em.close();
        }
    }
}