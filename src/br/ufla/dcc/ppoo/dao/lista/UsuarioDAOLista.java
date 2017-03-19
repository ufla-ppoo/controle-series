package br.ufla.dcc.ppoo.dao.lista;

import br.ufla.dcc.ppoo.dao.UsuarioDAO;
import br.ufla.dcc.ppoo.modelo.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação do Data Access Object (Padrão de Projeto) do Usuário através de
 * Lista em memória
 *
 * @author Breno
 */
public class UsuarioDAOLista implements UsuarioDAO, Serializable {

    // instância única da classe (Padrão de Projeto Singleton)
    private static UsuarioDAOLista instancia;

    // lista em em memória dos usuários cadastrados
    private List<Usuario> listaUsuario;

    /**
     * Constrói o objeto já definindo 5 usuários padrões
     */
    private UsuarioDAOLista() {
        listaUsuario = new ArrayList<Usuario>();

    }

    /**
     * Retorna a instância única da classe (Padrão de Projeto Singleton)
     *
     * @return A instância única da classe
     */
    public static UsuarioDAOLista obterInstancia() {
        if (instancia == null) {
            instancia = new UsuarioDAOLista();
        }
        return instancia;
    }

    /**
     * Retorna o usuário a partir de seu login
     *
     * @param login Login do usuário a ser retornado.
     * @return Usuário correspondente ao login passado.
     */
    @Override
    public Usuario obterUsuarioPeloLogin(String login) {
        for (Usuario u : listaUsuario) {
            if (login.equals(u.obterLogin())) {
                return u;
            }
        }
        return null;
    }

    /**
     * Cadastra o usuário passado.
     *
     * @param usuario Usuário a ser cadastrado.
     */
    @Override
    public void adicionarUsuario(Usuario usuario) {
        listaUsuario.add(usuario);
        salvarUsuariosArquivo();
    }

    /**
     * Faz a persistência de Usuários em um arquivo binário.
     */
    @Override
    public void salvarUsuariosArquivo() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usuarios.bin"));
            oos.writeObject(listaUsuario);
            oos.close();

        } catch (Exception e) {
        }

    }
    
    /**
     * Recuperar os dados persistidos no arquivo binário.
     */
    @Override
    public void recuperarUsuariosArquivo() {
        try {

            File f = new File("usuarios.bin");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            listaUsuario = (List<Usuario>) ois.readObject();
            ois.close();

        } catch (Exception e) {
        }

    }

    /**
     * Retorna a lista de Usuários cadastrados.
     */
    @Override
    public List<Usuario> getListaDeUsuarios() {
        return listaUsuario;
    }

    /**
     * Adiciona uma pontuação aos pontos do Usuário.
     */
    @Override
    public void setPontuacao(int pontuacao, Usuario usuario) {
        for (Usuario u : listaUsuario) {
            if (u.obterLogin().equals(usuario.obterLogin())) {
                u.setPontuacao(pontuacao);
            }
        }
        salvarUsuariosArquivo();
    }
}
