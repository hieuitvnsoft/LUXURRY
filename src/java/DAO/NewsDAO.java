/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.TblNews;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class NewsDAO {
     public boolean insert(TblNews p) {
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

    public boolean update(TblNews o) {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(o);
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

    public boolean delete(TblNews o) {

        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(o);
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

    public List<TblNews> getAll() {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<TblNews> list = session.createQuery("from TblNews").list();

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

    public TblNews getById(int o) {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            TblNews list = (TblNews) session.createQuery("from TblNews where typeNewId =:id").setParameter("id", o).uniqueResult();

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
}
