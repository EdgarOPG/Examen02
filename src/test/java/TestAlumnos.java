/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entidades.Alumno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author edgar
 */
public class TestAlumnos {
    
    public TestAlumnos() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void hello() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HibernateExamen");
        
        EntityManager em = emf.createEntityManager();
        
        Alumno a = new Alumno("Edgar", "Peinado", "Garcia", 21, "PEGE950318HCHNRD04", "PEGE950318");
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
        
        Query q = em.createQuery("SELECT a from Alumno a");
        
        List<Alumno> alumnos = q.getResultList();
        
        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
        
//        a.setApellido2("Chaparro");
//        em.getTransaction().begin();
//        em.persist(a);
//        em.getTransaction().commit();
//
//        em.remove(a);
    }
}
