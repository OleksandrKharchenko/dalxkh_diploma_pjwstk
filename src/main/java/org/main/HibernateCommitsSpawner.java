package org.main;

import org.hibernate.Session;

public class HibernateCommitsSpawner {

    public void createCommit(Object o) {
        Session startCreateSession = HibernateSessionFactorySpawner.spawnSession();
        startCreateSession.beginTransaction();
        startCreateSession.persist(o);
        startCreateSession.getTransaction().commit();
    }

    public void updateCommit(Object o) {
        Session startCreateSession = HibernateSessionFactorySpawner.spawnSession();
        startCreateSession.beginTransaction();
        startCreateSession.merge(o);
        startCreateSession.getTransaction().commit();
    }

    public void deleteCommit(Object o) {
        Session startCreateSession = HibernateSessionFactorySpawner.spawnSession();
        startCreateSession.beginTransaction();
        startCreateSession.remove(o);
        startCreateSession.getTransaction().commit();
    }

}
