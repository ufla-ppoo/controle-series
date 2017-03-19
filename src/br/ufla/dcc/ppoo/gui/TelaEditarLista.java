package br.ufla.dcc.ppoo.gui;

import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.imagens.GerenciadorDeImagens;
import br.ufla.dcc.ppoo.modelo.ListaSerie;
import br.ufla.dcc.ppoo.modelo.Serie;
import br.ufla.dcc.ppoo.seguranca.SessaoUsuario;
import br.ufla.dcc.ppoo.servicos.GerenciadorListaSeries;
import br.ufla.dcc.ppoo.servicos.GerenciadorSeries;
import br.ufla.dcc.ppoo.util.Utilidades;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Classe que representa a tela de Edição de Lista de Serie.
 *
 * @author Breno
 */
public class TelaEditarLista {

    private final GerenciadorSeries gerenciadorSeries;
    private final GerenciadorListaSeries gerenciadorListaSeries;
    private final SessaoUsuario sessaoUsuario;
    private ListaSerie listaSerie;

    // referência para a tela principal
    private final TelaPrincipal telaPrincipal;

    // componentes da tela
    private JDialog janela;
    private GridBagLayout layout;
    private GridBagConstraints gbc;
    private JButton btnEditarLista;
    private JButton btnCancelar;
    private JTable tbSeries;
    private JLabel lbNomeLista;
    private JLabel lbTag;
    private JTextField txtNomeLista;
    private JTextField txtTags;
    private JCheckBox checkBox;

    /**
     * Constrói a tela de autenticação guardando a referência da tela principal.
     *
     * @param telaPrincipal Referência da tela principal.
     */
    public TelaEditarLista(TelaPrincipal telaPrincipal, ListaSerie listaSerie) {
        this.telaPrincipal = telaPrincipal;
        this.listaSerie = listaSerie;
        sessaoUsuario = SessaoUsuario.obterInstancia();
        gerenciadorSeries = new GerenciadorSeries();
        gerenciadorListaSeries = new GerenciadorListaSeries();
        gerenciadorSeries.RecuperarSeriesArquivo();
        gerenciadorListaSeries.recuperarListaSeriesArquivo();
    }

    /**
     * Inicializa a tela, construindo seus componentes, configurando os eventos
     * e, ao final, exibe a tela.
     */
    public void inicializar() {
        construirTela();
        configurarEventosTela();
        exibirTela();

    }

    /**
     * Constrói a janela tratando internacionalização, componentes e layout.
     */
    private void construirTabela() {

        Object[] titulosColunas = {
            I18N.obterRotuloSerie()
        };

        List<String[]> lista = new ArrayList<>();

        // Add o titulo e o genero na lista criada utilizando expressão lambda do java 8
        listaSerie.getSeries().stream().forEach((s) -> {
            lista.add(new String[]{s.getTitulo()});
        });

        DefaultTableModel modelo = new DefaultTableModel(lista.toArray(new String[lista.size()][]), titulosColunas);

        tbSeries = new JTable();
        tbSeries.setModel(modelo);
        tbSeries.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tbSeries.setFillsViewportHeight(true);
    }

    /**
     * Adiciona um componente à tela.
     */
    private void adicionarComponente(Component c,
            int anchor, int fill, int linha,
            int coluna, int largura, int altura) {
        gbc.anchor = anchor;
        gbc.fill = fill;
        gbc.gridy = linha;
        gbc.gridx = coluna;
        gbc.gridwidth = largura;
        gbc.gridheight = altura;
        gbc.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(c, gbc);
        janela.add(c);
    }

    /**
     * Trata o estado inicial da tela
     */
    private void prepararComponentesEstadoInicial() {
        tbSeries.clearSelection();
        tbSeries.setEnabled(true);
        tbSeries.setDefaultEditor(Object.class, null);
        tbSeries.selectAll();

        txtNomeLista.setText(listaSerie.getNome());
        txtTags.setText(listaSerie.getPalavrasChave());
        checkBox.setEnabled(true);
        checkBox.setSelected(true);

        btnCancelar.setEnabled(true);
    }

    /**
     * Trata o estado da tela para seleção de séries
     */
    private void prepararComponentesEstadoSelecaoSerie() {
        btnEditarLista.setEnabled(true);
        btnCancelar.setEnabled(true);
    }

    /**
     * Adiciona os componentes da tela tratando layout e internacionalização
     */
    private void adicionarComponentes() {

        construirTabela();

        JScrollPane scrollPaneTabela = new JScrollPane(tbSeries);

        adicionarComponente(scrollPaneTabela,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                0, 0, 4, 1);

        lbNomeLista = new JLabel(I18N.obterRotuloListaTitulo());
        adicionarComponente(lbNomeLista,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                1, 0, 1, 1);

        txtNomeLista = new JTextField(25);
        adicionarComponente(txtNomeLista,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                1, 1, 3, 1);

        lbTag = new JLabel(I18N.obterRotuloListaTags());
        adicionarComponente(lbTag,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                2, 0, 1, 1);

        txtTags = new JTextField(25);
        adicionarComponente(txtTags,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                2, 1, 3, 1);

        checkBox = new JCheckBox(I18N.obterRotuloListaPrivado());
        adicionarComponente(checkBox,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                3, 3, 1, 1);

        btnEditarLista = new JButton(I18N.obterBotaoEditar(),
                GerenciadorDeImagens.OK);

        btnCancelar = new JButton(I18N.obterBotaoCancelar(),
                GerenciadorDeImagens.CANCELAR);

        prepararComponentesEstadoInicial();

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnEditarLista);
        painelBotoes.add(btnCancelar);

        adicionarComponente(painelBotoes,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                6, 0, 4, 1);
    }

    /**
     * Trata a selação de séries na grade.
     */
    private List selecionouSerie() {

        List<Serie> lista = new ArrayList<>();
        lista = listaSerie.getSeries();

        List<Serie> listaFinal = new ArrayList<>(); // lista final com series selecionadas       

        int[] indices = tbSeries.getSelectedRows(); // array com indices das series selecionadas na lista

        for (int i = 0; i < indices.length; i++) {
            listaFinal.add(lista.get(indices[i]));
        }

        return listaFinal;
    }

    /**
     * Configura os eventos da tela.
     */
    private void configurarEventosTela() {
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.dispose();
            }
        });

        btnEditarLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<Serie> seriesSelecionadas = selecionouSerie();

                ListaSerie novaListaSerie = new ListaSerie(txtNomeLista.getText(), checkBox.isSelected(), txtTags.getText(), sessaoUsuario.obterUsuario(), seriesSelecionadas);

                if (gerenciadorListaSeries.isCamposCorretosEditar(novaListaSerie)) {

                    gerenciadorListaSeries.editarListaSerie(listaSerie, novaListaSerie, listaSerie.getUsuario());
                    Utilidades.msgInformacao(I18N.obterSucessoAlterarSerie());

                    janela.dispose();
                }

            }
        });
    }

    /**
     * Constrói a janela tratando internacionalização, componentes e layout.
     */
    private void construirTela() {
        janela = new JDialog();
        janela.setTitle(I18N.obterTituloTelaEditarLista());
        layout = new GridBagLayout();
        gbc = new GridBagConstraints();
        janela.setLayout(layout);
        adicionarComponentes();
        janela.pack();
    }

    /**
     * Exibe a tela.
     */
    private void exibirTela() {
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setLocationRelativeTo(telaPrincipal.obterJanela());
        janela.setModal(true);
        janela.setVisible(true);
        janela.setResizable(false);
    }
}
