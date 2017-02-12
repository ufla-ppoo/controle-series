package br.ufla.dcc.ppoo.modelo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Breno
 */
public class ListaSerie implements Serializable {
    
    private String nome;
    private boolean privado = true;
    String palavrasChave;
    Usuario usuario;
    List<Serie> series;

    public ListaSerie(String nome, boolean visivel, String palavrasChave, Usuario usuario, List<Serie> series) {
        this.nome = nome;
        this.privado = visivel;
        this.palavrasChave = palavrasChave;
        this.usuario = usuario;
        this.series = series;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isVisivel() {
        return privado;
    }

    public void setVisivel(boolean visivel) {
        this.privado = visivel;
    }

    public String getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(String palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }
    
    
}
