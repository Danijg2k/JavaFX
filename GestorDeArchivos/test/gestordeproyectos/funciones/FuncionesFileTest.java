/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.funciones;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author dani
 */
public class FuncionesFileTest {

    public FuncionesFileTest() {
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
     * Test of crearDirectorio method, of class FuncionesFile.
     */
    @Test
    public void testCrearDirectorio_EMPTY_OK() {
        System.out.println("crearDirectorioEmpty");
        String ruta = "";
        FuncionesFile.crearDirectorio(ruta);
    }

    @Test
    public void testCrearDirectorio_NULL_OK() {
        System.out.println("crearDirectorioNull");
        String ruta = null;
        FuncionesFile.crearDirectorio(ruta);
    }

    /**
     * Test of borrarDirectorio method, of class FuncionesFile.
     */
    @Test
    public void testBorrarDirectorio_EMPTY_OK() throws Exception {
        System.out.println("borrarDirectorioEmpty");
        String ruta = "";
        FuncionesFile.borrarDirectorio(ruta);
    }

    @Test
    public void testBorrarDirectorio_NULL_OK() throws Exception {
        System.out.println("borrarDirectorioNull");
        String ruta = null;
        FuncionesFile.borrarDirectorio(ruta);
    }

    /**
     * Test of crearActualizarFichero method, of class FuncionesFile.
     */
    @Test
    public void testCrearActualizarFichero_EMPTY_OK() {
        System.out.println("crearActualizarFicheroEmpty");
        String ruta = "";
        String contenido = "";
        FuncionesFile.crearActualizarFichero(ruta, contenido);
    }

    @Test
    public void testCrearActualizarFichero_NULL_OK() {
        System.out.println("crearActualizarFicheroNull");
        String ruta = null;
        String contenido = null;
        FuncionesFile.crearActualizarFichero(ruta, contenido);
    }

    /**
     * Test of crearFichero method, of class FuncionesFile.
     */
    @Test
    public void testCrearFichero_EMPTY_OK() {
        System.out.println("crearFicheroEmpty");
        String ruta = "";
        FuncionesFile.crearFichero(ruta);
    }

    @Test
    public void testCrearFichero_NULL_OK() {
        System.out.println("crearFicheroNull");
        String ruta = null;
        FuncionesFile.crearFichero(ruta);
    }

    /**
     * Test of borrarFichero method, of class FuncionesFile.
     */
    @Test
    public void testBorrarFichero_EMPTY_OK() {
        System.out.println("borrarFicheroEmpty");
        String ruta = "";
        FuncionesFile.borrarFichero(ruta);
    }

    @Test
    public void testBorrarFichero_NULL_OK() {
        System.out.println("borrarFicheroNull");
        String ruta = null;
        FuncionesFile.borrarFichero(ruta);
    }

}
