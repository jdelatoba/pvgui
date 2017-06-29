/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.almacen;

import java.util.ListIterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Condese
 */
public class TraspasoSolicitudTest {
    
    public TraspasoSolicitudTest() {
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

     /**
     * Test of setTraspaso method, of class Traspaso.
     */
  /*  @Test
    public void testSetTraspaso() throws Exception {
        System.out.println("setTraspasoSolicitud");
        TraspasoSolicitudBean tb = new TraspasoSolicitudBean();
        
        tb.setId(1);
        tb.setId_almacen_origen(1);
        tb.setId_solicita(2);
        tb.setId_almacen_destino(3);
        tb.setEstatus("A");
        tb.setSerie("A");
        
        
        TraspasoSolicitud instance = new TraspasoSolicitud();
        int expResult = 1;
        int result = instance.setTraspasoSolicitud(tb);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of updateTraspaso method, of class Traspaso.
     */
/*   @Test
    public void testUpdateTraspaso() throws Exception {
        System.out.println("updateTraspaso");
        TraspasoSolicitudBean tb = new TraspasoSolicitudBean();
        tb.setId(2);
        tb.setId_almacen_destino(7);
        tb.setEstatus("A");
        tb.setSerie("B");
        tb.setModificado_por(2);
        
        TraspasoSolicitud instance = new TraspasoSolicitud();
        int expResult = 1;
        int result = instance.updateTraspasoSolicitud(tb);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
      //  fail("The test case is a prototype.");
    }

    /**
     * Test of deleteTraspaso method, of class Traspaso.
     */
  /*  @Test
    public void testDeleteTraspaso() throws Exception {
        System.out.println("deleteTraspaso");
        TraspasoSolicitudBean tb = new TraspasoSolicitudBean();
        tb.setId(2);
        tb.setEstatus("D");
        tb.setModificado_por(2);
        TraspasoSolicitud instance = new TraspasoSolicitud();
        int expResult = 1;
        int result = instance.deleteTraspasoSolicitud(tb);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of getTraspaso method, of class Traspaso.
     */
    @Test
    public void testGetTraspaso() throws Exception {
        System.out.println("getTraspaso");
        int id = 2;
        TraspasoSolicitud instance = new TraspasoSolicitud();
        TraspasoSolicitudBean tb = null;
        tb = instance.getTraspasoSolicitud(id);
        System.out.println("id "+tb.getId());
        System.out.println("Almacen "+tb.getId_almacen_origen());
        System.out.println("solicita "+tb.getId_solicita());
        assertNotNull(tb);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
   
}
