package br.ufla.dcc.ppoo.servicos;

import br.ufla.dcc.ppoo.dao.SerieDAO;
import br.ufla.dcc.ppoo.dao.lista.SerieDAOLista;
import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.modelo.Serie;
import java.util.List;

/**
 * Classe que representa a camada de negócios de cadastro de Séries. Permite
 * cadastrar, editar e remover séries.
 * 
 * @author Breno e Lucas
 */

public class GerenciadorSeries {

    private final SerieDAO repositorioSerie;
    
    /**
     * Constroi o gerenciador de séries, inicializando as camadas de acesso a 
     * dados.
     */
    public GerenciadorSeries() {
        repositorioSerie
                = SerieDAOLista.obterInstancia();
            }

    public List<Serie> getListaSerie(){
    return repositorioSerie.getListaSeries();
    }

     /**
     * Cadastra uma série no sistema.
     * 
     * @param usuario Serie a ser cadastrada.
     */  
    
    public void cadastrarSerie(Serie serie){

        repositorioSerie.adicionarSerie(serie);
    }
    
     /**
     * Edita uma série no sistema.
     * 
     */  
    
    public void editarSerie(Serie serie,int a) {
        repositorioSerie.editarSerie(serie,a);
    }
    
     /**
     * Remove uma série no sistema.
     * 
     */  
    
    public void deletarSerie (Serie serie, int a){
        repositorioSerie.deletarSerie(serie, a);
    }
}
