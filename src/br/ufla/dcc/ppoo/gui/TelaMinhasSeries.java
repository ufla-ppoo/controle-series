package br.ufla.dcc.ppoo.gui;

import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.imagens.GerenciadorDeImagens;
import br.ufla.dcc.ppoo.modelo.Serie;
import br.ufla.dcc.ppoo.servicos.GerenciadorSeries;
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
 * Classe que representa a tela Minhas Séries
 *
 * @author Paulo Jr. e Julio Alves
 */
public class TelaMinhasSeries {

    // referência para a tela principal
    private final TelaPrincipal telaPrincipal;

    // componentes da tela
    private JDialog janela;
    private GridBagLayout layout;
    private GridBagConstraints gbc;
    private JButton btnNovaSerie;
    private JButton btnEditarSerie;
    private JButton btnDeletarSerie;
    private JButton btnSalvarSerie;
    private JButton btnCancelar;
    private JTable tbSeries;
    private JLabel lbTitulo;
    private JLabel lbNumTemporadas;
    private JLabel lbAno;
    private JLabel lbGenero;
    private JLabel lbElenco;
    private JTextField txtTitulo;
    private JTextField txtNumTemporadas;
    private JTextField txtAno;
    private JTextField txtGenero;
    private JTextArea taElenco;
    private final GerenciadorSeries gerenciadorSeries;


    /**
     * Constrói a tela de autenticação guardando a referência da tela principal.
     *
     * @param telaPrincipal Referência da tela principal.
     */
    public TelaMinhasSeries(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
        this.gerenciadorSeries = new GerenciadorSeries();
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
            I18N.obterRotuloSerieTitulo(),
            I18N.obterRotuloSerieGenero()
        };

        
        
        
              
        Object[][] dados = new Object [gerenciadorSeries.getListaSerie().size()][2];
        
        for (int i=0; i< gerenciadorSeries.getListaSerie().size(); i++ ){
         dados [i][0] = gerenciadorSeries.getListaSerie().get(i).getTitulo();
         dados[i][1] = gerenciadorSeries.getListaSerie().get(i).getGenero();
        }
        
        
        /* Dados "fake"
        Object[][] dados = {
            {"The Big Bang Theory", "Sitcom"},
            {"Game of Thrones", "Aventura, Drama, Épico, Fantasia"}
        }; */

        tbSeries = new JTable(dados, titulosColunas);
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

        txtTitulo.setText("");
        txtNumTemporadas.setText("");
        txtAno.setText("");
        txtGenero.setText("");
        taElenco.setText("");

        txtTitulo.setEditable(false);
        txtNumTemporadas.setEditable(false);
        txtAno.setEditable(false);
        txtGenero.setEditable(false);
        taElenco.setEditable(false);

