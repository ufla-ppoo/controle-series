package br.ufla.dcc.ppoo.gui;

import static br.ufla.dcc.ppoo.gui.TelaDetalhesLista.verificador;
import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.imagens.GerenciadorDeImagens;
import br.ufla.dcc.ppoo.modelo.Comentario;
import br.ufla.dcc.ppoo.modelo.ListaSerie;
import br.ufla.dcc.ppoo.modelo.Usuario;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.print.Collation;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Classe que representa a Tela de Autenticação (login no sistema)
 *
 * @author Breno
 */
public class TelaComentar {

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
    private JTextField txtComentario;
    private JButton btnEnviar;
    private JButton btnCancelar;
    private ListaSerie listaSerie;

    /**
     * Constrói a tela de autenticação guardando a referência da tela principal
     * e criando o gerenciador de usuários.
     * 
     * @param telaPrincipal Referência da tela principal.
     */
    public TelaComentar(TelaPrincipal telaPrincipal, ListaSerie listaSerie) {
        this.listaSerie = listaSerie;
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

        txtComentario = new JTextField(25);
        adicionarComponente(txtComentario,
                GridBagConstraints.LINE_START,
                GridBagConstraints.NONE,
                0, 1, 1, 1);


        btnEnviar = new JButton(I18N.obterBotaoEnviar(),
                GerenciadorDeImagens.OK);

        btnCancelar = new JButton(I18N.obterBotaoCancelar(),
                GerenciadorDeImagens.CANCELAR);

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnEnviar);
        painelBotoes.add(btnCancelar);

        adicionarComponente(painelBotoes,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                2, 0, 2, 1);
    }

    /**
     * Retorna um novo usuário a partir do login e senha passados.
     * 
     * @return Usuário criado.
     */
//    private Usuario carregarUsuario() {
//        return new Usuario(txtLogin.getText(), txtSenha.getPassword());
//    }

    /**
     * Configura os eventos da tela.
     */
    private void configurarEventosTela() {
        
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtComentario.getText().isEmpty()){
                    new Utilidades().msgErro("O campo não pode estar vazio!");
                } else { 
                    
                    if (txtComentario.getText().length() > 140){
                        new Utilidades().msgErro("Limite de caracteres atingido!");
                    
                    } else {
                    
                    Comentario comentario = new Comentario(txtComentario.getText(), SessaoUsuario.obterInstancia().obterUsuario());
                    gerenciadorListaSeries.setComentario(comentario, listaSerie);
                    janela.dispose();
                    }
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
        janela.setTitle(I18N.obterTituloTelaComentar());
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
