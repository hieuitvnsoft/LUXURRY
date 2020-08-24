/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.TblTypeNews;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class TypeNewsDAO {
     public boolean insert(TblTypeNews p) {
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

    public boolean update(TblTypeNews o) {
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

    public boolean delete(TblTypeNews o) {

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

    public List<TblTypeNews> getAll() {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<TblTypeNews> list = session.createQuery("from TblTypeNews").list();

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

    public TblTypeNews getById(int o) {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            TblTypeNews list = (TblTypeNews) session.createQuery("from TblTypeNews where typeNewId =:id").setParameter("id", o).uniqueResult();

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
