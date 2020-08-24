/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.TblOrderDetail;
import entities.TblOrders;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class OrderDAO {

    public boolean insert(TblOrders p, List<TblOrderDetail> list) {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(p);

            int id = (Integer) session.createQuery("select max(orderId) from TblOrders").uniqueResult();
            p.setOrderId(id);
            for (TblOrderDetail list1 : list) {
                list1.setOrderId(p);
                session.save(list1);
            }

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

    public TblOrders getOrderbyId(int id) {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            TblOrders ad = (TblOrders) session.createQuery("from TblOrders where OrderId = :id")
                    .setParameter("id", id)
                    .uniqueResult();

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

    public List<TblOrderDetail> getOrderDetailbyId(int id) {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<TblOrderDetail> ad = session.createQuery("from TblOrderDetail where orderId.orderId = :id")
                    .setParameter("id", id)
                    .list();

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

    public boolean updateOrder(TblOrders p) {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            TblOrders od = (TblOrders) session.createQuery("from TblOrders where orderId= :id")
                    .setParameter("id", p.getOrderId()).uniqueResult();

            od.setStatus(p.getStatus());
            session.update(od);
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

    public boolean update(TblOrders o) {
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

    public boolean delete(TblOrders o) {

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

    public List<TblOrders> getAll() {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<TblOrders> list = session.createQuery("from TblOrders order by  DateOrder DESC").list();

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

    public List<TblOrders> getAllbyUser(int u) {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<TblOrders> list = session.createQuery("from TblOrders where UserId=:u order by  DateOrder DESC").setParameter("u", u).list();

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

    public TblOrders getById(int o) {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            TblOrders list = (TblOrders) session.createQuery("from TblOrders where id =:id").setParameter("id", o).uniqueResult();

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

    public boolean cancelOrder(TblOrders o) {
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

    public int countOrderNew() {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            int count = ((Number) session.createSQLQuery("select COUNT(*) from TblOrders where status = 0").uniqueResult()).intValue();

            session.getTransaction().commit();
            session.close();
            return count;

        } catch (Exception e) {
            session.getTransaction().rollback();
            session.close();
        }
        return 0;

    }

    public int countOrderDetroy() {
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            int count = ((Number) session.createSQLQuery("select COUNT(*) from TblOrders where status = 4").uniqueResult()).intValue();

            session.getTransaction().commit();
            session.close();
            return count;

        } catch (Exception e) {
            session.getTransaction().rollback();
            session.close();
        }
        return 0;

    }
}
