package br.ufla.dcc.ppoo.servicos;

import br.ufla.dcc.ppoo.dao.UsuarioDAO;
import br.ufla.dcc.ppoo.dao.lista.UsuarioDAOLista;
import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.modelo.Usuario;
import br.ufla.dcc.ppoo.seguranca.SessaoUsuario;
import java.io.Serializable;
import java.util.List;

/**
 * Classe que representa a camada de negócios de cadastro de usuários. Permite
 * cadastrar e autenticar um usuário.
 *
 * @author Breno
 */
public class GerenciadorUsuarios implements Serializable {

    // atributo utilizado como camada de acesso a dados do cadastro de usuários
    private final UsuarioDAO repositorioUsuario;
    // atributo para controle de sessão (autenticação do usuário)
    private final SessaoUsuario sessaoUsuario;

    /**
     * Constroi o gerenciador de usuários, inicializando as camadas de acesso a
     * dados e de sessão.
     */
    public GerenciadorUsuarios() {
        repositorioUsuario
                = UsuarioDAOLista.obterInstancia();
        sessaoUsuario
                = SessaoUsuario.obterInstancia();
    }

    /**
     * Tenta autenticar o usuário passado no sistema.
     *
     * @param usuario Usuário a ser autenticado
     * @throws Exception Exceção gerada caso o usuário não possa ser
     * autenticado, ou seja, usuário não existe ou senha incorreta.
     */
    public void autenticarUsuario(Usuario usuario) throws Exception {
        Usuario usuarioCadastrado
                = repositorioUsuario.obterUsuarioPeloLogin(usuario.obterLogin());

        sessaoUsuario.alterarUsuario(usuarioCadastrado, usuario.obterSenha());
    }

    /**
     * Cadastra o usuário passado no sistema.
     *
     * @param usuario Usuário a ser cadastrado.
     * @throws Exception Exceção gerada caso o usuário já esteja cadastrado.
     */
    public void cadastrarUsuario(Usuario usuario) throws Exception {
        Usuario ret = repositorioUsuario.obterUsuarioPeloLogin(usuario.obterLogin());
        if (ret != null) {
            throw new Exception(I18N.obterErroUsuarioJaCadastrado());
        }
        repositorioUsuario.adicionarUsuario(usuario);
    }

    /**
     * Faz a persistência de Usuários em um arquivo binário.
     */
    public void SalvarUsuariosArquivo() {
        repositorioUsuario.salvarUsuariosArquivo();
    }

    /**
     * Recuperar os dados persistidos no arquivo binário.
     */
    public void RecuperarUsuariosArquivo() {
        repositorioUsuario.recuperarUsuariosArquivo();
    }

    /**
     * Retorna a lista de Usuários cadastrados.
     */
    public List<Usuario> getListadeListaUsuarios() {
        return repositorioUsuario.getListaDeUsuarios();
    }

    /**
     * Adiciona uma pontuação aos pontos do Usuário.
     */
    public void setPontuacao(int pontuacao, Usuario usuario) {
        repositorioUsuario.setPontuacao(pontuacao, usuario);
    }

}
