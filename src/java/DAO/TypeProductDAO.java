/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.*;
import entities.TblTypeProduct;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class TypeProductDAO {

    public boolean insert(TblTypeProduct p) {
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

    public boolean update(TblTypeProduct o) {
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

    public boolean delete(TblTypeProduct o) {
    
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

    public List<TblTypeProduct> getAll() {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<TblTypeProduct> list = session.createQuery("from TblTypeProduct").list();

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

    public TblTypeProduct getById(int o) {
     Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            TblTypeProduct list = (TblTypeProduct) session.createQuery("from TblTypeProduct where id =:id").setParameter("id",o).uniqueResult();

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
