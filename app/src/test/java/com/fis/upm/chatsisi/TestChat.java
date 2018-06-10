package com.fis.upm.chatsisi;

import com.fis.upm.chatsisi.entities.Chat;

import org.junit.Test;

public class TestChat {


    private Chat chat = new Chat("10/06/2018", 1, 2);

    @Test
    public void testChatSingletonConstructor() throws Exception {

        assert (chat != null);
    }

    @Test
    public void testFechaInicio(){


        assert (chat.getFecha_inicio()!=null && !chat.getFecha_inicio().equals(""));
    }

    @Test
    public void testFkUsuarioEnvia(){


        assert (chat.getFk_usuario_envia()>0);
    }

    @Test
    public void testFkUsuarioRecibe(){


        assert (chat.getFk_usuario_recibe()>0);
    }



}