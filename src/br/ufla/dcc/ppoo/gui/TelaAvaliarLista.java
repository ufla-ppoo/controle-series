package br.ufla.dcc.ppoo.gui;

import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.imagens.GerenciadorDeImagens;
import br.ufla.dcc.ppoo.modelo.ListaSerie;
import br.ufla.dcc.ppoo.seguranca.SessaoUsuario;
import br.ufla.dcc.ppoo.servicos.GerenciadorListaSeries;
import br.ufla.dcc.ppoo.servicos.GerenciadorUsuarios;
import br.ufla.dcc.ppoo.util.Utilidades;
import java.awt.Component;
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
import javax.swing.JSlider;
import javax.swing.SwingConstants;

/**
 * Classe que representa a Tela de Avaliar determinada Lista
 *
 * @author Breno
 */
public class TelaAvaliarLista {

    // referência para a tela principal
    private final TelaPrincipal telaPrincipal;
    // referência para o gerenciador de usuários
    private final GerenciadorUsuarios gerenciadorUsuarios;
    private final GerenciadorListaSeries gerenciadorListaSeries;
    private final SessaoUsuario sessaoUsuario;
    // componentes da tela
    private JDialog janela;
    private GridBagLayout layout;
    private GridBagConstraints gbc;
    private JButton btnAvaliar;
    private JButton btnCancelar;
    private JLabel lbCH;
    private JLabel lbSlider;
    private JSlider slider;

    private ListaSerie listaSerie;

    /**
     * Constrói a tela de autenticação guardando a referência da tela principal
     * e criando o gerenciador de usuários.
     *
     * @param telaPrincipal Referência da tela principal.
     */
    public TelaAvaliarLista(TelaPrincipal telaPrincipal, ListaSerie listaSerie) {
        this.gerenciadorUsuarios = new GerenciadorUsuarios();
        this.listaSerie = listaSerie;
        this.telaPrincipal = telaPrincipal;
        gerenciadorListaSeries = new GerenciadorListaSeries();
        sessaoUsuario = SessaoUsuario.obterInstancia();
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
     * Adiciona os componentes da tela tratando layout e internacionalização
     */
    private void adicionarComponentes() {

        lbCH = new JLabel(I18N.obterRotuloAvaliar());
        adicionarComponente(lbCH,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                0, 0, 1, 1);

        slider = new JSlider(SwingConstants.HORIZONTAL, 1, 5, 3);
        slider.setMajorTickSpacing(1);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);

        lbSlider = new JLabel(I18N.obterRotuloAvaliar());
        adicionarComponente(slider,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                2, 0, 2, 1);

        btnAvaliar = new JButton(I18N.obterBotaoAvaliar(),
                GerenciadorDeImagens.OK);

        btnCancelar = new JButton(I18N.obterBotaoCancelar(),
                GerenciadorDeImagens.CANCELAR);

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnAvaliar);
        painelBotoes.add(btnCancelar);

        adicionarComponente(painelBotoes,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                3, 0, 3, 1);
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

        btnAvaliar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                gerenciadorListaSeries.avaliarListaSerie(slider.getValue(), listaSerie, sessaoUsuario.obterUsuario());
                Utilidades.msgInformacao(I18N.obterSucessoListaAvaliada());
                janela.dispose();

            }

        });
    }

    /**
     * Constrói a janela tratando internacionalização, componentes e layout.
     */
    private void construirTela() {
        janela = new JDialog();
        janela.setTitle(I18N.obterTituloTelaAvaliarListas());
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
