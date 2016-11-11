/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import entidades.Computadoras;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author edgar
 */
public class ComputadorasJpaController implements Serializable {

    public ComputadorasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Computadoras computadoras) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(computadoras);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findComputadoras(computadoras.getId()) != null) {
                throw new PreexistingEntityException("Computadoras " + computadoras + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Computadoras computadoras) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            computadoras = em.merge(computadoras);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = computadoras.getId();
                if (findComputadoras(id) == null) {
                    throw new NonexistentEntityException("The computadoras with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Computadoras computadoras;
            try {
                computadoras = em.getReference(Computadoras.class, id);
                computadoras.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The computadoras with id " + id + " no longer exists.", enfe);
            }
            em.remove(computadoras);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Computadoras> findComputadorasEntities() {
        return findComputadorasEntities(true, -1, -1);
    }

    public List<Computadoras> findComputadorasEntities(int maxResults, int firstResult) {
        return findComputadorasEntities(false, maxResults, firstResult);
    }

    private List<Computadoras> findComputadorasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Computadoras.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Computadoras findComputadoras(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Computadoras.class, id);
        } finally {
            em.close();
        }
    }

    public int getComputadorasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Computadoras> rt = cq.from(Computadoras.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
