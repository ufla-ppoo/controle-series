package br.ufla.dcc.ppoo.dao;

import br.ufla.dcc.ppoo.modelo.Serie;
import java.util.List;

public interface SerieDAO {

    public void adicionarSerie(Serie serie);
    public List<Serie> getListaSeries();
    
}
