/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.TblAdmin;
import entities.TblUser;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class UserDAO {

    public boolean insert(TblUser p) {
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

    public boolean update(TblUser o) {
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

    public boolean delete(TblUser o) {

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

    public List<TblUser> getAll() {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<TblUser> list = session.createQuery("from TblUser").list();

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

    public TblUser getById(int o) {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            TblUser list = (TblUser) session.createQuery("from TblUser where userId =:id").setParameter("id", o).uniqueResult();

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
    public boolean checkExist(String o) {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            TblUser list = (TblUser) session.createQuery("from TblUser where UserName =:id").setParameter("id", o).uniqueResult();

            session.getTransaction().commit();
            session.close();
            if (list != null) {
                return true;
            } else {

                return false;
            }

        } catch (Exception e) {
            session.getTransaction().rollback();
            session.close();
        }
        return false;

    }
    

    public TblUser checkLogin(String username, String pass) {

        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            TblUser ad = (TblUser) session.createQuery("from TblUser where UserName= :user and Password= :pass")
                    .setParameter("user", username)
                    .setParameter("pass", pass).uniqueResult();
            session.getTransaction().commit();
            session.close();
            if (ad != null) {
                return ad;
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
