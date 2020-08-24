/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.TblAdmin;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class AdminDAO {
    public TblAdmin checkLogin(String username, String pass) {
        
        Session session = common.HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            TblAdmin ad = (TblAdmin) session.createQuery("from TblAdmin where UserName= :user and Password= :pass")
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
