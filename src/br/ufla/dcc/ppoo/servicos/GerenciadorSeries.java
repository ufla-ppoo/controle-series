package br.ufla.dcc.ppoo.servicos;

import br.ufla.dcc.ppoo.dao.SerieDAO;
import br.ufla.dcc.ppoo.dao.lista.SerieDAOLista;
import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.modelo.Serie;
import java.util.List;

public class GerenciadorSeries {

    private final SerieDAO repositorioSerie;
    

    public GerenciadorSeries() {
        repositorioSerie
                = SerieDAOLista.obterInstancia();
            }

    public List<Serie> getListaSerie(){
    return repositorioSerie.getListaSeries();
    }

    public void cadastrarSerie(Serie serie){

        repositorioSerie.adicionarSerie(serie);
    }
}
