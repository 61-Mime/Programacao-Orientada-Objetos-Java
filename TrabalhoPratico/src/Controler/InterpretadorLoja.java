/**
 * Classe que controla o menu da loja
 */
package Controler;

import Model.GestTrazAqui;
import Model.Login;
import View.Apresentacao;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class InterpretadorLoja implements Serializable, IInterpretador {
    private final Input in;


    public InterpretadorLoja() {
        in = new Input();
    }

    /**
     * Metodo que aceita ou não uma encomenda numa loja
     *
     * @param c GestTrazAqui
     * @param a Apresentação
     * @param l Login
     */
    private void aceitarEncomendaLoja(GestTrazAqui c, Apresentacao a, Login l) {
        List<String> encomendas = c.encomendasNaoAceitesLoja(l.getCode());

        if(encomendas.size() == 0) {
            a.printErroSemEncomenda();
            return;
        }

        a.printArray("Compras disponíveis:", encomendas);

        String encCode = in.lerStringSolicitarEnc(a, a.pedirEncomenda(), encomendas);

        if(in.lerSN(a, "Pertende aceitar a compra? (S/N)")) {
            c.aceitarEncomenda(encCode);
            c.addUserNotificacao(c.getEncUser(encCode), a.notificacaoUtilizadorLojaAceite(l.getCode()), 1, "");
            a.printCompraAceite(encCode);
        }
        else {
            c.addUserNotificacao(c.getEncUser(encCode), a.notificacaoUtilizadorLojaRecusado(l.getCode()), 1, "");
            c.removeEncomenda(encCode);
            a.printCompraRecusada(encCode);
        }
    }

    /**
     * Interpretador menu loja
     *
     * @param c GestTrazAqui
     * @param a Apresentação
     * @param l Login
     */
    public void interpretador(GestTrazAqui c, Apresentacao a,Login l) {
        boolean r = true;
        int command;
        String encCode;

        while (r) {
            if(!c.hasQueueInfoLoja(l.getCode())) {
                a.printMenuLoja();
                command = (int) in.lerDouble(a,"Escolha a sua opção:", 0, 3);

                switch (command) {
                    case 1:
                        double time = in.lerDouble(a, "Introduza o tempo de espera na fila", 0, 30);
                        c.setStoreQueueTime(l.getCode(), time);
                        break;

                    case 2:
                        aceitarEncomendaLoja(c, a, l);
                        break;
                    case 3:
                        a.printArray("Encomendas Loja:\n",c.getEncLoja(l.getCode()).stream().collect(Collectors.toList()));
                        break;

                    case 0:
                        r = false;
                        break;

                    default:
                        a.printErroComandoInvalido();
                }
            }
            else {
                a.printMenuLojaIndisponivel();
                command = (int) in.lerDouble(a,"Escolha a sua opção:", 0, 1);

                switch (command) {
                    case 1:
                        aceitarEncomendaLoja(c, a, l);
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
}
