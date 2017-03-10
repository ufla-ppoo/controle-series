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
import javax.swing.table.DefaultTableModel;

/**
 * Classe que representa a tela Minhas Séries
 *
 * @author Breno
 */
public class TelaMinhasListas {
    private final TelaCadastroLista telaCadastroLista;
    private final GerenciadorSeries gerenciadorSeries;
    private final GerenciadorListaSeries gerenciadorListaSeries;
    private final SessaoUsuario sessaoUsuario;
    private String titulo;

    // referência para a tela principal
    private final TelaPrincipal telaPrincipal;
    private final TelaDetalhesLista telaDetalhesLista;

    // componentes da tela
    private JDialog janela;
    private GridBagLayout layout;
    private GridBagConstraints gbc;
    private JButton btnNovaLista;
    private JButton btnEditarLista;
    private JButton btnDeletarSLista;
    private JButton btnDetalhesLista;
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

    

    /**
     * Constrói a tela de autenticação guardando a referência da tela principal.
     *
     * @param telaPrincipal Referência da tela principal.
     */
    public TelaMinhasListas(TelaPrincipal telaPrincipal) {
        telaCadastroLista = new TelaCadastroLista(telaPrincipal);
        this.telaPrincipal = telaPrincipal;
        telaDetalhesLista = new TelaDetalhesLista(telaPrincipal);
        sessaoUsuario = SessaoUsuario.obterInstancia();
        gerenciadorSeries = new GerenciadorSeries();
        gerenciadorListaSeries = new GerenciadorListaSeries();
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
            I18N.obterRotuloListaTitulo(),
            I18N.obterRotuloListaAutor()
        };
        

        List<String[]> lista = new ArrayList<>();

        // Add o titulo e o genero na lista criada utilizando expressão lambda do java 8
        gerenciadorListaSeries.getListadeListaSerie(sessaoUsuario.obterUsuario()).stream().forEach((s) -> {
            lista.add(new String[]{s.getNome(),s.getUsuario().obterNome()});
        });  
        
        // Modelo utilizado na Jtable de séries
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

        btnNovaLista.setEnabled(true);
        btnEditarLista.setEnabled(false);
        btnDeletarSLista.setEnabled(false);
        btnDetalhesLista.setEnabled(false);
        btnCancelar.setEnabled(true);
    }

    /**
     * Trata o estado da tela para seleção de séries
     */
    private void prepararComponentesEstadoSelecaoSerie() {

        btnNovaLista.setEnabled(true);
        btnEditarLista.setEnabled(true);
        btnDeletarSLista.setEnabled(true);
        btnDetalhesLista.setEnabled(true);
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

        btnNovaLista = new JButton(I18N.obterBotaoNovo(),
                GerenciadorDeImagens.NOVO);

        btnEditarLista = new JButton(I18N.obterBotaoEditar(),
                GerenciadorDeImagens.EDITAR);

        btnDeletarSLista = new JButton(I18N.obterBotaoDeletar(),
                GerenciadorDeImagens.DELETAR);

        btnCancelar = new JButton(I18N.obterBotaoCancelar(),
                GerenciadorDeImagens.CANCELAR);

        btnDetalhesLista = new JButton(I18N.obterBotaoVisualizar(),
                GerenciadorDeImagens.SOBRE);
        
        prepararComponentesEstadoInicial();

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnNovaLista);
        painelBotoes.add(btnDetalhesLista);
        //painelBotoes.add(btnEditarLista);
        painelBotoes.add(btnDeletarSLista);
        painelBotoes.add(btnCancelar);

        adicionarComponente(painelBotoes,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                6, 0, 4, 1);
    }

    /**
     * Trata a selação de séries na grade.
     */
    private ListaSerie selecionouSerie() {
        
        int posicao = tbSeries.getSelectedRow();
        
        List<ListaSerie> listaDeLista = gerenciadorListaSeries.getListadeListaSerie(sessaoUsuario.obterUsuario());
        ListaSerie listaSerie = listaDeLista.get(posicao);
        
        return listaSerie;       
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
        
        btnDeletarSLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Utilidades.msgConfirmacao(I18N.obterConfirmacaoDeletar())) {
                    gerenciadorListaSeries.deletarListaSerie(selecionouSerie().getNome());
                    Utilidades.msgInformacao(I18N.obterSucessoDeletarListaSerie());
                    atualiza();
                }
                
            }
        });
        
        btnDetalhesLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListaSerie listaParaMostrar = selecionouSerie();
                telaDetalhesLista.setListaSerieParaMostrar(listaParaMostrar); // Envia a listaSerie para ser exibida
                telaDetalhesLista.inicializar();
            }
        });

        tbSeries.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                prepararComponentesEstadoSelecaoSerie();
                selecionouSerie();
            }
        });
        
        btnNovaLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaCadastroLista.inicializar();
                atualiza();
            }
        });

//        btnEditarLista.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                confereSerie = false;
//                titulo = txtTitulo.getText();
//                prepararComponentesEstadoEditouSerie();
//            }
//        });
//
//
//        btnDeletarSLista.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (Utilidades.msgConfirmacao(I18N.obterConfirmacaoDeletar())) {
//                    gerenciadorSeries.deletarSerie(txtTitulo.getText());
//                    Utilidades.msgInformacao(I18N.obterSucessoDeletarSerie());
//                    atualiza();
//                }
//            }
//        });
    }

    /**
     * Constrói a janela tratando internacionalização, componentes e layout.
     */
    private void construirTela() {
        janela = new JDialog();
        janela.setTitle(I18N.obterTituloTelaMinhasListas());
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
        
    public void atualiza(){
        construirTabela();
        prepararComponentesEstadoInicial();      
        janela.dispose();
        inicializar();
    }    
}
