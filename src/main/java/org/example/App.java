package org.example;

import org.example.ui.Display;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Launcher Application
 */
public class App {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MLF");
        EntityManager em = emf.createEntityManager();

        Display display = new Display("Mangoes Leisure Facility", 600, 400);

        display.show();
    }

}
