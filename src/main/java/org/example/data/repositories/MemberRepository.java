package org.example.data.repositories;

import org.example.data.entities.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class MemberRepository {

    private final EntityManager entityManager;

    public MemberRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Member> findAll() {

        return entityManager.createNamedQuery("Member.findAll", Member.class).getResultList();

    }

    public Optional<Member> findById(Integer id) {
        Member member = entityManager.find(Member.class, id);

        return (member == null) ? Optional.empty() : Optional.of(member);
    }

    public Optional<Member> findByEmail(String email) {

        Member member = entityManager.createNamedQuery("Member.findByEmail", Member.class)
                .setParameter("email", email)
                .getSingleResult();

        return (member == null) ? Optional.empty() : Optional.of(member);

    }

    public Optional<Member> findByPhone(String phone) {

        Member member = entityManager.createNamedQuery("Member.findByPhone", Member.class).getSingleResult();

        return (member == null) ? Optional.empty() : Optional.of(member);
    }

    public Optional<Member> save(Member member) {

        try {

            entityManager.getTransaction().begin();

            entityManager.persist(member);

            entityManager.getTransaction().commit();

            return Optional.of(member);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return Optional.empty();
    }

}
