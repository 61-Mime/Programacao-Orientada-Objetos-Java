package Controler;

import Model.GestTrazAqui;
import Model.Login;
import View.Apresentacao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InterpretadorTransportadora {
    private final Input in;

    public InterpretadorTransportadora() {
        in = new Input();
    }

    private List<String> criarRota(GestTrazAqui c,String transpCode,Apresentacao a){
        String encCode;
        int max = c.getEstafetaNumEnc(transpCode);
        boolean val = true;
        List<String> list = c.encomendasPossiveis(transpCode);
        List<String> rota = new ArrayList<>();

        while (val && rota.size() < max) {
            if(list.size() == 0) {
                a.printSemEncomendas();
                val = false;
            }
            else {
                a.printArray("Encomendas disponíveis:", list);
                encCode = in.lerStringSolicitarEnc(a, a.pedirEncomenda(), list);
                list.remove(encCode);
                rota.add(encCode);

                if (!in.lerSN(a, "Pretende adicionar mais encomendas? (S/N)"))
                    val = false;
            }
        }

        return rota;
    }

    public void interpretador(GestTrazAqui c, Apresentacao a, Login l) {
        boolean r = true;
        int command;
        LocalDateTime min, max;

        while (r) {
            a.printMenuTransportadora();
            command = (int) in.lerDouble(a,"Escolha a sua opção:", 0, 6);

            switch (command) {
                case 1:
                    if(c.isEstafetaFree(l.getCode())) {
                        c.setEstafetaFree(l.getCode(), false);
                        a.printEstafetaIndisponivel();
                    }
                    else {
                        c.setEstafetaFree(l.getCode(), true);
                        a.printEstafetaDisponivel();
                    }
                    break;

                case 2:
                    String encCode = in.lerStringEncomenda(a, "Introduza o código de encomenda: ", c, l.getCode());
                    a.printEstafetaPreco(c.precoEncomenda(encCode, l.getCode()));
                    break;

                case 3:
                    min = in.lerData(a,"Intruza a 1º data de tipo(2018-12-02T10:15)");
                    max = in.lerData(a,"Intruza a 2º data de tipo(2018-12-02T10:15)");
                    a.printEncomendas("Lista de Entregas da Transportadora", c.getEncomendasEstafeta(l.getCode(),min,max));
                    break;

                case 4:
                    min = in.lerData(a,"Intruza a 1º data de tipo(2018-12-02T10:15)");
                    max = in.lerData(a,"Intruza a 2º data de tipo(2018-12-02T10:15)");
                    a.printEstafetaFaturacao(c.calcularFaturacao(l.getCode(), min, max));
                    break;

                case 5:
                    a.printEstafetaClassicacao(c.getEstafetaClassificacao(l.getCode()));
                    break;

                case 6:
                    System.out.println(c.getEstafetaRota(l.getCode()).toString());
                    if(c.getEstafetaRotaSize(l.getCode()) > 0) {
                        List<String> enclist = c.getEstafetaRota(l.getCode());
                        boolean val = true;
                        for(String enc:enclist){
                            if(!c.getUserEncStandBy(enc))
                                val = false;
                        }
                        if(val){
                            for(String code:enclist) {
                                c.entregarEncomenda(code, l.getCode());
                                a.printEncomendaEntregue(code, c.getEstafetaType(code), c.getEstafetaName(code), c.precoEncomenda(code, l.getCode()), c.getEncTime(code));
                                c.addUserNotificacao(c.getEncUser(code), a.notificacaoUtilizadorEntregaTransportadora(l.getCode(), code), 2, l.getCode());
                            }
                            a.printMessage("Encomendas entregues!");
                        }
                    }
                    List<String> enclist = criarRota(c,l.getCode(),a);
                    System.out.println(enclist.size());
                    if(enclist.size() > 0){
                        c.addEstafetaRota(l.getCode(),enclist);
                        for(String enc:enclist){
                            c.sugerirTransp(enc,l.getCode());
                            c.addUserNotificacao(c.getEncUser(enc), a.notificacaoUtilizadorAceitarTransportadora(enc,l.getCode()), 1, l.getCode());
                            c.addUserStandBy(c.getEncUser(enc),enc);
                        }
                    }
                    break;
                case 0:
                    r = false;
                    break;

                default:
                    a.printErroComandoInvalido();
            }
        }
    }
}

