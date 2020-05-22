package Controler;

import Model.Encomenda;
import Model.GestTrazAqui;
import Model.LinhaEncomenda;
import Model.Login;
import View.Apresentacao;

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

    private Encomenda registaEncomenda(String[] linhaPartida, GestTrazAqui c, String userCode, String storeCode) {
        double peso = 0;
        String[] tmp;
        List<LinhaEncomenda> linhaEncomendas = new ArrayList<>();

        for (String s: linhaPartida) {
            tmp = s.split(" ");
            double quantidade = Double.parseDouble(tmp[1]);
            peso += quantidade * c.getProdWeight(tmp[0]);
            linhaEncomendas.add(registaLinhaEncomenda(tmp[0], quantidade, c));
        }

        String encCode;
        do { encCode = c.generateCode("Encomenda"); } while(c.containsEncomenda(encCode));

        return new Encomenda(encCode, userCode, "", storeCode, peso, false, LocalDateTime.now(), false, linhaEncomendas, false);
    }

    private LinhaEncomenda registaLinhaEncomenda(String prodCode, double quantidade, GestTrazAqui c) { // Atualizar preço
        return new LinhaEncomenda(prodCode, c.getProdName(prodCode), quantidade, 0);// bump
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
                        code = c.escolheEstafeta(encCode);
                        c.entregarEncomenda(encCode,code);
                        a.printEncomendaEntregue(code, c.getEstafetaType(code), c.getEstafetaName(code), c.precoEncomenda(encCode, code),
                                                 c.calculaTempo(c.getEstafetaCoord(code), c.getStoreCoordFromEnc(encCode), c.getUserCoord(l.getCode()), c.getStoreQueueTimeFromEnc(encCode), c.getEstafetaVelocidade(code)));

                        a.printPedirClassificar();
                        if(s.nextLine().toUpperCase().equals("S"))
                            pontuacao = in.lerDouble("Introduza a classificação (0/10)",0,10);
                        c.classificarEstafeta(pontuacao,code);
                    }
                    else
                        a.printErroEncomendaInvalida();
                    break;

                case 2:
                    int res = (int) in.lerDouble("Escolha um tipo (1-Voluntários|2-Transportadoras|3-Ambos)",1,3);//escolheVoluntarioTransportadora();
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
                    c.addEncomenda(enc);
                    c.aceitarEncomenda(enc.getEncCode());
                    a.printEncomendaAceite();
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
