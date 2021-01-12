package org.example;

import org.example.data.repositories.MemberRepository;
import org.example.ui.Display;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Launcher Application
 */
public class App {

    public static void main(String[] args) {

        Display display = new Display("Mangoes Leisure Facility", 600, 400);

        display.show();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MLF");
        EntityManager em = emf.createEntityManager();

        MemberRepository memberRepo = new MemberRepository(em);

        memberRepo.findAll().forEach(System.out::println);

        em.close();

        emf.close();
    }

}
