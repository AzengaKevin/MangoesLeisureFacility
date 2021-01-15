package org.example.data.repositories;

import org.example.data.entities.Facility;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class FacilityRepository {

    private final EntityManager entityManager;

    public FacilityRepository(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public List<Facility> findAll() {
        return entityManager.createQuery("from Facility", Facility.class).getResultList();
    }

    public Optional<Facility> findById(Integer id) {
        Facility facility = entityManager.find(Facility.class, id);

        return (facility != null) ? Optional.of(facility) : Optional.empty();
    }

    public Optional<Facility> save(Facility facility) {

        try {

            entityManager.getTransaction().begin();

            entityManager.persist(facility);

            entityManager.getTransaction().commit();

            return Optional.of(facility);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

}
