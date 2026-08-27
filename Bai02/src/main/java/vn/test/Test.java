package vn.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import vn.config.JpaConfig;
import vn.entity.Category;

public class Test {

    public static void main(String[] args) {

        EntityManager em = JpaConfig.getEntityManager();

        EntityTransaction trans = em.getTransaction();

        try {

            Category cate = new Category();

            cate.setName("Điện thoại");
            cate.setIcon("phone.jpg");

            trans.begin();

            em.persist(cate);

            trans.commit();

            System.out.println("Thêm category thành công!");

        } catch (Exception e) {

            if (trans.isActive()) {
                trans.rollback();
            }

            e.printStackTrace();

        } finally {

            em.close();
        }
    }
}