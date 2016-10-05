package br.ufla.dcc.ppoo.gui;

import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.imagens.GerenciadorDeImagens;
import br.ufla.dcc.ppoo.servicos.GerenciadorUsuarios;
import br.ufla.dcc.ppoo.util.Utilidades;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Classe que representa a tela Minhas Músicas
 *
 * @author Paulo Jr. e Julio Alves
 */
public class TelaMinhasMusicas {

    // referência para a tela principal
    private final TelaPrincipal telaPrincipal;
    // referência para o gerenciador de usuários
    private final GerenciadorUsuarios gerenciadorUsuarios;

    // componentes da tela
    private JDialog janela;
    private GridBagLayout layout;
    private GridBagConstraints gbc;
    private JButton btnNovaMusica;
    private JButton btnEditarMusica;
    private JButton btnDeletarMusica;
    private JButton btnSalvarMusica;
    private JButton btnCancelar;
    private JTable tbMusicas;
    private JLabel lbTitulo;
    private JLabel lbArtista;
    private JLabel lbAno;
    private JLabel lbGenero;
    private JLabel lbLetra;
    private JTextField txtTitulo;
    private JTextField txtArtista;
    private JTextField txtAno;
    private JTextField txtGenero;
    private JTextArea taLetra;

    /**
     * Constrói a tela de autenticação guardando a referência da tela principal
     * e criando o gerenciador de usuários.
     *
     * @param telaPrincipal Referência da tela principal.
     */
    public TelaMinhasMusicas(TelaPrincipal telaPrincipal) {
        this.gerenciadorUsuarios = new GerenciadorUsuarios();
        this.telaPrincipal = telaPrincipal;
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
            I18N.obterColunaTituloMusica(),
            I18N.obterColunaArtistaMusica()
        };

        // Dados "fake"
        Object[][] dados = {
            {"Like a Stone", "Audioslave"},
            {"Alive", "Pearl Jam"}
        };

        tbMusicas = new JTable(dados, titulosColunas);
        tbMusicas.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tbMusicas.setFillsViewportHeight(true);
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
        tbMusicas.clearSelection();
        tbMusicas.setEnabled(true);

        txtTitulo.setText("");
        txtArtista.setText("");
        txtAno.setText("");
        txtGenero.setText("");
        taLetra.setText("");

        txtTitulo.setEditable(false);
        txtArtista.setEditable(false);
        txtAno.setEditable(false);
        txtGenero.setEditable(false);
        taLetra.setEditable(false);

