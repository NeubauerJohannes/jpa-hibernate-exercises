package com.bobocode.dao;

import com.bobocode.model.Photo;
import com.bobocode.model.PhotoComment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Please note that you should not use auto-commit mode for your implementation.
 */

public class PhotoDaoImpl implements PhotoDao {
    private EntityManagerFactory entityManagerFactory;


    public PhotoDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void save(Photo photo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        if (photo.getId() == 0) {
            entityManager.merge(photo);
        } else {
            entityManager.persist(photo);
        }

    }

    @Override
    public Photo findById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Photo.class, id);
    }

    @Override
    public List<Photo> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return  entityManager.createNamedQuery(Photo.class.getSimpleName() + "FindAll").getResultList();
        //return entityManager.createQuery("select e from Photo e").getResultList();
    }

    @Override
    public void remove(Photo photo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
       entityManager.getTransaction().begin();
        try {
            entityManager.remove(entityManager.merge(photo));

       entityManager.getTransaction().commit();
        } catch(Exception e){
        entityManager.getTransaction().rollback();
        } finally{
            entityManager.close();
        }
    }

    @Override
    public void addComment(long photoId, String comment) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        PhotoComment photoComment = new PhotoComment();
        photoComment.setText(comment);
        entityManager.find(Photo.class, photoId).addComment(photoComment);

    }
    @Override

    public List<Photo> findByDescription(String description) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Photo> photos = new ArrayList<>();
        try{
            photos = entityManager.createNamedQuery(Photo.class.getSimpleName() +
                    "FindByDescription").setParameter("description", description).getResultList();
            entityManager.getTransaction().commit();
        } catch(Exception e){
            entityManager.getTransaction().rollback();
        } finally{
            entityManager.close();
        }
        return photos;

    }

}
