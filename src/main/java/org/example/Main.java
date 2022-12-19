package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().
                configure(EnvVars.HibirnateConfigFile).
                addAnnotatedClass(User.class).
                buildSessionFactory();



    }

}