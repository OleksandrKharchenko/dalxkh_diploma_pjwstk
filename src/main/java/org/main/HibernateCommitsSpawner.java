package org.main;

import org.hibernate.Session;

public class HibernateCommitsSpawner {

    public void createCommit(Object o) {
        Session session = HibernateSessionFactorySpawner.spawnSession();
        session.beginTransaction();
        session.persist(o);
        session.getTransaction().commit();
        session.close();
    }

    public void updateCommit(Object o) {
        Session session = HibernateSessionFactorySpawner.spawnSession();
        session.beginTransaction();
        session.merge(o);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteCommit(Object o) {
        Session session = HibernateSessionFactorySpawner.spawnSession();
        session.beginTransaction();
        session.remove(o);
        session.getTransaction().commit();
        session.close();
    }

}
