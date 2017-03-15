package br.ufla.dcc.ppoo.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Classe que trata a internacionalização do sistema (idiomas)
 *
 * @author Paulo Jr. e Julio Alves
 */
public class I18N {

    // caminho base para os arquivos de internacionalização
    private static final String CAMINHO_ARQUIVOBASE_I18N = "br/ufla/dcc/ppoo/i18n/Strings";
    // objeto utilizado para carregar os textos do sistema de acordo com a localidade
    private static ResourceBundle rb = ResourceBundle.getBundle(CAMINHO_ARQUIVOBASE_I18N, Locale.getDefault());
    // indica a localidade (idioma) Português - Brasil
    public static final Locale PT_BR = new Locale("pt", "BR");
    // indica a localidade (idioma) Inglês - Americano
    public static final Locale EN_US = new Locale("en", "US");

    /**
     * Altera a localidade a ser utilizada.
     *
     * @param localidade Nova localidade a ser utilizada (Português - Brasil é a
     * padrão)
     */
    public static void alterarLocalidade(Locale localidade) {
        Locale.setDefault(localidade);
        rb = ResourceBundle.getBundle(CAMINHO_ARQUIVOBASE_I18N, localidade);
    }

    /**
     * Retorna o nome do sistema.
     *
     * @return Nome do sistema.
     */
    public static String obterNomeDoSistema() {
        return rb.getString("sistema.nome");
    }

    /**
     * Retorna o texto do menu Início.
     *
     * @return Texto do menu Início.
     */
    public static String obterMenuInicio() {
        return rb.getString("menu.inicio");
    }
    
    /**
     * Retorna o texto do menu Listas.
     *
     * @return Texto do menu Listas.
     */
    public static String obterMenuListas() {
        return rb.getString("menu.listas");
    }

    /**
     * Retorna o mnemônico do menu Início.
     *
     * @return Mnemônico do menu Início.
     */
    public static char obterMnemonicoMenuInicio() {
        return rb.getString("mnemonico.menu.inicio").charAt(0);
    }

    /**
     * Retorna o texto do menu Entrar.
     *
     * @return Texto do menu Entrar.
     */
    public static String obterMenuEntrar() {
        return rb.getString("menu.inicio.entrar");
    }

    /**
     * Retorna o texto do menu Minhas Séries.
     *
     * @return Texto do menu Minhas Séries.
     */
    public static String obterMenuMinhasSeries() {
        return rb.getString("menu.inicio.minhas_series");
    }
    
     /**
     * Retorna o texto do menu Minhas Listas.
     *
     * @return Texto do menu Minhas Listas.
     */
    public static String obterMenuMinhasListas() {
        return rb.getString("menu.inicio.minhas_listas");
    }
    
    public static String obterMenuPesquisarListas(){
        return rb.getString("menu.listas.pesquisar_listas");
    }

    /**
     * Retorna o texto do menu Cadastrar Usuário.
     *
     * @return Texto do menu Cadastrar Usuário.
     */
    public static String obterMenuCadastrarUsuario() {
        return rb.getString("menu.inicio.cadastrar");
    }

    /**
     * Retorna o texto do menu Sair.
     *
     * @return Texto do menu Sair.
     */
    public static String obterMenuSair() {
        return rb.getString("menu.inicio.sair");
    }

    /**
     * Retorna o texto do menu Logout.
     *
     * @return Texto do menu Logout.
     */
    public static String obterMenuLogout() {
        return rb.getString("menu.inicio.logout");
    }

    /**
     * Retorna o texto do menu Idioma.
     *
     * @return Texto do menu Idioma.
     */
    public static String obterMenuIdioma() {
        return rb.getString("menu.idioma");
    }

    /**
     * Retorna o mnemônico do menu Idioma.
     *
     * @return Mnemônico do menu Idioma.
     */
    public static char obterMnemonicoMenuIdioma() {
        return rb.getString("mnemonico.menu.idioma").charAt(0);
    }

    /**
     * Retorna o texto do menu Idioma Português.
     *
     * @return Texto do menu Idioma Português.
     */
    public static String obterMenuIdiomaPortugues() {
        return rb.getString("menu.idioma.pt_br");
    }

    /**
     * Retorna o texto do menu Idioma Inglês.
     *
     * @return Texto do menu Idioma Inglês.
     */
    public static String obterMenuIdiomaIngles() {
        return rb.getString("menu.idioma.en_us");
    }

