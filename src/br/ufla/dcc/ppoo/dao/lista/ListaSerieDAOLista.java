package br.ufla.dcc.ppoo.dao.lista;

import br.ufla.dcc.ppoo.dao.ListaSerieDAO;
import br.ufla.dcc.ppoo.dao.SerieDAO;
import br.ufla.dcc.ppoo.modelo.ListaSerie;
import br.ufla.dcc.ppoo.modelo.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Breno
 */
public class ListaSerieDAOLista implements ListaSerieDAO, Serializable {
    
    // instância única da classe (Padrão de Projeto Singleton)
    private static ListaSerieDAOLista instancia;
    
    // lista em em memória das listas cadastrados
    private List<ListaSerie> listaDeListasSerie;
    
    public ListaSerieDAOLista(){
        listaDeListasSerie = new ArrayList<ListaSerie>();
    }
    
    /**
     * Retorna a instância única da classe (Padrão de Projeto Singleton)
     * 
     * @return A instância única da classe
     */
    public static ListaSerieDAOLista obterInstancia() {
        if (instancia == null) {
            instancia = new ListaSerieDAOLista();
        }
        return instancia;
    }

    @Override
    public void adicionarLista(ListaSerie listaSerie) {
        listaDeListasSerie.add(listaSerie);
        salvarListaSeriesArquivo();
    }

    @Override
    public List<ListaSerie> getListaDeListaSerie(Usuario usuario) {
        List<ListaSerie> listaFinal = new ArrayList<>();
        
        for (ListaSerie listaSerie : listaDeListasSerie) {
            if (listaSerie.getUsuario().obterLogin().equals(usuario.obterLogin())){
                listaFinal.add(listaSerie);
            }
        }
        
        return listaFinal;
    }

    @Override
    public List<ListaSerie> getListaDeListaSeriePublicas() {
        List<ListaSerie> listaFinal = new ArrayList<>();
        
        for (ListaSerie listaSerie : listaDeListasSerie) {
            if (!listaSerie.isVisivel()){
                listaFinal.add(listaSerie);
            }
        }
        
        return listaFinal;
    }
    
    @Override
    public void salvarListaSeriesArquivo(){
        try {
        ObjectOutputStream oos = new ObjectOutputStream(new
        FileOutputStream("ListaSeries.bin"));
        oos.writeObject(listaDeListasSerie);
        oos.close();

        } catch (Exception e) {}

    }
    
    @Override
    public void recuperarListaSeriesArquivo(){
        try {
            
            File f = new File("ListaSeries.bin");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            listaDeListasSerie = (List<ListaSerie>) ois.readObject();
            ois.close();

        } 
        catch (Exception e) {}
        
    }
    
    /**
     * Deleta ListaSerie
     *
     */
    @Override
    public void deletarListaSerie(String nome, Usuario usuario) {
        
        for (int i=0; i< listaDeListasSerie.size(); i++) {
            if (listaDeListasSerie.get(i).getNome().equals(nome) && listaDeListasSerie.get(i).getUsuario().obterLogin().equals(usuario.obterLogin())){      
                listaDeListasSerie.remove(i);
                salvarListaSeriesArquivo();
            }
        }
    }
    
}
