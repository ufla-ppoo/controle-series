package br.ufla.dcc.ppoo.modelo;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Representa um usuário do sistema.
 *
 * @author Breno
 */
public class Usuario implements Serializable, Comparable<Usuario> {

    // login do usuário
    private String login;
    // senha do usuário
    private char[] senha;
    // nome do usuário
    private String nome;
    // email do usuário
    private String email;
    // pontuaçao das listas do usuário
    private int pontuacao = 0;

    /**
     * Constrói um usuário a partir de seu login, senha e nome.
     *
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @param nome Nome do usuário.
     */
    public Usuario(String login, char[] senha, String nome) {
        this.login = login;
        this.senha = Arrays.copyOf(senha, senha.length);
        this.nome = nome;
    }

    /**
     * Constrói um usuário a partir de seu login, senha, nome e e-mail
     *
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @param nome Nome do usuário.
     * @param email E-mail do usuário.
     */
    public Usuario(String login, char[] senha, String nome, String email) {
        this.login = login;
        this.senha = Arrays.copyOf(senha, senha.length);
        this.nome = nome;
        this.email = email;
    }
    
    /**
     * Retorna o e-mail do Usuário.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Constrói um usuário a partir de seu login e senha (deixa nome vazio).
     *
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     */
    public Usuario(String login, char[] senha) {
        this(login, senha, "");
    }

    /**
     * Retorna o login do usuário.
     *
     * @return O login do usuário.
     */
    public String obterLogin() {
        return login;
    }

    /**
     * Retorna a senha do usuário.
     *
     * @return A senha do usuário.
     */
    public char[] obterSenha() {
        return senha;
    }

    /**
     * Retorna o nome do usuário.
     *
     * @return O nome do usuário.
     */
    public String obterNome() {
        return nome;
    }

    /**
     * Retorna a pontuação total do Usuário.
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * Adiciona uma pontuação ao usuário.
     */
    public void setPontuacao(int pontuacao) {
        this.pontuacao += pontuacao;
    }

    /**
     * Compara listas por pontos para ordenação.
     */
    @Override
    public int compareTo(Usuario usuario) {

        if (this.getPontuacao() < usuario.getPontuacao()) {
            return 1;
        }
        if (this.getPontuacao() > usuario.getPontuacao()) {
            return -1;
        }

        return 0;
    }

}
