package org.example.data.repositories;

import org.example.data.entities.Membership;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class MembershipRepository {

    private final EntityManager entityManager;

    public MembershipRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Membership> findById(Integer id) {
        Membership membership = entityManager.find(Membership.class, id);

        return (membership == null) ? Optional.empty() : Optional.of(membership);
    }

    public List<Membership> findAll() {
        return entityManager.createQuery("from Membership", Membership.class).getResultList();
    }

    public Optional<Membership> findByName(String name) {

        Membership membership = entityManager.createNamedQuery("Membership.findByName", Membership.class)
                .setParameter("name", name)
                .getSingleResult();

        return (membership == null) ? Optional.empty() : Optional.of(membership);
    }

    public Optional<Membership> save(Membership membership) {

        try {

            entityManager.getTransaction().begin();

            entityManager.persist(membership);

            entityManager.getTransaction().commit();

            return Optional.of(membership);

        } catch (Exception exception) {

            exception.printStackTrace();

        }

        return Optional.empty();
    }
}
