package org.example.data.repositories;

import org.example.data.entities.Staff;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class StaffRepository {

    private final EntityManager entityManager;

    public StaffRepository(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public List<Staff> findAll() {
        return entityManager.createQuery("from Staff", Staff.class)
                .getResultList();
    }

    public Optional<Staff> findById(Integer id) {

        Staff staff = entityManager.find(Staff.class, id);

        return (staff != null) ? Optional.of(staff) : Optional.empty();
    }

    public Optional<Staff> save(Staff staff) {

        try {

            entityManager.getTransaction().begin();

            entityManager.persist(staff);

            entityManager.getTransaction().commit();

            return Optional.of(staff);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return Optional.empty();
    }
}
