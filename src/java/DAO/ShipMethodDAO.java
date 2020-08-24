/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.TblShipMethod;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class ShipMethodDAO {
    public boolean insert(TblShipMethod p) {
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

    public boolean update(TblShipMethod o) {
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

    public boolean delete(TblShipMethod o) {

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

    public List<TblShipMethod> getAll() {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<TblShipMethod> list = session.createQuery("from TblShipMethod").list();

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

    public TblShipMethod getById(int o) {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            TblShipMethod list = (TblShipMethod) session.createQuery("from TblShipMethod where ShipMethodId =:id").setParameter("id", o).uniqueResult();

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
