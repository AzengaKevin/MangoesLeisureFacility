package org.example.data.repositories;

import org.example.data.entities.Category;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class CategoryRepository {

    private final EntityManager entityManager;

    public CategoryRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Category> findById(Integer id) {

        Category category = entityManager.find(Category.class, id);

        return (category == null) ? Optional.empty() : Optional.of(category);

    }

    public Optional<Category> findByName(String name) {
        Category category = entityManager.createNamedQuery("Category.findByName", Category.class)
                .setParameter("name", name)
                .getSingleResult();

        return (category == null) ? Optional.empty() : Optional.of(category);
    }

    public List<Category> findAll() {
        return entityManager.createNamedQuery("Category.findAll", Category.class)
                .getResultList();
    }

    public Optional<Category> save(Category category) {

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(category);

            entityManager.getTransaction().commit();

            return Optional.of(category);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
