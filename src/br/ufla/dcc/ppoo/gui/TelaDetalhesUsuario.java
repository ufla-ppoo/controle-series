package br.ufla.dcc.ppoo.gui;

import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.imagens.GerenciadorDeImagens;
import br.ufla.dcc.ppoo.modelo.ListaSerie;
import br.ufla.dcc.ppoo.modelo.Usuario;
import br.ufla.dcc.ppoo.seguranca.SessaoUsuario;
import br.ufla.dcc.ppoo.servicos.GerenciadorListaSeries;
import br.ufla.dcc.ppoo.servicos.GerenciadorSeries;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * Classe que representa a Tela de detalhes de determinado Usuário.
 *
 * @author Breno
 */
public class TelaDetalhesUsuario {

    private final GerenciadorSeries gerenciadorSeries;
    private final GerenciadorListaSeries gerenciadorListaSeries;
    private final SessaoUsuario sessaoUsuario;
    private Usuario usuario;

    // referência para a tela principal
    private final TelaPrincipal telaPrincipal;

    // componentes da tela
    private JDialog janela;
    private GridBagLayout layout;
    private GridBagConstraints gbc;
    private JButton btnCancelar;
    private JButton btnDetalhes;
    private JTable tbSeries;
    private JLabel lbNomeUsuario;
    private JLabel lbPontuacaoUsuario;
    private JLabel lbEmail;
    private JTextField txtNomeUsuario;
    private JTextField txtPontuacaoUsuario;
    private JTextField txtEmail;

    /**
     * Constrói a tela de autenticação guardando a referência da tela principal.
     *
     * @param telaPrincipal Referência da tela principal.
     */
    public TelaDetalhesUsuario(TelaPrincipal telaPrincipal, Usuario usuario) {
        this.usuario = usuario;
        this.telaPrincipal = telaPrincipal;
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

        Object[] tituloColuna = {
            I18N.obterRotuloListaSerie()
        };

        List<String[]> lista = new ArrayList<>();

        for (int i = 0; i < gerenciadorListaSeries.getListadeListaSerie(usuario).size(); i++) {

            // Adiciona apenas as listas públicas
            if (!gerenciadorListaSeries.getListadeListaSerie(usuario).get(i).isVisivel()) {
                lista.add(new String[]{gerenciadorListaSeries.getListadeListaSerie(usuario).get(i).getNome()});
            }
        }

        // Modelo utilizado na Jtable de séries
        DefaultTableModel modelo = new DefaultTableModel(lista.toArray(new String[lista.size()][]), tituloColuna);

        tbSeries = new JTable();

        tbSeries.setModel(modelo);
        tbSeries.setPreferredScrollableViewportSize(new Dimension(400, 80));
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

        txtNomeUsuario.setEditable(false);
        txtPontuacaoUsuario.setEditable(false);
        txtEmail.setEditable(false);

        txtNomeUsuario.setText(usuario.obterNome());
        txtEmail.setText(usuario.getEmail());
        txtPontuacaoUsuario.setText(Integer.toString(usuario.getPontuacao()));
        btnCancelar.setEnabled(true);
        btnDetalhes.setEnabled(false);
    }

    /**
     * Trata o estado da tela para seleção de séries
     */
    private void prepararComponentesEstadoSelecaoListaSerie() {
        btnDetalhes.setEnabled(true);
    }

    /**
     * Adiciona os componentes da tela tratando layout e internacionalização
     */
    private void adicionarComponentes() {

        lbNomeUsuario = new JLabel(I18N.obterRotuloUsuarioNome());
        adicionarComponente(lbNomeUsuario,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                0, 0, 1, 1);

        txtNomeUsuario = new JTextField(25);
        adicionarComponente(txtNomeUsuario,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                0, 1, 3, 1);

        lbPontuacaoUsuario = new JLabel(I18N.obterRotuloUsuarioPontuacao());
        adicionarComponente(lbPontuacaoUsuario,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                1, 0, 1, 1);

        txtPontuacaoUsuario = new JTextField(25);
        adicionarComponente(txtPontuacaoUsuario,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                1, 1, 3, 1);

        lbEmail = new JLabel(I18N.obterRotuloUsuarioEmail());
        adicionarComponente(lbEmail,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                2, 0, 1, 1);

        txtEmail = new JTextField(25);
        adicionarComponente(txtEmail,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                2, 1, 3, 1);

        construirTabela();

        JScrollPane scrollPaneTabela = new JScrollPane(tbSeries);

        adicionarComponente(scrollPaneTabela,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                3, 0, 4, 1);

        btnDetalhes = new JButton(I18N.obterBotaoVisualizar(),
                GerenciadorDeImagens.EDITAR);

        btnCancelar = new JButton(I18N.obterBotaoCancelar(),
                GerenciadorDeImagens.CANCELAR);

        prepararComponentesEstadoInicial();

        JPanel painelBotoes = new JPanel();

        painelBotoes.add(btnDetalhes);
        painelBotoes.add(btnCancelar);

        adicionarComponente(painelBotoes,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                6, 0, 4, 1);
    }

    /**
     * Trata a selação de séries na grade.
     */
    private ListaSerie selecionouListaSerie() {

        List<ListaSerie> lista = new ArrayList<>(); // lista com todas as ListaSeries daquele usuario.     
        lista = gerenciadorListaSeries.getListadeListaSerie(usuario);

        int indice = tbSeries.getSelectedRow(); // indices da ListaSerie selecionada.

        return lista.get(indice);
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

        btnDetalhes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepararComponentesEstadoSelecaoListaSerie();
                new TelaDetalhesLista(telaPrincipal, selecionouListaSerie()).inicializar();
            }
        });

        tbSeries.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                prepararComponentesEstadoSelecaoListaSerie();
                selecionouListaSerie();
            }
        });

    }

    /**
     * Constrói a janela tratando internacionalização, componentes e layout.
     */
    private void construirTela() {
        janela = new JDialog();
        janela.setTitle(I18N.obterTituloTelaDetalhes());
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