        btnNovaSerie.setEnabled(true);
        btnEditarSerie.setEnabled(false);
        btnSalvarSerie.setEnabled(false);
        btnDeletarSerie.setEnabled(false);
        btnCancelar.setEnabled(true);
    }

    /**
     * Trata o estado da tela para seleção de séries
     */
    private void prepararComponentesEstadoSelecaoSerie() {
        txtTitulo.setEditable(false);
        txtNumTemporadas.setEditable(false);
        txtAno.setEditable(false);
        txtGenero.setEditable(false);
        taElenco.setEditable(false);

        btnNovaSerie.setEnabled(true);
        btnEditarSerie.setEnabled(true);
        btnSalvarSerie.setEnabled(false);
        btnDeletarSerie.setEnabled(true);
        btnCancelar.setEnabled(true);
    }

    /**
     * Trata o estado da tela para cadastro de nova série
     */
    private void prepararComponentesEstadoNovaSerie() {
        tbSeries.clearSelection();
        tbSeries.setEnabled(true);

        txtTitulo.setText("");
        txtNumTemporadas.setText("");
        txtAno.setText("");
        txtGenero.setText("");
        taElenco.setText("");

        txtTitulo.setEditable(true);
        txtNumTemporadas.setEditable(true);
        txtAno.setEditable(true);
        txtGenero.setEditable(true);
        taElenco.setEditable(true);

        btnNovaSerie.setEnabled(false);
        btnEditarSerie.setEnabled(false);
        btnSalvarSerie.setEnabled(true);
        btnDeletarSerie.setEnabled(false);
        btnCancelar.setEnabled(true);
    }

    /**
     * Trata o estado da tela para cadastro série editada
     */
    private void prepararComponentesEstadoEditouSerie() {
        tbSeries.setEnabled(false);

        txtTitulo.setEditable(true);
        txtNumTemporadas.setEditable(true);
        txtAno.setEditable(true);
        txtGenero.setEditable(true);
        taElenco.setEditable(true);

        btnNovaSerie.setEnabled(false);
        btnEditarSerie.setEnabled(false);
        btnSalvarSerie.setEnabled(true);
        btnDeletarSerie.setEnabled(false);
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

        lbTitulo = new JLabel(I18N.obterRotuloSerieTitulo());
        adicionarComponente(lbTitulo,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                1, 0, 1, 1);

        txtTitulo = new JTextField(25);
        adicionarComponente(txtTitulo,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                1, 1, 3, 1);

        lbGenero = new JLabel(I18N.obterRotuloSerieGenero());
        adicionarComponente(lbGenero,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                2, 0, 1, 1);

        txtGenero = new JTextField(25);
        adicionarComponente(txtGenero,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                2, 1, 3, 1);

        lbAno = new JLabel(I18N.obterRotuloSerieAno());
        adicionarComponente(lbAno,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                3, 0, 1, 1);

        txtAno = new JTextField(8);
        adicionarComponente(txtAno,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                3, 1, 1, 1);

        lbNumTemporadas = new JLabel(I18N.obterRotuloSerieNumTemporadas());
        adicionarComponente(lbNumTemporadas,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                3, 2, 1, 1);

        txtNumTemporadas = new JTextField(8);
        adicionarComponente(txtNumTemporadas,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                3, 3, 1, 1);

        lbElenco = new JLabel(I18N.obterRotuloSerieElenco());
        adicionarComponente(lbElenco,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                4, 0, 1, 1);

        taElenco = new JTextArea(7, 25);
        JScrollPane scrollPaneDescricao = new JScrollPane(taElenco,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        adicionarComponente(scrollPaneDescricao,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                4, 1, 3, 1);

        btnNovaSerie = new JButton(I18N.obterBotaoNovo(),
                GerenciadorDeImagens.NOVO);

        btnEditarSerie = new JButton(I18N.obterBotaoEditar(),
                GerenciadorDeImagens.EDITAR);

        btnSalvarSerie = new JButton(I18N.obterBotaoSalvar(),
                GerenciadorDeImagens.OK);

        btnDeletarSerie = new JButton(I18N.obterBotaoDeletar(),
                GerenciadorDeImagens.DELETAR);

        btnCancelar = new JButton(I18N.obterBotaoCancelar(),
                GerenciadorDeImagens.CANCELAR);

        prepararComponentesEstadoInicial();

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnNovaSerie);
        painelBotoes.add(btnEditarSerie);
        painelBotoes.add(btnSalvarSerie);
        painelBotoes.add(btnDeletarSerie);
        painelBotoes.add(btnCancelar);

        adicionarComponente(painelBotoes,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                6, 0, 4, 1);
    }

    /**
     * Trata a selação de séries na grade.
     */
    private void selecionouSerie() {
        

        // Dados "fake"
        /*
        String texto = String.format("Linha selecionada: %d", tbSeries.getSelectedRow());
        txtTitulo.setText(texto);
        txtNumTemporadas.setText(texto);
        txtAno.setText(texto);
        txtGenero.setText(texto);
        taElenco.setText(texto);
        */
    }
    
        private Serie carregarSerie() {
        return new Serie(txtTitulo.getText(),
                txtGenero.getText(),
                txtAno.getText(),
                txtNumTemporadas.getText());
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

        tbSeries.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                prepararComponentesEstadoSelecaoSerie();
                selecionouSerie();
            }
        });

        btnEditarSerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepararComponentesEstadoEditouSerie();
            }
        });

        btnSalvarSerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepararComponentesEstadoInicial();
                gerenciadorSeries.cadastrarSerie(carregarSerie());
                System.out.println(gerenciadorSeries.getListaSerie().get(0).getTitulo());
            }
        });

        btnNovaSerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepararComponentesEstadoNovaSerie();
            }
        });

        btnDeletarSerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Utilidades.msgConfirmacao(I18N.obterConfirmacaoDeletar())) {
                    // Remover série!
                }
            }
        });
    }

    /**
     * Constrói a janela tratando internacionalização, componentes e layout.
     */
    private void construirTela() {
        janela = new JDialog();
        janela.setTitle(I18N.obterTituloTelaMinhasSeries());
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
