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
    
    public void adicionarLista(ListaSerie listaSerie);
    
    public List<ListaSerie> getListaDeListaSerie(Usuario usuario);  
    
    public List<ListaSerie> getListaDeListaSeriePublicas();
    
    public void salvarListaSeriesArquivo();
    
    public void recuperarListaSeriesArquivo();
    
    public void deletarListaSerie(String nome, Usuario usuario);
    
    public void avaliarListaSerie(int nota, ListaSerie listaSerie, Usuario usuarioAvaliador);
    
    public void setComentario(Comentario Comentario, ListaSerie listaSerie);
}
