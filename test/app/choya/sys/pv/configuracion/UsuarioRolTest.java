/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.configuracion;

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
public class UsuarioRolTest {
    
    public UsuarioRolTest() {
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

    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setUsuarioRol method, of class UsuarioRol.
     */
    @Test
    public void testSetUsuarioRol() throws Exception {
        System.out.println("setUsuarioRol");
        int usuario_id = 1;
        int rol_id = 1;
        int creado_por = 1;
        UsuarioRol instance = new UsuarioRol();
        int expResult = 1;
        int result = instance.setUsuarioRol(usuario_id, rol_id, creado_por);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
