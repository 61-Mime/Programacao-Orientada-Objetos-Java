package View;

import Model.Encomenda;
import Model.LinhaEncomenda;

import java.io.Serializable;
import java.util.List;

public class Apresentacao implements IApresentacao, Serializable {
    private final ApresentacaoMain am;
    private final ApresentacaoLogin al;
    private final ApresentacaoUtilizador au;
    private final ApresentacaoVoluntarioTransportadora avt;
    private final ApresentacaoNotificacoes an;
    private final ApresentacaoLoja alj;
    private final Output out;
    private final Audio audio;

    // ------------------------ Construtor ------------------------- \\

    public Apresentacao() {
        am = new ApresentacaoMain();
        al = new ApresentacaoLogin();
        au = new ApresentacaoUtilizador();
        avt = new ApresentacaoVoluntarioTransportadora();
        an = new ApresentacaoNotificacoes();
        alj = new ApresentacaoLoja();
        out = new Output();
        audio = new Audio();
    }

    // ------------------------ Apresentação Main ------------------------- \\

    public void welcome() {
        am.welcome();
    }

    public void printMainMenuLogIn() {
        am.printMainMenuLogIn();
    }

    public void printMainMenuLogOut(String type, int numN) {
        am.printMainMenuLogOut(type, numN);
    }

    public void printMenuConsultas() {
        am.printMenuConsultas();
    }

    public void printErroComandoInvalido(){
        am.printErroComandoInvalido();
    }

    public void printFicheiroCarregado(String file){
        am.printFicheiroCarregado(file);
    }

    public void printFicheiroGuardado(String file){
        am.printFicheiroGuardado(file);
    }

    public void printSair() {
        System.out.println("A Sair do Programa");
    }

    // ------------------------ Apresentação Login ------------------------- \\

    public void printMenuLogin() {
        al.printMenuLogin();
    }

    public void printCodigoAcesso(String code) {
        al.printCodigoAcesso(code);
    }

    public void printLoginSucesso() {
        al.printLoginSucesso();
    }

    public void printLogoutSucesso() {
        al.printLogoutSucesso();
    }

    public void printRegistoSucesso() {
        al.printRegistoSucesso();
    }

    public void printErroDadosInvalidos() {
        al.printErroDadosInvalidos();
    }

    public void printPedirUsername() {
        al.printPedirUsername();
    }

    public void printPedirPassword() {
        al.printPedirPassword();
    }

    public void printPedirEncomendasMedicas() {
        al.printPedirEncomendasMedicas();
    }

    public void printPedirFilaEspera() {
        al.printPedirFilaEspera();
    }

    public void printPedirNomeCompleto() {
        al.printPedirNomeCompleto();
    }

    public void printPedirTipoConta() {
        al.printPedirTipoConta();
    }

    // ------------------------ Apresentação Utilizador ------------------------- \\

    public void printMenuUtilizador() {
        au.printMenuUtilizador();
    }

    public void printEncomendas(String message, List<Encomenda> arr)  {
        au.printEncomendas(message, arr);
    }

    public void printFatura(Encomenda enc)  {
        au.printFatura(enc);
    }

    public void printErroEntrega() {
        au.printErroEntrega();
    }

    public void printErroSemEncomenda(){
        au.printErroSemEncomenda();
    }

    public void printEncomendaEntregueVol(String code, String nome, double tempo) {
        au.printEncomendaEntregueVol(code, nome, tempo);
    }

    public void printEncomendaEntregue(String code, String tipo, String nome, double preco, double tempo) {
        au.printEncomendaEntregue(code, tipo, nome, preco, tempo);
    }

    public void printEncomendaAceite() {
        au.printEncomendaAceite();
    }

    public void printCompraCancelada() {
        au.printCompraCancelada();
    }

    public void printCompraEspera() {
        au.printCompraEspera();
    }

    public void printPedirClassificar() {
        au.printPedirClassificar();
    }

    public void printEncomendaStandBy(String code){
        au.printEncomendaStandBy(code);
    }

    public String pedirEncomenda() {
        return au.pedirEncomenda();
    }

    // ------------------------ Apresentação Voluntario Transportadora ------------------------- \\

    public void printMenuVoluntario() {
        avt.printMenuVoluntario();
    }

    public void printMenuTransportadora() {
        avt.printMenuTransportadora();
    }

    public void printEstafetaDisponivel() {
        avt.printEstafetaDisponivel();
    }

    public void printEstafetaIndisponivel() {
        avt.printEstafetaIndisponivel();
    }

    public void printEstafetaPreco(double preco) {
        avt.printEstafetaPreco(preco);
    }

    public void printEstafetaFaturacao(double faturacao) {
        avt.printEstafetaFaturacao(faturacao);
    }

    public void printEstafetaClassicacao(double classificacao) {
        avt.printEstafetaClassicacao(classificacao);
    }

    public void printSemEncomendas(){
        avt.printSemEncomendas();
    }

    public void printEncRecusada(){
        avt.printEncRecusada();
    }

    // ------------------------ Apresentacao Loja ------------------------- \\

    public void printMenuLoja() {
        alj.printMenuLoja();
    }

    public void printMenuLojaIndisponivel() {
        alj.printMenuLojaIndisponivel();
    }

    public void printCompraAceite(String encCode) {
        alj.printCompraAceite(encCode);
    }

    public void printCompraRecusada(String encCode) {
        alj.printCompraRecusada(encCode);
    }

    // ------------------------ Apresentacao Notificacoes ------------------------- \\

    public void notifTable(String not, int type, int page, int max) {
        an.notifTable(not, type, page, max);
    }

    public void printEmptyNot() {
        an.printEmptyNot();
    }

    public String notificacaoUtilizadorLojaAceite(String storeCode) {
        return an.notificacaoUtilizadorLojaAceite(storeCode);
    }

    public String notificacaoUtilizadorLojaRecusado(String storeCode) {
        return an.notificacaoUtilizadorLojaRecusado(storeCode);
    }

    public String notificacaoUtilizadorVoluntarioRecusado(String code) {
        return an.notificacaoUtilizadorVoluntarioRecusado(code);
    }

    public String notificacaoUtilizadorEntregaTransportadora(String transCode, String encCode) {
        return an.notificacaoUtilizadorEntregaTransportadora(transCode, encCode);
    }

    public String notificacaoUtilizadorEntregaVoluntario(String transCode, String encCode) {
        return an.notificacaoUtilizadorEntregaVoluntario(transCode, encCode);
    }

    public String notificacaoVoluntarioNovaEntrega(String encCode, String userCode) {
        return an.notificacaoVoluntarioNovaEntrega(encCode, userCode);
    }

    public String notificacaoLojaNovaCompra(String encCode, String userCode) {
        return an.notificacaoLojaNovaCompra(encCode, userCode);
    }

    public String notificacaoUtilizadorAceitarTransportadora(String encCode, String transpCode) {
        return an.notificacaoUserNovaTransportadora(encCode, transpCode);
    }

    // ------------------------ Audio ------------------------- \\

    public void play(String filePath) {
        audio.play(filePath);
    }

    // ------------------------ Outros métodos ------------------------- \\

    public void printArray(String message, List<String> arr) {
        out.printArray(message, arr);
    }

    public void printTable(String message, List<String> arr) {
        out.printTable(message, arr);
    }

    public void printMessage(String message) {
        out.printMessage(message);
    }

}
