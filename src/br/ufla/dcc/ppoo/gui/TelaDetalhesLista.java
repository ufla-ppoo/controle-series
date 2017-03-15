package br.ufla.dcc.ppoo.gui;

import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.imagens.GerenciadorDeImagens;
import br.ufla.dcc.ppoo.modelo.ListaSerie;
import br.ufla.dcc.ppoo.modelo.Serie;
import br.ufla.dcc.ppoo.seguranca.SessaoUsuario;
import br.ufla.dcc.ppoo.servicos.GerenciadorListaSeries;
import br.ufla.dcc.ppoo.servicos.GerenciadorSeries;
import br.ufla.dcc.ppoo.servicos.GerenciadorUsuarios;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Breno
 */
public class TelaDetalhesLista {
    
    private final GerenciadorSeries gerenciadorSeries;
    private final GerenciadorListaSeries gerenciadorListaSeries; 
    private final SessaoUsuario sessaoUsuario;
    private String titulo;
    private ListaSerie listaSerie;
    public static boolean verificador = false;

    // referência para a tela principal
    private final TelaPrincipal telaPrincipal;

    // componentes da tela
    private JDialog janela;
    private GridBagLayout layout;
    private GridBagConstraints gbc;
    private JButton btnCancelar;
    private JButton btnAvaliar;
    private JTable tbSeries;
    private JLabel lbNomeLista;
    private JLabel lbNomeAutor;
    private JLabel lbTag;
    private JTextField txtNomeLista;
    private JTextField txtNomeAutor;
    private JTextField txtTags;

    

    /**
     * Constrói a tela de autenticação guardando a referência da tela principal.
     *
     * @param telaPrincipal Referência da tela principal.
     */
    public TelaDetalhesLista(TelaPrincipal telaPrincipal, ListaSerie listaSerie) {
        this.listaSerie = listaSerie;
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
        
        Object[] titulosColunas = {
            I18N.obterRotuloSerie(),
            I18N.obterRotuloSerieNumTemporadas()
        };
        
        List<String[]> lista = new ArrayList<>();
        
        for (int i=0; i < listaSerie.getSeries().size(); i++) {
            lista.add(new String[] {listaSerie.getSeries().get(i).getTitulo(), listaSerie.getSeries().get(i).getNumeroDeTemporadas()});
        }
        
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
        tbSeries.setEnabled(false);
        txtNomeLista.setEditable(false);
        txtNomeAutor.setEditable(false);
        txtTags.setEditable(false);
        
        txtNomeLista.setText(listaSerie.getNome() + " (" + listaSerie.getPontos() + ")");
        txtTags.setText(listaSerie.getPalavrasChave());
        txtNomeAutor.setText(listaSerie.getUsuario().obterNome());
        btnCancelar.setEnabled(true);
        btnAvaliar.setEnabled(true);
    }

    /**
     * Trata o estado da tela para seleção de séries
     */
    private void prepararComponentesEstadoSelecaoSerie() {
        btnCancelar.setEnabled(true);
        btnAvaliar.setEnabled(true);
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
        
        lbNomeAutor = new JLabel(I18N.obterRotuloListaAutor());
        adicionarComponente(lbNomeAutor,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                2, 0, 1, 1);
        
        txtNomeAutor = new JTextField(25);
        adicionarComponente(txtNomeAutor,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                2, 1, 3, 1);
        
        lbTag = new JLabel(I18N.obterRotuloListaTags());
        adicionarComponente(lbTag,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                3, 0, 1, 1);
        
        txtTags = new JTextField(25);
        adicionarComponente(txtTags,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                3, 1, 3, 1);        

        btnAvaliar = new JButton(I18N.obterBotaoAvaliar(),
        GerenciadorDeImagens.EDITAR);
        
        btnCancelar = new JButton(I18N.obterBotaoCancelar(),
                GerenciadorDeImagens.CANCELAR);

        prepararComponentesEstadoInicial();

        JPanel painelBotoes = new JPanel();
        
        if (sessaoUsuario.estahLogado() && !(listaSerie.getUsuario().obterLogin().equals(sessaoUsuario.obterUsuario().obterLogin()))){
            painelBotoes.add(btnAvaliar);
        }
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
          
        List<Serie> lista = new ArrayList<>(); // lista com todas as series daquele usuario       
        lista = gerenciadorSeries.getListaSerie(sessaoUsuario.obterUsuario());
        
        List<Serie> listaFinal= new ArrayList<>(); // lista final com series selecionadas       
       
        int[] indices = tbSeries.getSelectedRows(); // array com indices das series selecionadas na lista
        
        for(int i=0; i < indices.length; i++){ // for com tamanho de linhas selecionadas
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
                verificador = false;
                janela.dispose();
            }
        });
        
        btnAvaliar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!listaSerie.usuarioJaAvaliou(sessaoUsuario.obterUsuario())){
                    
                    new TelaAvaliarLista(telaPrincipal, listaSerie).inicializar();
                        
                        if (verificador == true){
                            janela.dispose();
                        }
                } else {
                    
                Utilidades.msgErro(I18N.obterErroListaJaAvaliada());
                
                } 
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
        
    public void atualiza(){
        construirTabela();
        prepararComponentesEstadoInicial();      
        janela.dispose();
        inicializar();
    }

}
