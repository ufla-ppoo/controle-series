package br.ufla.dcc.ppoo.dao.lista;

import br.ufla.dcc.ppoo.dao.SerieDAO;
import br.ufla.dcc.ppoo.modelo.Serie;
import java.util.ArrayList;
import java.util.List;

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
    
    @Override
    public List<Serie> getListaSeries(){
        return listaSerie;
    }

    @Override
    public void adicionarSerie(Serie serie) {
        listaSerie.add(serie);
    }
}
