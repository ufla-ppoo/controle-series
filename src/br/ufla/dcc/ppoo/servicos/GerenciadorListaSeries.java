package br.ufla.dcc.ppoo.servicos;

import br.ufla.dcc.ppoo.dao.ListaSerieDAO;
import br.ufla.dcc.ppoo.dao.SerieDAO;
import br.ufla.dcc.ppoo.dao.lista.ListaSerieDAOLista;
import br.ufla.dcc.ppoo.dao.lista.SerieDAOLista;
import br.ufla.dcc.ppoo.modelo.Comentario;
import br.ufla.dcc.ppoo.modelo.ListaSerie;
import br.ufla.dcc.ppoo.modelo.Usuario;
import br.ufla.dcc.ppoo.seguranca.SessaoUsuario;
import java.util.List;

/**
 *
 * @author Breno
 */
public class GerenciadorListaSeries {

    // Utiliza a interface por conta do polimorfismo
    private final SerieDAO repositorioSerie;
    private final SessaoUsuario sessaoUsuario;
    private final ListaSerieDAO repositorioListaSerie;

    public GerenciadorListaSeries() {
        repositorioSerie = SerieDAOLista.obterInstancia();
        sessaoUsuario = SessaoUsuario.obterInstancia();
        repositorioListaSerie = ListaSerieDAOLista.obterInstancia();
    }

    /**
     * Adiciona um objeto listaSerie na Lista de Series
     */
    public void cadastrarListaSeries(ListaSerie listaSerie) {

        repositorioListaSerie.adicionarLista(listaSerie);
    }

    /**
     * Retorna uma coleção de ListaSeries.
     */
    public List<ListaSerie> getListadeListaSerie(Usuario usuario) {
        return repositorioListaSerie.getListaDeListaSerie(usuario);
    }

    /**
     * Retorna uma coleção de ListaSeries Públicas
     */
    public List<ListaSerie> getListaDeListaSeriePublicas() {
        return repositorioListaSerie.getListaDeListaSeriePublicas();
    }

    /**
     * Faz a persistência das Series no arquivo binário.
     */
    public void salvarListaSeriesArquivo() {
        repositorioListaSerie.salvarListaSeriesArquivo();
    }

    /**
     * Recuperar os dados persistidos do arquivo binário.
     */
    public void recuperarListaSeriesArquivo() {
        repositorioListaSerie.recuperarListaSeriesArquivo();
    }

    /**
     * Remove uma ListaSerie da lista de ListaSeries.
     */
    public void deletarListaSerie(String nome) {
        repositorioListaSerie.deletarListaSerie(nome, sessaoUsuario.obterUsuario());
    }

    /**
     * Avalia uma ListaSerie.
     */
    public void avaliarListaSerie(int nota, ListaSerie listaSerie, Usuario usuarioAvaliador) {
        repositorioListaSerie.avaliarListaSerie(nota, listaSerie, usuarioAvaliador);
    }

    /**
     * Adiciona um Comentario em uma ListaSerie.
     */
    public void setComentario(Comentario comentario, ListaSerie listaSerie) {
        repositorioListaSerie.setComentario(comentario, listaSerie);
    }

    /**
     * Altera uma ListaSerie da lista de ListaSeries.
     */
    public void editarListaSerie(ListaSerie listaSerie, ListaSerie novaLista, Usuario usuario) {
        repositorioListaSerie.alterarListaserie(listaSerie, novaLista, usuario);
    }

    /**
     * Checa se os campos foram preenchidos corretamente.
     */
    public boolean isCamposCorretos(ListaSerie listaSerie) {
        return repositorioListaSerie.isCamposCorretos(listaSerie);
    }
    
    /**
     * Checa se os campos foram preenchidos corretamente na hora de editar uma ListaSerie.
     */
    public boolean isCamposCorretosEditar(ListaSerie listaSerie) {
        return repositorioListaSerie.isCamposCorretosEditar(listaSerie);
    }

}
