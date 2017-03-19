package br.ufla.dcc.ppoo.modelo;

import java.io.Serializable;

/**
 * Classe que representa um Comentário de um Lista de Série.
 *
 * @author Breno
 */
public class Comentario implements Serializable {

    private String comentario;
    Usuario usuario;

    /**
     * Construtor único.
     */
    public Comentario(String comentario, Usuario usuario) {
        this.comentario = comentario;
        this.usuario = usuario;
    }

    /**
     * Retorna a mensagem de comentário.
     */
    public String getComentario() {
        return comentario;
    }

     /**
     * Efetua um comentário.
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    /**
     * Retorna o usuário que comentou.
     */
    public Usuario getUsuario() {
        return usuario;
    }
    
    /**
     * Indica um usuário que comentou.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
