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
public class ArticuloTest {
    
    public ArticuloTest() {
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

//    /**
//     * Test of setArticulo method, of class Articulo.
//     */
//    @Test
//    public void testSetArticulo() throws Exception {
//        System.out.println("setArticulo");
//        ArticuloBean ab = null;
//        Articulo instance = new Articulo();
//        int expResult = 0;
//        int result = instance.setArticulo(ab);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateArticulo method, of class Articulo.
//     */
//    @Test
//    public void testUpdateArticulo() throws Exception {
//        System.out.println("updateArticulo");
//        ArticuloBean ab = null;
//        Articulo instance = new Articulo();
//        int expResult = 0;
//        int result = instance.updateArticulo(ab);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteArticulo method, of class Articulo.
//     */
//    @Test
//    public void testDeleteArticulo() throws Exception {
//        System.out.println("deleteArticulo");
//        ArticuloBean ab = null;
//        Articulo instance = new Articulo();
//        int expResult = 0;
//        int result = instance.deleteArticulo(ab);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getArticulo method, of class Articulo.
//     */
//    @Test
//    public void testGetArticulo_0args() throws Exception {
//        System.out.println("getArticulo");
//        Articulo instance = new Articulo();
//        ListIterator<ArticuloBean> expResult = null;
//        ListIterator<ArticuloBean> result = instance.getArticulo();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getArticulo method, of class Articulo.
//     */
//    @Test
//    public void testGetArticulo_String_String() throws Exception {
//        System.out.println("getArticulo");
//        String campo = "";
//        String buscar = "";
//        Articulo instance = new Articulo();
//        ListIterator<ArticuloBean> expResult = null;
//        ListIterator<ArticuloBean> result = instance.getArticulo(campo, buscar);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getArticulo method, of class Articulo.
//     */
//    @Test
//    public void testGetArticulo_int() throws Exception {
//        System.out.println("getArticulo");
//        int id = 0;
//        Articulo instance = new Articulo();
//        ListIterator<ArticuloBean> expResult = null;
//        ListIterator<ArticuloBean> result = instance.getArticulo(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getArticuloBean method, of class Articulo.
//     */
//    @Test
//    public void testGetArticuloBean() throws Exception {
//        System.out.println("getArticuloBean");
//        int id = 0;
//        Articulo instance = new Articulo();
//        ArticuloBean expResult = null;
//        ArticuloBean result = instance.getArticuloBean(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of buscarArticulo method, of class Articulo.
//     */
//    @Test
//    public void testBuscarArticulo() throws Exception {
//        System.out.println("buscarArticulo");
//        String buscar = "";
//        int todos_dep = 0;
//        int todos_cat = 0;
//        int con_existencia = 0;
//        int sin_existencia = 0;
//        Articulo instance = new Articulo();
//        ListIterator<ArticuloBean> expResult = null;
//        ListIterator<ArticuloBean> result = instance.buscarArticulo(buscar, todos_dep, todos_cat, con_existencia, sin_existencia);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getListaArticulo method, of class Articulo.
     */
    @Test
    public void testGetListaArticulo() throws Exception {
        System.out.println("getListaArticulo");
        String buscar = "REM";
        String tipo_busqueda = "clave";
        int categorias = 1;
        int departamentos = 1;
        int categoria_id = 0;
        int departamento_id = 0;
        int con_existencia = 1;
        int sin_existencia = 1;
        Articulo instance = new Articulo();
        JSONObject expResult = null;
        JSONObject result = instance.getListaArticulo(buscar, tipo_busqueda, categorias, departamentos, categoria_id, departamento_id, con_existencia, sin_existencia);
        assertNotNull(result);
        
        System.out.println(result.toJSONString());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
