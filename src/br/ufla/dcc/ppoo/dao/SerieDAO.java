package br.ufla.dcc.ppoo.dao;

/**
 * Interface do Data Access Object (Padrão de Projeto) da Serie
 *
 * @author Breno
 */
// importação das classes utilizadas
import br.ufla.dcc.ppoo.modelo.Serie;
import br.ufla.dcc.ppoo.modelo.Usuario;
import java.util.List;

public interface SerieDAO {

    /**
     * Adiciona um objeto Serie na Lista de Series
     */

    public void adicionarSerie(Serie serie);

    /**
     * Retonar um ArrayList contendo objetos da classe Serie
     */
    public List<Serie> getListaSeries(Usuario usuario);

    /**
     * Edita série a partir de um indicador da posição da Serie na Lista
     */
    public void editarSerie(Serie serie, String tituloSerie, Usuario usuario);

    /**
     * Deleta série a partir de um indicador da posição da Serie na Lista
     */
    public void deletarSerie(String nome, Usuario usuario);

    /**
     * Faz a persistencia das Series em um arquivo binário.
     */
    public void salvarSeriesArquivo();

    /**
     * Recupera os dados persistidos no arquivo binário.
     */
    public void recuperarSeriesArquivo();

}
