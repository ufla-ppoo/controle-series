package br.ufla.dcc.ppoo.gui;

import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.imagens.GerenciadorDeImagens;
import br.ufla.dcc.ppoo.modelo.Usuario;
import br.ufla.dcc.ppoo.servicos.GerenciadorListaSeries;
import br.ufla.dcc.ppoo.servicos.GerenciadorUsuarios;
import br.ufla.dcc.ppoo.util.Utilidades;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Classe que representa a Tela de Pesquisa de Usuários.
 *
 * @author Breno
 */
public class TelaBuscarUsuarios {

    // referência para a tela principal
    private final TelaPrincipal telaPrincipal;
    // referência para o gerenciador de usuários
    private final GerenciadorUsuarios gerenciadorUsuarios;
    private final GerenciadorListaSeries gerenciadorListaSeries;
    // componentes da tela
    private JDialog janela;
    private GridBagLayout layout;
    private GridBagConstraints gbc;
    private JLabel lbBusca;
    private JTextField txtBusca;
    private JButton btnBuscar;
    private JButton btnCancelar;

    /**
     * Constrói a tela de autenticação guardando a referência da tela principal
     * e criando o gerenciador de usuários.
     *
     * @param telaPrincipal Referência da tela principal.
     */
    public TelaBuscarUsuarios(TelaPrincipal telaPrincipal) {
        this.gerenciadorUsuarios = new GerenciadorUsuarios();
        this.telaPrincipal = telaPrincipal;
        gerenciadorListaSeries = new GerenciadorListaSeries();
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

        txtBusca = new JTextField(25);
        adicionarComponente(txtBusca,
                GridBagConstraints.LINE_START,
                GridBagConstraints.NONE,
                0, 1, 1, 1);

        btnBuscar = new JButton(I18N.obterBotaoBuscar(),
                GerenciadorDeImagens.OK);

        btnCancelar = new JButton(I18N.obterBotaoCancelar(),
                GerenciadorDeImagens.CANCELAR);

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnBuscar);
        painelBotoes.add(btnCancelar);

        adicionarComponente(painelBotoes,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                2, 0, 2, 1);
    }

    /**
     * Configura os eventos da tela.
     */
    private void configurarEventosTela() {

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<Usuario> lista = new ArrayList<>();

                gerenciadorUsuarios.getListadeListaUsuarios().stream().forEach((s) -> {
                    if (s.obterNome().contains(txtBusca.getText())) {
                        lista.add(s);
                    }
                });

                if (lista.isEmpty()) {
                    new Utilidades().msgErro("Nenhum resultado encontrado!");

                } else {
                    Collections.sort(lista);
                    new TelaPerfisUsuarios(telaPrincipal, lista).inicializar();
                    janela.dispose();
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.dispose();
            }
        });
    }

    /**
     * Constrói a janela tratando internacionalização, componentes e layout.
     */
    private void construirTela() {
        janela = new JDialog();
        janela.setTitle(I18N.obterTituloTelaBuscarUsuarios());
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
