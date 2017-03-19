package br.ufla.dcc.ppoo.servicos;

import br.ufla.dcc.ppoo.dao.SerieDAO;
import br.ufla.dcc.ppoo.dao.lista.SerieDAOLista;
import br.ufla.dcc.ppoo.modelo.Serie;
import br.ufla.dcc.ppoo.modelo.Usuario;
import br.ufla.dcc.ppoo.seguranca.SessaoUsuario;
import java.io.Serializable;
import java.util.List;

/**
 * Classe que representa a camada de negócios de cadastro de Séries. Permite
 * cadastrar, editar e remover séries.
 *
 * @author Breno
 */
public class GerenciadorSeries implements Serializable {

    // Utiliza a interface por conta do polimorfismo
    private final SerieDAO repositorioSerie;
    private final SessaoUsuario sessaoUsuario;

    /**
     * Constroi o gerenciador de séries, inicializando as camadas de acesso a
     * dados.
     */
    public GerenciadorSeries() {
        repositorioSerie = SerieDAOLista.obterInstancia();
        sessaoUsuario = SessaoUsuario.obterInstancia();

    }

    public List<Serie> getListaSerie(Usuario usuario) {
        return repositorioSerie.getListaSeries(usuario);
    }

    /**
     * Cadastra uma série no sistema.
     *
     * @param usuario Serie a ser cadastrada.
     */
    public void cadastrarSerie(Serie serie) {

        repositorioSerie.adicionarSerie(serie);
        // Chamar metodo para adicionar a serie no arquivo.
    }

    /**
     * Edita uma série no sistema.
     *
     */
    public void editarSerie(Serie serie, String tituloSerie) {
        repositorioSerie.editarSerie(serie, tituloSerie, sessaoUsuario.obterUsuario());
    }

    /**
     * Remove uma série no sistema.
     *
     */
    public void deletarSerie(String nome) {
        repositorioSerie.deletarSerie(nome, sessaoUsuario.obterUsuario()); // Envia o usuario logado
    }

    /**
     * Faz a persistencia das Series em um arquivo binário.
     */
    public void SalvarSeriesArquivo() {
        repositorioSerie.salvarSeriesArquivo();
    }

    /**
     * Recupera os dados persistidos no arquivo binário.
     */
    public void RecuperarSeriesArquivo() {
        repositorioSerie.recuperarSeriesArquivo();
    }
}
