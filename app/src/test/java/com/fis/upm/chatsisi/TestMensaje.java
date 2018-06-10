package com.fis.upm.chatsisi;

import com.fis.upm.chatsisi.entities.Mensaje;

import org.junit.Assert;
import org.junit.Test;

public class TestMensaje {


    private Mensaje mensaje = new Mensaje(1,1,"esto es una prueba de mensaje","10/06/2018","13:00","36520222");

    @Test
    public void testMensajeSingletonConstructor() throws Exception {

        assert (mensaje != null);
    }

    @Test
    public void testFkChat(){


        assert (mensaje.getFk_chat()>0);
    }

    @Test
    public void testFkUsuario(){


        assert (mensaje.getFk_usuario()>0);
    }

    @Test
    public void testCuerpo(){


        assert (mensaje.getCuerpo()!=null);
    }


    @Test
    public void testFecha(){

        assert (mensaje.getFecha()!=null);

    }


    @Test
    public void testHora(){

        assert (mensaje.getHora()!=null);

    }


    @Test
    public void testMili(){

        assert (mensaje.getMili()!=null);

    }





}
