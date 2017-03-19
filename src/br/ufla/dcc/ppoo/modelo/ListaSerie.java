package br.ufla.dcc.ppoo.modelo;

import br.ufla.dcc.ppoo.servicos.GerenciadorUsuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma Lista de Série.
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

    /**
     * Construtor único.
     */
    public ListaSerie(String nome, boolean visivel, String palavrasChave, Usuario usuario, List<Serie> series) {
        this.nome = nome;
        this.privado = visivel;
        this.palavrasChave = palavrasChave;
        this.usuario = usuario;
        this.series = series;
        avaliadores = new ArrayList<Usuario>();
        comentarios = new ArrayList<Comentario>();
    }

    /**
     * Retorna o nome da Lista de Série.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Coloca o nome na Lista de Série.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna a visibilidade da Lista de Série.
     */
    public boolean isVisivel() {
        return privado;
    }

    /**
     * Coloca a visibilidade da Lista de Série.
     */
    public void setVisivel(boolean visivel) {
        this.privado = visivel;
    }

    /**
     * Retorna as palavras-chave da Lista de Série.
     */
    public String getPalavrasChave() {
        return palavrasChave;
    }

    /**
     * Coloca as palavras-chave na Lista de Série.
     */
    public void setPalavrasChave(String palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    /**
     * Retorna o autor da Lista de Série.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Adiciona o autor da Lista de Série.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Retorna as Séries contidas na Lista de Série.
     */
    public List<Serie> getSeries() {
        return series;
    }

    /**
     * Adiciona uma coleção de Séries na Lista de Série.
     */
    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    /**
     * Adiciona uma avaliação na Lista de Série.
     */
    public void setAvaliacao(int nota, Usuario usuarioAvaliador) {
        pontos += nota;
        new GerenciadorUsuarios().setPontuacao(nota, usuario);
        avaliadores.add(usuarioAvaliador);
    }

    /**
     * Confere se um Usuário já avaliou a Lista de Série.
     */
    public boolean usuarioJaAvaliou(Usuario usuario) {

        for (int i = 0; i < avaliadores.size(); i++) {
            if (avaliadores.get(i).obterLogin().equals(usuario.obterLogin())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Retorna os pontos da Lista de Série.
     */
    public int getPontos() {
        return pontos;
    }

    /**
     * Compara listas por pontos para ordenação.
     *
     */
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

    /**
     * Retorna os comentários da Lista de Série.
     */
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    /**
     * Adiciona um comentário na Lista de Série.
     */
    public void setComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

}
