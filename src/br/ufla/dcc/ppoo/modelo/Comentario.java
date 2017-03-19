package br.ufla.dcc.ppoo.modelo;

import java.io.Serializable;

/**
 *
 * @author Breno
 */
public class Comentario implements Serializable{
    
  private String comentario;
  Usuario usuario;

    public Comentario(String comentario, Usuario usuario) {
        this.comentario = comentario;
        this.usuario = usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
