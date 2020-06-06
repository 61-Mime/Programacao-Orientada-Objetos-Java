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
    private final Input in;

    public InterpretadorUtilizador() {
        in = new Input();
    }

    private void aceitarEncomenda(Apresentacao a, GestTrazAqui c, Login l) {
        String code,encCode;
        List<String> list = c.getEncReady(l.getCode());
        a.printArray("Encomendas disponíveis:", list);

        encCode = in.lerStringSolicitarEnc(a, a.pedirEncomenda(), list);
        code = aceitaEstafeta(a,c,encCode);

        if(!code.equals("") && c.getEstafetaType(code).equals("Voluntario")){
            c.addEncomendaEstafeta(code,encCode);
            c.addUserStandBy(l.getCode(), encCode);
            c.setEstafetaOccup(code,true);
            c.addEstafetaNotificacao(code, a.notificacaoVoluntarioNovaEntrega(encCode, l.getCode()), 1, "");
            a.printEncomendaStandBy(code);
        }
        else if(!code.equals("") && c.getEstafetaType(code).equals("Transportadora")){
            c.entregarEncomenda(encCode, code);
            a.printEncomendaEntregue(code, c.getEstafetaType(code), c.getEstafetaName(code), c.precoEncomenda(encCode, code), c.getEncTime(encCode));
            c.addUserNotificacao(l.getCode(), a.notificacaoUtilizadorEntregaTransportadora(code, encCode), 2, code);
        }
        else
            a.printErroEntrega();
    }

    private void fazerEncomenda(Apresentacao a, GestTrazAqui c, Login l) {
        a.printArray("Lojas disponíveis:", c.getLojas());
        String loja = in.lerStringLoja(a,"Introduza o código da loja para fazer a encomenda:", c);

        a.printArray("Produtos disponíveis:", c.getProdutosLoja(loja));
        String[] linha = in.lerLinhaEncomenda(a,"Escolhas os produtos, seguidos da quantidade separados por espaço e os produtos por \" | \"\n" +
                "Por exemplo: p9 2.1 | p10 3 | p11 3.2", c, loja);

        Encomenda enc = registaEncomenda(linha, c, l.getCode(), loja);

        if(enc == null) {
            a.printEncomendaInvalida();
            return;
        }

        a.printFatura(enc);

        if(in.lerSN(a,"Quer continuar com a compra? (S/N)")) {
            c.addEncomenda(enc);
            a.printCompraEspera();
            c.addLojaNotificacao(loja, a.notificacaoLojaNovaCompra(enc.getEncCode(),l.getCode()), 1, "");
        }

        else
            a.printCompraCancelada();
    }

    public String aceitaEstafeta(Apresentacao a, GestTrazAqui c,String encCode){
        boolean aceite = false;
        Scanner sc = new Scanner(System.in);
        List <String> list = c.possiveisEstafetas(encCode);
        String code = "";
        while (!aceite) {
            code = c.escolheEstafeta(list,encCode);
            if(code.equals("") || c.getEstafetaType(code).equals("Voluntario"))
                aceite = true;
            else {
                if(in.lerSN(a,"Aceita a transportadora " + c.getEstafetaName(code) + " pelo preço:" + c.precoEncomenda(encCode,code) + "€?(S/N)"))
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
            try {
                tmp = s.split(" ");
                double quantidade = Double.parseDouble(tmp[1]);
                peso += quantidade * c.getProdWeight(tmp[0]);
                linhaEncomendas.add(registaLinhaEncomenda(tmp[0], quantidade, c.getProdPrice(tmp[0]), c));

                if (c.getProdisMedic(tmp[0]))
                    isMedic = true;
            } catch(ArrayIndexOutOfBoundsException e) {
                return null;
            }
        }

        String encCode;
        do { encCode = c.generateCode("Encomenda"); } while(c.containsEncomenda(encCode));

        return new Encomenda(encCode, userCode, "", storeCode, peso, isMedic, LocalDateTime.now(), false, linhaEncomendas, false,0);
    }

    private LinhaEncomenda registaLinhaEncomenda(String prodCode, double quantidade, double preco, GestTrazAqui c) {
        return new LinhaEncomenda(prodCode, c.getProdName(prodCode), quantidade, preco);
    }

    public void interpretador(GestTrazAqui c, Apresentacao a, Login l) {
        boolean r=true;
        Scanner s = new Scanner(System.in);
        int command;

        while(r) {
            a.printMenuUtilizador();
            command = (int) in.lerDouble(a,"Escolha a sua opção:", 0, 3);

            switch(command) {
                case 1:
                    aceitarEncomenda(a, c, l);
                    break;

                case 2:
                    int res = (int) in.lerDouble(a,"Escolha um tipo (1-Voluntários|2-Transportadoras|3-Ambos)",1,3);
                    LocalDateTime min = in.lerData(a,"Intruza a 1º data de tipo(2018-12-02T10:15)");
                    LocalDateTime max = in.lerData(a,"Intruza a 2º data de tipo(2018-12-02T10:15)");
                    a.printEncomendas("Lista de Encomendas do Utilizador", c.getUserEncbyData(l.getCode(),res,min,max));
                    break;

                case 3:
                    fazerEncomenda(a, c, l);
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
