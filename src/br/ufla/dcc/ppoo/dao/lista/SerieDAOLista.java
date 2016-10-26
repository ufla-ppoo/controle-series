package br.ufla.dcc.ppoo.dao.lista;

import br.ufla.dcc.ppoo.dao.SerieDAO;
import br.ufla.dcc.ppoo.modelo.Serie;
import java.util.ArrayList;
import java.util.List;


/**
 * Implementação do Data Access Object (Padrão de Projeto) da Serie através de
 * Lista em memória
 * 
 * @author Breno e Lucas
 */

public class SerieDAOLista implements SerieDAO {

    // instância única da classe (Padrão de Projeto Singleton)
    private static SerieDAOLista instancia;
    
    // lista em em memória dos usuários cadastrados
    private final List<Serie> listaSerie;

    private SerieDAOLista() {
        listaSerie = new ArrayList<Serie>();
   }

    /**
     * Retorna a instância única da classe (Padrão de Projeto Singleton)
     * 
     * @return A instância única da classe
     */
    public static SerieDAOLista obterInstancia() {
        if (instancia == null) {
            instancia = new SerieDAOLista();
        }
        return instancia;
    }
    
    /**
     * Adiciona um objeto Serie na Lista 
     * de Series
     */  
    @Override
    public List<Serie> getListaSeries(){
        return listaSerie;
    }

     /**
     * Retonar um ArrayList contendo
     * objetos da classe Serie
     */
    @Override
    public void adicionarSerie(Serie serie) {
        listaSerie.add(serie);
    }
    
     /**
     * Deleta série a partir
     * de um indicador da posição da Serie na Lista
     */
     public void editarSerie(Serie serie,int a) {
        listaSerie.get(a).setTitulo(serie.getTitulo());
        listaSerie.get(a).setGenero(serie.getGenero());
        listaSerie.get(a).setElenco(serie.getElenco());
        listaSerie.get(a).setNumeroDeTemporadas(serie.getNumeroDeTemporadas());
        listaSerie.get(a).setAnoLancamento(serie.getAnoLancamento());
    }

    /**
     * Deleta série a partir
     * de um indicador da posição da Serie na Lista
     */
    @Override
    public void deletarSerie(Serie serie, int a) {
        listaSerie.remove(a);
    }
}
