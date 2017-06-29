/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.catalogo;

import java.util.ListIterator;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rembao
 */
public class CategoriaTest {
    
    public CategoriaTest() {
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
     * Test of setCategoria method, of class Categoria.
     */
//    @Test
//    public void testSetCategoria() throws Exception {
//        System.out.println("setCategoria");
//        CategoriaBean cb = null;
//        Categoria instance = new Categoria();
//        int expResult = 0;
//        int result = instance.setCategoria(cb);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of updateCategoria method, of class Categoria.
     */
//    @Test
//    public void testUpdateCategoria() throws Exception {
//        System.out.println("updateCategoria");
//        CategoriaBean cb = null;
//        Categoria instance = new Categoria();
//        int expResult = 0;
//        int result = instance.updateCategoria(cb);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of deleteCategoria method, of class Categoria.
     */
//    @Test
//    public void testDeleteCategoria() throws Exception {
//        System.out.println("deleteCategoria");
//        CategoriaBean cb = null;
//        Categoria instance = new Categoria();
//        int expResult = 0;
//        int result = instance.deleteCategoria(cb);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getCategoria method, of class Categoria.
     */
//    @Test
//    public void testGetCategoria_0args() throws Exception {
//        System.out.println("getCategoria");
//        Categoria instance = new Categoria();
//        ListIterator<CategoriaBean> expResult = null;
//        ListIterator<CategoriaBean> result = instance.getCategoria();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getCategoria method, of class Categoria.
     */
//    @Test
//    public void testGetCategoria_int() throws Exception {
//        System.out.println("getCategoria");
//        int id = 0;
//        Categoria instance = new Categoria();
//        CategoriaBean expResult = null;
//        CategoriaBean result = instance.getCategoria(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getCategoriaListaJSON method, of class Categoria.
     */
    @Test
    public void testGetCategoriaListaJSON() throws Exception {
        System.out.println("getCategoriaListaJSON");
        int draw = 0;
        Categoria instance = new Categoria();
        JSONObject expResult = null;
        JSONObject result = instance.getCategoriaListaJSON(draw);
        System.out.println(result.toJSONString());
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
