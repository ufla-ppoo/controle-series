package br.ufla.dcc.ppoo.dao;

import br.ufla.dcc.ppoo.modelo.Usuario;
import java.util.List;

/**
 * Interface do Data Access Object (Padrão de Projeto) do Usuário
 * 
 * @author Breno
 */
public interface UsuarioDAO {
    /**
     * Retorna o usuário a partir de seu login
     * 
     * @param login Login do usuário a ser retornado.
     * @return Usuário correspondente ao login passado.
     */
    public Usuario obterUsuarioPeloLogin(String login);
    
    /**
     * Cadastra o usuário passado.
     * 
     * @param usuario Usuário a ser cadastrado.
     */
    public void adicionarUsuario(Usuario usuario);
    
    public void SalvarUsuariosArquivo();
    
    public void RecuperarUsuariosArquivo();
}
