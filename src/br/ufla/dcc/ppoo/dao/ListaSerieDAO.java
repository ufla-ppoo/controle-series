package br.ufla.dcc.ppoo.dao;

import br.ufla.dcc.ppoo.modelo.Comentario;
import br.ufla.dcc.ppoo.modelo.ListaSerie;
import br.ufla.dcc.ppoo.modelo.Usuario;
import java.util.List;

/**
 *
 * @author Breno
 */
public interface ListaSerieDAO {

    /**
     * Adiciona um objeto listaSerie na Lista de Series
     */
    public void adicionarLista(ListaSerie listaSerie);
    
    /**
     * Retorna uma coleção de ListaSeries.
     */
    public List<ListaSerie> getListaDeListaSerie(Usuario usuario);
    
    /**
     * Retorna uma coleção de ListaSeries Públicas
     */
    public List<ListaSerie> getListaDeListaSeriePublicas();

    /**
     * Faz a persistência das Series no arquivo binário.
     */
    public void salvarListaSeriesArquivo();
  
    /**
     * Recuperar os dados persistidos do arquivo binário.
     */
    public void recuperarListaSeriesArquivo();
    
    /**
     * Remove uma ListaSerie da lista de ListaSeries.
     */
    public void deletarListaSerie(String nome, Usuario usuario);
   
    /**
     * Altera uma ListaSerie da lista de ListaSeries.
     */
    public void alterarListaserie(ListaSerie listaSerie, ListaSerie novaLista, Usuario usuario);
   
    /**
     * Avalia uma ListaSerie.
     */
    public void avaliarListaSerie(int nota, ListaSerie listaSerie, Usuario usuarioAvaliador);
    
    /**
     * Adiciona um Comentario em uma ListaSerie.
     */
    public void setComentario(Comentario Comentario, ListaSerie listaSerie);
   
    /**
     * Checa se os campos foram preenchidos corretamente.
     */
    public boolean isCamposCorretos(ListaSerie listaSerie);
    
    /**
     * Checa se os campos foram preenchidos corretamente na hora de editar uma ListaSerie.
     */
    public boolean isCamposCorretosEditar(ListaSerie listaSerie);
}
