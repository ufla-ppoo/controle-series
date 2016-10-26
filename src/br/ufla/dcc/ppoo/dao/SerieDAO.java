package br.ufla.dcc.ppoo.dao;

/**
 * Interface do Data Access Object (Padrão de Projeto) da Serie
 * @author Breno e lucas
 */

// importação das classes utilizadas
import br.ufla.dcc.ppoo.modelo.Serie;
import java.util.List;

public interface SerieDAO {
    /**
     * Adiciona um objeto Serie na Lista 
     * de Series
     */

    public void adicionarSerie(Serie serie);
    /**
     * Retonar um ArrayList contendo
     * objetos da classe Serie
     */
    public List<Serie> getListaSeries();
    /**
     * Edita série a partir
     * de um indicador da posição da Serie na Lista
     */
    public void editarSerie(Serie serie,int a);
    /**
     * Deleta série a partir
     * de um indicador da posição da Serie na Lista
     */
    public void deletarSerie(Serie serie, int a);
    
}
