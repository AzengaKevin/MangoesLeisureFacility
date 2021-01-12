package org.example;

import org.example.data.entities.Member;
import org.example.data.entities.Membership;
import org.example.data.repositories.MemberRepository;
import org.example.data.repositories.MembershipRepository;
import org.example.ui.Display;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

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
        MembershipRepository membershipRepo = new MembershipRepository(em);

        Optional<Membership> maybeMembership = membershipRepo.save(new Membership("Swim", 20.00));

        maybeMembership.ifPresent(membership -> {

            Member member = new Member("Dottie", "dottie@gmail.com", "+254700016349", 2, 1174665696L, membership, null);
            memberRepo.save(member);

            Member anotherMember = new Member("Winny", "winny@gmail.com", "+25498952540", 3, 20931525L, membership, null);
            memberRepo.save(anotherMember);

            List<Member> members = memberRepo.findAll();

            System.out.println("Members:");
            members.forEach(System.out::println);

        });

        em.close();

        emf.close();
    }

}
