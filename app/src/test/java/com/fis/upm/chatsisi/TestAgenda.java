package com.fis.upm.chatsisi;

import com.fis.upm.chatsisi.entities.Agenda;

import org.junit.Test;

public class TestAgenda {


    private Agenda agenda = new Agenda("agenda1",1,false);

    @Test
    public void testAgendaSingletonConstructor(){

        assert (agenda!=null);
    }
    @Test
    public void testNombre(){



        assert (agenda.getNombre_agenda()!=null);
    }


    @Test
    public void testFkUsuario(){
        assert (agenda.getFk_usuario()>0);
    }


    @Test
    public void testFavorita(){

        assert (agenda.isB_favorita() || !agenda.isB_favorita());
    }

}
