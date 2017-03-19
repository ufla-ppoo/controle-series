package br.ufla.dcc.ppoo.dao.lista;

import br.ufla.dcc.ppoo.dao.ListaSerieDAO;
import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.modelo.Comentario;
import br.ufla.dcc.ppoo.modelo.ListaSerie;
import br.ufla.dcc.ppoo.modelo.Usuario;
import br.ufla.dcc.ppoo.util.Utilidades;
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

    public ListaSerieDAOLista() {
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
    
    /**
     * Adiciona um objeto listaSerie na Lista de Series
     */
    @Override
    public void adicionarLista(ListaSerie listaSerie) {
        listaDeListasSerie.add(listaSerie);
        salvarListaSeriesArquivo();
    }

    /**
     * Retorna uma coleção de ListaSeries.
     */
    @Override
    public List<ListaSerie> getListaDeListaSerie(Usuario usuario) {
        List<ListaSerie> listaFinal = new ArrayList<>();

        for (ListaSerie listaSerie : listaDeListasSerie) {
            if (listaSerie.getUsuario().obterLogin().equals(usuario.obterLogin())) {
                listaFinal.add(listaSerie);
            }
        }

        return listaFinal;
    }

    /**
     * Retorna uma coleção de ListaSeries Públicas
     */
    @Override
    public List<ListaSerie> getListaDeListaSeriePublicas() {
        List<ListaSerie> listaFinal = new ArrayList<>();

        for (ListaSerie listaSerie : listaDeListasSerie) {
            if (!listaSerie.isVisivel()) {
                listaFinal.add(listaSerie);
            }
        }

        return listaFinal;
    }

    /**
     * Faz a persistência das Series no arquivo binário.
     */
    @Override
    public void salvarListaSeriesArquivo() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ListaSeries.bin"));
            oos.writeObject(listaDeListasSerie);
            oos.close();

        } catch (Exception e) {
        }

    }

    /**
     * Recuperar os dados persistidos do arquivo binário.
     */
    @Override
    public void recuperarListaSeriesArquivo() {
        try {

            File f = new File("ListaSeries.bin");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            listaDeListasSerie = (List<ListaSerie>) ois.readObject();
            ois.close();

        } catch (Exception e) {
        }

    }

    /**
     * Remove uma ListaSerie da lista de ListaSeries.
     */
    @Override
    public void deletarListaSerie(String nome, Usuario usuario) {

        for (int i = 0; i < listaDeListasSerie.size(); i++) {
            if (listaDeListasSerie.get(i).getNome().equals(nome) && listaDeListasSerie.get(i).getUsuario().obterLogin().equals(usuario.obterLogin())) {
                listaDeListasSerie.remove(i);
                salvarListaSeriesArquivo();
            }
        }
    }

    /**
     * Avalia uma ListaSerie.
     */
    @Override
    public void avaliarListaSerie(int nota, ListaSerie listaSerie, Usuario usuarioAvaliador) {

        for (int i = 0; i < listaDeListasSerie.size(); i++) {
            if (listaDeListasSerie.get(i).getNome().equals(listaSerie.getNome())) {
                listaDeListasSerie.get(i).setAvaliacao(nota, usuarioAvaliador);
                salvarListaSeriesArquivo();
            }
        }
    }

    /**
     * Adiciona um Comentario em uma ListaSerie.
     */
    @Override
    public void setComentario(Comentario Comentario, ListaSerie listaSerie) {

        for (int i = 0; i < listaDeListasSerie.size(); i++) {
            if (listaDeListasSerie.get(i).getNome().equals(listaSerie.getNome())) {
                listaDeListasSerie.get(i).setComentario(Comentario);
                salvarListaSeriesArquivo();
            }
        }
    }

    /**
     * Altera uma ListaSerie da lista de ListaSeries.
     */
    @Override
    public void alterarListaserie(ListaSerie listaSerie, ListaSerie novaLista, Usuario usuario) {
        for (int i = 0; i < listaDeListasSerie.size(); i++) {

            if (listaDeListasSerie.get(i).getNome().equals(listaSerie.getNome()) && listaSerie.getUsuario().obterLogin()
                    .equals(novaLista.getUsuario().obterLogin())) {
                listaDeListasSerie.get(i).setNome(novaLista.getNome());
                listaDeListasSerie.get(i).setPalavrasChave(novaLista.getPalavrasChave());
                listaDeListasSerie.get(i).setVisivel(novaLista.isVisivel());

                // limpa o conteúdo de séries da lista
                listaDeListasSerie.get(i).getSeries().clear();

                // adiciona o novo conteúdo de séries na lista
                for (int k = 0; k < novaLista.getSeries().size(); k++) {
                    listaDeListasSerie.get(i).getSeries().add(novaLista.getSeries().get(k));
                }

            }
        }
        salvarListaSeriesArquivo();
    }

    /**
     * Checa se os campos foram preenchidos corretamente.
     */
    @Override
    public boolean isCamposCorretos(ListaSerie listaSerie) {

        for (int i = 0; i < listaDeListasSerie.size(); i++) {

            if (listaDeListasSerie.get(i).getNome().equals(listaSerie.getNome())) {
                Utilidades.msgErro(I18N.obterErroNomesIguais());
                return false;
            }

            if (listaSerie.getPalavrasChave().isEmpty()) {
                Utilidades.msgErro(I18N.obterErroPalavrasChave());
                return false;
            }
        }

        if (listaSerie.getSeries().size() < 2) {
            Utilidades.msgErro(I18N.obterErroPoucasSeries());
            return false;
        }

        if (listaSerie.getNome().isEmpty()) {
            Utilidades.msgErro(I18N.obterErroDadosInvalidos());
            return false;
        }

        return true;
    }

    /**
     * Checa se os campos foram preenchidos corretamente na hora de editar uma ListaSerie.
     */
    @Override
    public boolean isCamposCorretosEditar(ListaSerie listaSerie) {

        for (int i = 0; i < listaDeListasSerie.size(); i++) {

            if (listaSerie.getPalavrasChave().isEmpty()) {
                Utilidades.msgErro(I18N.obterErroPalavrasChave());
                return false;
            }
        }

        if (listaSerie.getSeries().size() < 2) {
            Utilidades.msgErro(I18N.obterErroPoucasSeries());
            return false;
        }

        if (listaSerie.getNome().isEmpty()) {
            Utilidades.msgErro(I18N.obterErroDadosInvalidos());
            return false;
        }

        return true;
    }

}
