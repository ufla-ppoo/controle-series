package br.ufla.dcc.ppoo.servicos;

import br.ufla.dcc.ppoo.dao.ListaSerieDAO;
import br.ufla.dcc.ppoo.dao.SerieDAO;
import br.ufla.dcc.ppoo.dao.lista.ListaSerieDAOLista;
import br.ufla.dcc.ppoo.dao.lista.SerieDAOLista;
import br.ufla.dcc.ppoo.modelo.Comentario;
import br.ufla.dcc.ppoo.modelo.ListaSerie;
import br.ufla.dcc.ppoo.modelo.Serie;
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
    
    public void cadastrarListaSeries(ListaSerie listaSerie){
        
        repositorioListaSerie.adicionarLista(listaSerie);
    }
    
    public List<ListaSerie> getListadeListaSerie(Usuario usuario){
        return repositorioListaSerie.getListaDeListaSerie(usuario);
    }
    
    public List<ListaSerie> getListaDeListaSeriePublicas(){
        return repositorioListaSerie.getListaDeListaSeriePublicas();
    }
    
     public void salvarListaSeriesArquivo (){
        repositorioListaSerie.salvarListaSeriesArquivo();
    }
    
    public void recuperarListaSeriesArquivo(){
        repositorioListaSerie.recuperarListaSeriesArquivo();
    }
    
    public void deletarListaSerie(String nome){
        repositorioListaSerie.deletarListaSerie(nome, sessaoUsuario.obterUsuario());
    }
    
    public void avaliarListaSerie(int nota, ListaSerie listaSerie, Usuario usuarioAvaliador){
        repositorioListaSerie.avaliarListaSerie(nota, listaSerie, usuarioAvaliador);
    }
    
    public void setComentario(Comentario comentario, ListaSerie listaSerie){
        repositorioListaSerie.setComentario(comentario, listaSerie);
    }

}