        btnNovaMusica.setEnabled(true);
        btnEditarMusica.setEnabled(false);
        btnSalvarMusica.setEnabled(false);
        btnDeletarMusica.setEnabled(false);
        btnCancelar.setEnabled(true);
    }

    /**
     * Trata o estado da tela para seleção de músicas
     */
    private void prepararComponentesEstadoSelecaoMusica() {
        txtTitulo.setEditable(false);
        txtArtista.setEditable(false);
        txtAno.setEditable(false);
        txtGenero.setEditable(false);
        taLetra.setEditable(false);

        btnNovaMusica.setEnabled(true);
        btnEditarMusica.setEnabled(true);
        btnSalvarMusica.setEnabled(false);
        btnDeletarMusica.setEnabled(true);
        btnCancelar.setEnabled(true);
    }

    /**
     * Trata o estado da tela para cadastro de nova música
     */
    private void prepararComponentesEstadoNovaMusica() {
        tbMusicas.clearSelection();
        tbMusicas.setEnabled(false);

        txtTitulo.setText("");
        txtArtista.setText("");
        txtAno.setText("");
        txtGenero.setText("");
        taLetra.setText("");

        txtTitulo.setEditable(true);
        txtArtista.setEditable(true);
        txtAno.setEditable(true);
        txtGenero.setEditable(true);
        taLetra.setEditable(true);

        btnNovaMusica.setEnabled(false);
        btnEditarMusica.setEnabled(false);
        btnSalvarMusica.setEnabled(true);
        btnDeletarMusica.setEnabled(false);
        btnCancelar.setEnabled(true);
    }

    /**
     * Trata o estado da tela para cadastro música editada
     */
    private void prepararComponentesEstadoEditouMusica() {
        tbMusicas.setEnabled(false);

        txtTitulo.setEditable(true);
        txtArtista.setEditable(true);
        txtAno.setEditable(true);
        txtGenero.setEditable(true);
        taLetra.setEditable(true);

        btnNovaMusica.setEnabled(false);
        btnEditarMusica.setEnabled(false);
        btnSalvarMusica.setEnabled(true);
        btnDeletarMusica.setEnabled(false);
        btnCancelar.setEnabled(true);
    }

    /**
     * Adiciona os componentes da tela tratando layout e internacionalização
     */
    private void adicionarComponentes() {
        construirTabela();
        JScrollPane scrollPaneTabela = new JScrollPane(tbMusicas);
        adicionarComponente(scrollPaneTabela,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                0, 0, 4, 1);

        lbTitulo = new JLabel(I18N.obterRotuloMusicaTitulo());
        adicionarComponente(lbTitulo,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                1, 0, 1, 1);

        txtTitulo = new JTextField(25);
        adicionarComponente(txtTitulo,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                1, 1, 3, 1);

        lbArtista = new JLabel(I18N.obterRotuloMusicaArtista());
        adicionarComponente(lbArtista,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                2, 0, 1, 1);

        txtArtista = new JTextField(25);
        adicionarComponente(txtArtista,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                2, 1, 3, 1);

        lbAno = new JLabel(I18N.obterRotuloMusicaAno());
        adicionarComponente(lbAno,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                3, 0, 1, 1);

        txtAno = new JTextField(8);
        adicionarComponente(txtAno,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                3, 1, 1, 1);

        lbGenero = new JLabel(I18N.obterRotuloMusicaGenero());
        adicionarComponente(lbGenero,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                3, 2, 1, 1);

        txtGenero = new JTextField(8);
        adicionarComponente(txtGenero,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                3, 3, 1, 1);

        lbLetra = new JLabel(I18N.obterRotuloMusicaLetra());
        adicionarComponente(lbLetra,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                4, 0, 1, 1);

        taLetra = new JTextArea(7, 25);
        JScrollPane scrollPaneDescricao = new JScrollPane(taLetra,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        adicionarComponente(scrollPaneDescricao,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                4, 1, 3, 1);

        btnNovaMusica = new JButton(I18N.obterBotaoNovo(),
                GerenciadorDeImagens.NOVO);

        btnEditarMusica = new JButton(I18N.obterBotaoEditar(),
                GerenciadorDeImagens.EDITAR);

        btnSalvarMusica = new JButton(I18N.obterBotaoSalvar(),
                GerenciadorDeImagens.OK);

        btnDeletarMusica = new JButton(I18N.obterBotaoDeletar(),
                GerenciadorDeImagens.DELETAR);

        btnCancelar = new JButton(I18N.obterBotaoCancelar(),
                GerenciadorDeImagens.CANCELAR);

        prepararComponentesEstadoInicial();

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnNovaMusica);
        painelBotoes.add(btnEditarMusica);
        painelBotoes.add(btnSalvarMusica);
        painelBotoes.add(btnDeletarMusica);
        painelBotoes.add(btnCancelar);

        adicionarComponente(painelBotoes,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                6, 0, 4, 1);
    }

    /**
     * Trata a selação de músicas na grade.
     */
    private void selecionouMusica() {
        // Dados "fake"
        String texto = String.format("Linha selecionada: %d", tbMusicas.getSelectedRow());
        txtTitulo.setText(texto);
        txtArtista.setText(texto);
        txtAno.setText(texto);
        txtGenero.setText(texto);
        taLetra.setText(texto);
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

        tbMusicas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                prepararComponentesEstadoSelecaoMusica();
                selecionouMusica();
            }
        });

        btnEditarMusica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepararComponentesEstadoEditouMusica();
            }
        });

        btnSalvarMusica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepararComponentesEstadoInicial();
            }
        });

        btnNovaMusica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepararComponentesEstadoNovaMusica();
            }
        });

        btnDeletarMusica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Utilidades.msgConfirmacao(I18N.obterConfirmacaoDeletar())) {
                    // Remover música!
                }
            }
        });
    }

    /**
     * Constrói a janela tratando internacionalização, componentes e layout.
     */
    private void construirTela() {
        janela = new JDialog();
        janela.setTitle(I18N.obterTituloTelaMinhasMusicas());
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