    /**
     * Retorna o texto do menu Ajuda.
     *
     * @return Texto do menu Ajuda.
     */
    public static String obterMenuAjuda() {
        return rb.getString("menu.ajuda");
    }

    /**
     * Retorna o mnemônico do menu Ajuda.
     *
     * @return Mnemônico do menu Ajuda.
     */
    public static char obterMnemonicoMenuAjuda() {
        return rb.getString("mnemonico.menu.ajuda").charAt(0);
    }

    /**
     * Retorna o texto do menu Sobre.
     *
     * @return Texto do menu Sobre.
     */
    public static String obterMenuSobre() {
        return rb.getString("menu.ajuda.sobre");
    }

    /**
     * Retorna o texto da mensagem de confirmação de saída do sistema.
     *
     * @return Texto da mensagem de confirmação de saída do sistema.
     */
    public static String obterConfirmacaoSaida() {
        return rb.getString("confirmacao.saida.descricao");
    }

    /**
     * Retorna o texto da mensagem de confirmação ao deletar.
     *
     * @return Texto da mensagem de confirmação ao deletar.
     */
    public static String obterConfirmacaoDeletar() {
        return rb.getString("confirmacao.deletar.descricao");
    }

    /**
     * Retorna o texto da mensagem de erro de autencicação
     *
     * @return Texto da mensagem de erro de autencicação
     */
    public static String obterErroAutenticacao() {
        return rb.getString("erro.usuario.autenticacao");
    }

    /**
     * Retorna o texto da mensagem de usuário já cadastrado.
     *
     * @return Texto da mensagem de usuário já cadastrado.
     */
    public static String obterErroUsuarioJaCadastrado() {
        return rb.getString("erro.usuario.ja_cadastrado");
    }

    /**
     * Retorna o texto da mensagem de senhas não conferem.
     *
     * @return Texto da mensagem de senhas não conferem.
     */
    public static String obterErroSenhasNaoConferem() {
        return rb.getString("erro.usuario.senhas_nao_conferem");
    }

    /**
     * Retorna o texto da mensagem de dados vazios.
     *
     * @return Texto da mensagem de de dados vazios.
     */    
    public static String obterErroDadosInvalidos() {
        return rb.getString("erro.usuario.dados_invalidos");
    }
    
     /**
     * Retorna o texto da mensagem de titulos iguais.
     *
     * @return Texto da mensagem de de titulos iguais.
     */    
    public static String obterErroTitulosIguais() {
        return rb.getString("erro.serie.titulos_iguais");
    }
    
    /**
     * Retorna o texto da mensagem de lista já avaliada.
     *
     * @return Texto da mensagem de de lista já avaliada.
     */    
    public static String obterErroListaJaAvaliada() {
        return rb.getString("erro.lista.ja_avaliada");
    }

    /**
     * Retorna o texto da mensagem de cadastro de usuário efetuado com sucesso.
     *
     * @return Texto da mensagem de cadastro de usuário efetuado com sucesso.
     */
    public static String obterSucessoCadastroUsuario() {
        return rb.getString("sucesso.usuario.cadastro");
    }
    
    public static String obterSucessoCadastroListaSerie() {
        return rb.getString("sucesso.lista.cadastro");
    }
    
    public static String obterSucessoImportarListaSerie() {
        return rb.getString("sucesso.lista.importar");
    }
    
    /**
     * Retorna o texto da mensagem de cadastro de série efetuado com sucesso.
     *
     * @return Retorna o texto da mensagem de cadastro de série efetuado com sucesso.
     */
    public static String obterSucessoCadastroSerie() {
        return rb.getString("sucesso.serie.cadastro");
    }
    
     /**
     * Retorna o texto da mensagem de remoção de série efetuado com sucesso.
     *
     * @return Retorna o texto da mensagem de remoção de série efetuado com sucesso.
     */
    public static String obterSucessoDeletarSerie() {
        return rb.getString("sucesso.serie.deletar");
    }
    
    /**
     * Retorna o texto da mensagem de remoção de ListaSérie efetuado com sucesso.
     *
     * @return Retorna o texto da mensagem de remoção de ListaSérie efetuado com sucesso.
     */
    public static String obterSucessoDeletarListaSerie() {
        return rb.getString("sucesso.lista.deletar");
    }
    
