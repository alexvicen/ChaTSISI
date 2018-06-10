package com.fis.upm.chatsisi;

import com.fis.upm.chatsisi.entities.Usuario;

import org.junit.Test;

public class TestUser {


    private Usuario usuario = new Usuario("a.cperez","contraseña","alvaro","calzado","a.cperez@alumnos.upm.es","disponible","descripcion");

    @Test
    public void testUsuarioSingletonConstructor() throws Exception {

        assert (usuario != null);
    }
    @Test
    public void testLogin(){
        assert (usuario.getNombre_usuario()!=null);
    }
    @Test
    public void testConstraseña(){
        assert (usuario.getContrasena_usuario()!=null);
    }
    @Test
    public void testNombre(){
        assert (usuario.getNombre_usuario()!=null);
    }

    @Test
    public void testApellidos() {
        assert (usuario.getApellidos_usuario()!=null);
    }

    @Test
    public void testCorreo() {
        assert (usuario.getCorreo_usuario()!= null);
    }

    @Test
    public void testCorreoUPM() {
        assert (usuario.getCorreo_usuario().contains("@alumnos.upm.es"));
    }


    @Test
    public void testEstado() {
        assert (usuario.getEstado_usuario()!=null);

    }


    @Test
    public void testDescripcion() {
        assert (usuario.getDescripcion_usuario()!= null);

    }


}
