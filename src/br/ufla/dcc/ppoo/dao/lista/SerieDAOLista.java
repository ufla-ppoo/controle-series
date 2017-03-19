package br.ufla.dcc.ppoo.dao.lista;

import br.ufla.dcc.ppoo.dao.SerieDAO;
import br.ufla.dcc.ppoo.modelo.Serie;
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
 * Implementação do Data Access Object (Padrão de Projeto) da Serie através de
 * Lista em memória
 *
 * @author Breno
 */
public class SerieDAOLista implements SerieDAO, Serializable {

    // instância única da classe (Padrão de Projeto Singleton)
    private static SerieDAOLista instancia;

    // lista em em memória das séries cadastrados
    private List<Serie> listaSerie;

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
     * Adiciona um objeto Serie na Lista de Series
     */
    @Override
    public List<Serie> getListaSeries(Usuario usuario) {
        List<Serie> listaDoUsuario = new ArrayList<>();

        for (Serie serie : listaSerie) {
            if (serie.getUsuario().obterLogin().equals(usuario.obterLogin())) {
                listaDoUsuario.add(serie);
            }
        }

        return listaDoUsuario;
    }

    /**
     * Retonar um ArrayList contendo objetos da classe Serie
     */
    @Override
    public void adicionarSerie(Serie serie) {
        listaSerie.add(serie);
        salvarSeriesArquivo();
    }

    /**
     * Edita uma série
     *
     */
    public void editarSerie(Serie serie, String tituloSerie, Usuario usuario) {

        for (int i = 0; i < listaSerie.size(); i++) {
            if ((listaSerie.get(i).getTitulo().equals(tituloSerie))
                    && (listaSerie.get(i).getUsuario().obterLogin().equals(usuario.obterLogin()))) {

                listaSerie.get(i).setAnoLancamento(serie.getAnoLancamento());
                listaSerie.get(i).setElenco(serie.getElenco());
                listaSerie.get(i).setGenero(serie.getGenero());
                listaSerie.get(i).setNumeroDeTemporadas(serie.getNumeroDeTemporadas());
                listaSerie.get(i).setTitulo(serie.getTitulo());
                salvarSeriesArquivo();
            }
        }
    }

    /**
     * Deleta série
     *
     */
    @Override
    public void deletarSerie(String nome, Usuario usuario) {

        for (int i = 0; i < listaSerie.size(); i++) { // modifiquei o ForEach para um For normal pois estava dando problema
            if (listaSerie.get(i).getTitulo().equals(nome) && listaSerie.get(i).getUsuario().obterLogin().equals(usuario.obterLogin())) {
                listaSerie.remove(i);
                salvarSeriesArquivo();
            }
        }
    }

    /**
     * Faz a persistencia das Series em um arquivo binário.
     */
    @Override
    public void salvarSeriesArquivo() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("series.bin"));
            oos.writeObject(listaSerie);
            oos.close();

        } catch (Exception e) {
        }

    }

    /**
     * Recupera os dados persistidos no arquivo binário.
     */
    @Override
    public void recuperarSeriesArquivo() {
        try {

            File f = new File("series.bin");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            listaSerie = (List<Serie>) ois.readObject();
            ois.close();

        } catch (Exception e) {
        }

    }
}
