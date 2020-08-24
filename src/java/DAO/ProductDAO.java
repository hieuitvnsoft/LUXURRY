/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.TblProducts;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public class ProductDAO implements ImplProductPage {

    public boolean insert(TblProducts p) {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(p);
            session.flush();
            session.getTransaction().commit();
            session.close();

            return true;

        } catch (Exception e) {
            session.getTransaction().rollback();
            session.close();
            System.out.println(e);
        }
        return false;

    }

    public boolean update(TblProducts p) {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(p);
            session.flush();
            session.getTransaction().commit();
            session.close();

            return true;

        } catch (Exception e) {
            session.getTransaction().rollback();
            session.close();
            System.out.println(e);
        }
        return false;

    }

    public boolean delete(TblProducts p) {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(p);
            session.flush();
            session.getTransaction().commit();
            session.close();

            return true;

        } catch (Exception e) {
            session.getTransaction().rollback();
            session.close();
            System.out.println(e);
        }
        return false;
    }

    public List<TblProducts> getAll() {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<TblProducts> list = session.createQuery("from TblProducts where Status=1").list();

            session.getTransaction().commit();
            session.close();
            if (list != null) {
                return list;
            } else {

                return null;
            }

        } catch (Exception e) {
            session.getTransaction().rollback();
            session.close();
        }
        return null;
    }

    public List<TblProducts> getAll(int id) {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<TblProducts> list = session.createQuery("from TblProducts where TrademarkId=:id and Status=1").setParameter("id", id).list();

            session.getTransaction().commit();
            session.close();
            if (list != null) {
                return list;
            } else {

                return null;
            }

        } catch (Exception e) {
            session.getTransaction().rollback();
            session.close();
        }
        return null;
    }

    public List<TblProducts> getAllType(int id) {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<TblProducts> list = session.createQuery("from TblProducts where TypeId=:id and Status=1").setParameter("id", id).list();

            session.getTransaction().commit();
            session.close();
            if (list != null) {
                return list;
            } else {

                return null;
            }

        } catch (Exception e) {
            session.getTransaction().rollback();
            session.close();
        }
        return null;
    }

    public TblProducts getById(int o) {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            TblProducts list = (TblProducts) session.createQuery("from TblProducts where productId=:id").setParameter("id", o).uniqueResult();

            session.getTransaction().commit();
            session.close();
            if (list != null) {
                return list;
            } else {

                return null;
            }

        } catch (Exception e) {
            session.getTransaction().rollback();
            session.close();
        }
        return null;
    }

    @Override
    public List<TblProducts> findAll(Integer offset, Integer maxResult) {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        return session.createCriteria(TblProducts.class)
                .setFirstResult(offset != null ? offset : 0)
                .setMaxResults(maxResult != null ? maxResult :10)
                .list();
    }
    
     public List<TblProducts> findAllbySearch(String search) {
         
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(TblProducts.class);
        cr.add(Restrictions.like("productName", "%"+search+"%"));
        return cr.list();
    }

    @Override
    public Long count() {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        return (Long) session
                .createCriteria(TblProducts.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

}