    /**
     * Retorna o texto da mensagem de ListaSérie avaliada com sucesso.
     *
     * @return Retorna o texto da mensagem  de ListaSérie avaliada com sucesso.
     */
    public static String obterSucessoListaAvaliada() {
        return rb.getString("sucesso.lista.avaliada");
    }

    /**
     * Retorna o texto da mensagem de edicao de serie efetuada com sucesso.
     *
     * @return Retorna o texto da mensagem de edicao de serie efetuada com sucesso.
     */
    public static String obterSucessoAlterarSerie() {
        return rb.getString("sucesso.serie.alterada");
    }

    /**
     * Retorna o título da mensagem de confirmação.
     *
     * @return Título da mensagem de confirmação.
     */
    public static String obterTituloMensagemConfirmacao() {
        return rb.getString("confirmacao.titulo");
    }

    /**
     * Retorna o texto da mensagem Sobre.
     *
     * @return Texto da mensagem Sobre.
     */
    public static String obterMensagemSobre() {
        return rb.getString("sistema.sobre");
    }

    /**
     * Retorna o título da mensagem de informação.
     *
     * @return Título da mensagem de informação.
     */
    public static String obterTituloMensagemInformacao() {
        return rb.getString("informacao.titulo");
    }

    /**
     * Retorna o título da mensagem de erro.
     *
     * @return Título da mensagem de erro.
     */
    public static String obterTituloMensagemErro() {
        return rb.getString("erro.titulo");
    }

    /**
     * Retorna o título da tela de autenticação.
     *
     * @return Título da tela de autenticação.
     */
    public static String obterTituloTelaAutenticacao() {
        return rb.getString("tela.autenticacao.titulo");
    }
    
    /**
     * Retorna o título da tela pequisar por listas.
     *
     * @return Título da tela pequisar por listas.
     */
    public static String obterTituloTelaBuscarListas() {
        return rb.getString("tela.buscarlistas.titulo");
    }
       
    /**
     * Retorna o título da tela AvaliarListas.
     *
     * @return Título da tela AvaliarListas.
     */
    public static String obterTituloTelaAvaliarListas() {
        return rb.getString("tela.avaliar_listas.titulo");
    }
       
        
        
    public static String obterTituloTelaDetalhes() {
        return rb.getString("tela.detalhes.titulo");
    }

    /**
     * Retorna o título da tela de principal.
     *
     * @return Título da tela de principal.
     */
    public static String obterTituloTelaPrincipal() {
        return obterNomeDoSistema();
    }

    /**
     * Retorna o título da tela de Minhas Séries.
     *
     * @return Título da tela de Minhas Séries.
     */
    public static String obterTituloTelaMinhasSeries() {
        return rb.getString("tela.minhasseries.titulo");
    }
    
    /**
     * Retorna o título da tela de Minhas Séries.
     *
     * @return Título da tela de Minhas Séries.
     */
    public static String obterTituloTelaMinhasListas() {
        return rb.getString("tela.minhaslistas.titulo");
    }
    
    public static String obterTituloTelaCadastrarLista() {
        return rb.getString("tela.lista.cadastrar");
    }
        
    public static String obterTituloTelaImportarLista() {
        return rb.getString("tela.lista.importar");
    }
    /**
     * Retorna o título da tela de Cadastro de Usuários.
     *
     * @return Título da tela de Cadastro de Usuários.
     */
    public static String obterTituloTelaCadastrarUsuario() {
        return rb.getString("tela.cadastrousuario.titulo");
    }
    
    

     /**
     * Retorna o título da tela de Listas Publicas.
     *
     * @return Título da tela de Listas Publicas.
     */
    public static String obterTituloTelaListasPublicas() {
        return rb.getString("tela.listas.publicas");
    }
    
    /**
     * Retorna o texto do rótulo login do usuário.
     *
     * @return Texto do rótulo login do usuário.
     */
    public static String obterRotuloUsuarioLogin() {
        return rb.getString("rotulo.usuario.login");
    }

    /**
     * Retorna o texto do rótulo avaliação.
     *
     * @return Texto do rótulo avaliação.
     */
    public static String obterRotuloAvaliar() {
        return rb.getString("rotulo.lista.avaliar");
    }

    /**
     * Retorna o texto do rótulo senha do usuário.
     *
     * @return Texto do rótulo senha do usuário.
     */
    public static String obterRotuloUsuarioSenha() {
        return rb.getString("rotulo.usuario.senha");
    }

    /**
     * Retorna o texto do botão Entrar (logar).
     *
     * @return Texto do botão Entrar (logar).
     */
    public static String obterBotaoEntrar() {
        return rb.getString("botao.entrar");
    }
    
