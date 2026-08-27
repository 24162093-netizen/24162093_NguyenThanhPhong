package vn.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import vn.config.JpaConfig;
import vn.entity.Category;

public class CategoryDao {

    // CREATE
    public void insert(Category category) {

        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            em.persist(category);

            transaction.commit();

        } catch (Exception e) {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            e.printStackTrace();

        } finally {
            em.close();
        }
    }


    // READ - lấy tất cả
    public List<Category> findAll() {

        EntityManager em = JpaConfig.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT c FROM Category c",
                    Category.class
            ).getResultList();

        } finally {
            em.close();
        }
    }


    // READ - lấy theo ID
    public Category findById(int id) {

        EntityManager em = JpaConfig.getEntityManager();

        try {

            return em.find(Category.class, id);

        } finally {
            em.close();
        }
    }


    // UPDATE
    public void update(Category category) {

        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {

            transaction.begin();

            em.merge(category);

            transaction.commit();

        } catch (Exception e) {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            e.printStackTrace();

        } finally {
            em.close();
        }
    }


    // DELETE
    public void delete(int id) {

        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {

            transaction.begin();

            Category category =
                    em.find(Category.class, id);

            if (category != null) {
                em.remove(category);
            }

            transaction.commit();

        } catch (Exception e) {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            e.printStackTrace();

        } finally {
            em.close();
        }
    }
}