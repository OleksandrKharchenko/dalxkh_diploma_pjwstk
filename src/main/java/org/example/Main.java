package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("Hello Diploma!");
        SessionFactory factory = new Configuration().
                configure(EnvVars.HibirnateConfigFile).
                addAnnotatedClass(Person.class).
                buildSessionFactory();



    }

}