    public static String obterBotaoVisualizar() {
        return rb.getString("botao.visualizar");
    }

    /**
     * Retorna o texto do botão Cancelar.
     *
     * @return Texto do botão Cancelar.
     */
    public static String obterBotaoCancelar() {
        return rb.getString("botao.cancelar");
    }
    
    /**
     * Retorna o texto do botão Avaliar.
     *
     * @return Texto do botão Avaliar.
     */
    public static String obterBotaoAvaliar() {
        return rb.getString("botao.avaliar");
    }

    /**
     * Retorna o texto do botão Salvar.
     *
     * @return Texto do botão Salvar.
     */
    public static String obterBotaoSalvar() {
        return rb.getString("botao.salvar");
    }
    
    /**
     * Retorna o texto do botão Importar.
     *
     * @return Texto do botão Importar.
     */
    public static String obterBotaoImportar() {
        return rb.getString("botao.importar");
    }
    /**
     * Retorna o texto do botão Buscar.
     *
     * @return Texto do botão Buscar.
     */
    public static String obterBotaoBuscar() {
        return rb.getString("botao.buscar");
    }


    /**
     * Retorna o texto do botão Novo.
     *
     * @return Texto do botão Novo.
     */
    public static String obterBotaoNovo() {
        return rb.getString("botao.novo");
    }
    
    /**
     * Retorna o texto do botão Novo.
     *
     * @return Texto do botão Novo.
     */
    public static String obterBotaImportar() {
        return rb.getString("botao.importar");
    }

    /**
     * Retorna o texto do botão Editar.
     *
     * @return Texto do botão Editar.
     */
    public static String obterBotaoEditar() {
        return rb.getString("botao.editar");
    }

    /**
     * Retorna o texto do botão Excluir (deletar).
     *
     * @return Texto do botão Excluir (deletar).
     */
    public static String obterBotaoDeletar() {
        return rb.getString("botao.deletar");
    }

    /**
     * Retorna o texto do rótulo nome do usuário.
     *
     * @return Texto do rótulo nome do usuário.
     */
    public static String obterRotuloUsuarioNome() {
        return rb.getString("rotulo.usuario.nome");
    }
    
    public static String obterRotuloSerie() {
        return rb.getString("rotulo.lista.serie");
    }
    
    /**
     * Retorna o texto do rótulo título da série.
     *
     * @return Texto do rótulo título da série.
     */
    public static String obterRotuloSerieTitulo() {
        return rb.getString("rotulo.serie.titulo");
    }
    
    /**
     * Retorna o texto do rótulo Pesquisar.
     *
     * @return Texto do rótulo Pesquisar.
     */
    public static String obterRotuloListaTitulo() {
        return rb.getString("rotulo.lista.titulo");
    }
        

    
    public static String obterRotuloListaPrivado() {
        return rb.getString("rotulo.lista.privado");
    }

    public static String obterRotuloListaAutor() {
        return rb.getString("rotulo.lista.autor");
    }
    
    public static String obterRotuloListaTags() {
        return rb.getString("rotulo.lista.tags");
    }
    
    /**
     * Retorna o texto do rótulo número de temporadas da série.
     *
     * @return Texto do rótulo número de temporadas da série.
     */
    public static String obterRotuloSerieNumTemporadas() {
        return rb.getString("rotulo.serie.numtemporadas");
    }

    /**
     * Retorna o texto do rótulo ano da série.
     *
     * @return Texto do rótulo ano da série.
     */
    public static String obterRotuloSerieAno() {
        return rb.getString("rotulo.serie.ano");
    }

    /**
     * Retorna o texto do rótulo gênero da série.
     *
     * @return Texto do rótulo gênero da série.
     */
    public static String obterRotuloSerieGenero() {
        return rb.getString("rotulo.serie.genero");
    }

    /**
     * Retorna o texto do rótulo elenco da série.
     *
     * @return Texto do rótulo elenco da série.
     */
    public static String obterRotuloSerieElenco() {
        return rb.getString("rotulo.serie.elenco");
    }

    /**
     * Retorna o texto do rótulo confirmar senha do usuário.
     *
     * @return Texto do rótulo confirmar senha do usuário.
     */
    public static String obterRotuloUsuarioConfirmarSenha() {
        return rb.getString("rotulo.usuario.confirmar_senha");
    }
    
}
