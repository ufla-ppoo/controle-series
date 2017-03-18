package br.ufla.dcc.ppoo.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Breno
 */
public class ListaSerie implements Serializable, Comparable<ListaSerie> {
    
    private String nome;
    private boolean privado = true;
    String palavrasChave;
    Usuario usuario;
    List<Serie> series;
    private int pontos = 0;
    List<Usuario> avaliadores;
    List<Comentario> comentarios;

    public ListaSerie(String nome, boolean visivel, String palavrasChave, Usuario usuario, List<Serie> series) {
        this.nome = nome;
        this.privado = visivel;
        this.palavrasChave = palavrasChave;
        this.usuario = usuario;
        this.series = series;
        avaliadores = new ArrayList<Usuario>();
        comentarios = new ArrayList<Comentario>();
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
    
    public void setAvaliacao(int nota, Usuario usuarioAvaliador){
        pontos+= nota;
        avaliadores.add(usuarioAvaliador);
        
    }
    
    public boolean usuarioJaAvaliou(Usuario usuario){
        
        for (int i = 0; i < avaliadores.size(); i++){
            if (avaliadores.get(i).obterLogin().equals(usuario.obterLogin())){
                return true;
            }
        }
        
        return false;
    } 

    public int getPontos() {
        return pontos;
    }
    
    // Compara listas por pontos para ordenação.
    @Override
    public int compareTo(ListaSerie lista) {
        
        if (this.getPontos() < lista.getPontos()) {
            return 1;
        }
        if (this.getPontos() > lista.getPontos()) {
            return -1;
        }
        
        return 0;        
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentario(Comentario comentario) {
        comentarios.add(comentario);
    }
    
    
    
}
