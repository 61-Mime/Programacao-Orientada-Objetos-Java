package Controler;

import Model.Encomenda;
import Model.GestTrazAqui;
import Model.LinhaEncomenda;
import Model.Login;
import View.Apresentacao;

import java.awt.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InterpretadorUtilizador implements Serializable {
    Apresentacao a = new Apresentacao();
    Input in = new Input();

    private String[] lerLinhaEncomenda(String message, GestTrazAqui c, String storeCode){
        Scanner s = new Scanner(System.in);
        String line;
        String[] linhaPartida;

        do{
            a.printMessage(message);
            line = s.nextLine();
            linhaPartida = line.split(" \\| ");
        } while (!linhaEncomendaValida(linhaPartida, storeCode, c));

        return linhaPartida;
    }

    private boolean linhaEncomendaValida(String[] line, String storeCode, GestTrazAqui c) {
        String[] tmp;

        for (String s: line) {
            tmp = s.split(" ");

            if (!c.containsProdutoLoja(storeCode, tmp[0]))
                return false;
        }
        return true;
    }

    public String aceitaEstafeta(GestTrazAqui c,String encCode){
        boolean aceite = false;
        Scanner sc = new Scanner(System.in);
        List <String> list = c.possiveisEstafetas(encCode);
        String code = "";
        System.out.println(list);
        while (!aceite) {
            code = c.escolheEstafeta(list,encCode);
            if(code.equals("") || c.getEstafetaType(code).equals("Voluntario"))
                aceite = true;
            else {
                if(in.lerSN("Aceita a transportadora " + c.getEstafetaName(code) + " pelo preço:" + c.precoEncomenda(encCode,code) + "€?(S/N)"))
                    aceite = true;
                else
                    list.remove(code);
            }
        }
        return code;
    }

    private Encomenda registaEncomenda(String[] linhaPartida, GestTrazAqui c, String userCode, String storeCode) {
        double peso = 0, preco = 0;
        String[] tmp;
        List<LinhaEncomenda> linhaEncomendas = new ArrayList<>();
        boolean isMedic = false;

        for (String s: linhaPartida) {
            tmp = s.split(" ");
            double quantidade = Double.parseDouble(tmp[1]);
            peso += quantidade * c.getProdWeight(tmp[0]);
            linhaEncomendas.add(registaLinhaEncomenda(tmp[0], quantidade, c.getProdPrice(tmp[0]), c));

            if(c.getProdisMedic(tmp[0]))
                isMedic = true;
        }

        String encCode;
        do { encCode = c.generateCode("Encomenda"); } while(c.containsEncomenda(encCode));

        return new Encomenda(encCode, userCode, "", storeCode, peso, isMedic, LocalDateTime.now(), false, linhaEncomendas, false,0);
    }

    private LinhaEncomenda registaLinhaEncomenda(String prodCode, double quantidade, double preco, GestTrazAqui c) {
        return new LinhaEncomenda(prodCode, c.getProdName(prodCode), quantidade, preco);
    }

    public void interpretador(GestTrazAqui c, Login l) {
        boolean r=true;
        Scanner s = new Scanner(System.in);
        String code,encCode;
        double pontuacao = 0;
        int command;

        while(r) {
            a.printMenuUtilizador();
            command = (int) in.lerDouble("Escolha a sua opção:", 0, 3);

            switch(command) {
                case 1:
                    List<String> list = c.getEncReady(l.getCode());
                    a.printArray("Encomendas disponíveis:", list);
                    a.printPedirEncomenda();
                    encCode = s.nextLine();
                    if(list.contains(encCode)) {
                        code = aceitaEstafeta(c,encCode);
                        if(!code.equals("") && c.getEstafetaType(code).equals("Voluntario")){
                            c.addEncomendaEstafeta(code,encCode);
                            c.addUserStandBy(l.getCode(), encCode);
                            c.setEstafetaOccup(code,true);
                            c.addEstafetaNotificacao(code, a.notificacaoNovaEntregaPendente(l.getCode()), 1);
                            a.printEncomendaStandBy(code);
                        }
                        else if(!code.equals("") && c.getEstafetaType(code).equals("Transportadora")){
                            c.entregarEncomenda(encCode, code);
                            a.printEncomendaEntregue(code, c.getEstafetaType(code), c.getEstafetaName(code), c.precoEncomenda(encCode, code), c.getEncTime(encCode));

                            if (in.lerSN("Pretende classificar a entrega?(S/N)"))
                                pontuacao = in.lerDouble("Introduza a classificação (0/10)", 0, 10);
                            c.classificarEstafeta(pontuacao, code);

                            c.addUserNotificacao(l.getCode(), a.notificacaoEntregaTransportadora(code, encCode), 2);
                            c.addEstafetaNotificacao(code, a.notificacaoEntregaAoUtilizador(l.getCode(), encCode), 1);
                        }
                        else
                            a.printErroEntrega();
                    }
                    else
                        a.printErroEncomendaInvalida();
                    break;

                case 2:
                    int res = (int) in.lerDouble("Escolha um tipo (1-Voluntários|2-Transportadoras|3-Ambos)",1,3);
                    LocalDateTime min = in.lerData("Intruza a 1º data de tipo(2018-12-02T10:15)");
                    LocalDateTime max = in.lerData("Intruza a 2º data de tipo(2018-12-02T10:15)");
                    a.printEncomendas("Lista de Encomendas do Utilizador", c.getUserEncbyData(l.getCode(),res,min,max));
                    break;

                case 3:
                    a.printArray("Lojas disponíveis:", c.getLojas());
                    String loja = in.lerString("Introduza o código da loja para fazer a encomenda:", c);

                    a.printArray("Produtos disponíveis:", c.getProdutosLoja(loja));
                    String[] linha = lerLinhaEncomenda("Escolhas os produtos, seguidos da quantidade separados por espaço e os produtos por \" | \"\n" +
                                                                "Por exemplo: p9 2.1 | p10 3 | p11 3.2", c, loja);

                    Encomenda enc = registaEncomenda(linha, c, l.getCode(), loja);

                    a.printFatura(enc);

                    if(in.lerSN("Quer continuar com a compra? (S/N)")) {
                        c.addEncomenda(enc);
                        c.aceitarEncomenda(enc.getEncCode());
                        a.printEncomendaAceite();
                        c.addUserNotificacao(l.getCode(), a.notificacaoCompraRealizada(enc.getEncCode(), loja), 1);
                    }

                    else
                        a.printCompraCancelada();

                    break;

                case 0:
                    r=false;
                    break;

                default:
                    a.printErroComandoInvalido();
                    break;
            }
        }
    }
}
