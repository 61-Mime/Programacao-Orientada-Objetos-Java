package Controler;

import Model.GestTrazAqui;
import Model.Login;
import View.Apresentacao;

import java.io.Serializable;
import java.time.LocalDateTime;

public class InterpretadorVoluntario implements Serializable {
    private final Input in;

    public InterpretadorVoluntario() {
        in = new Input();
    }

    public void interpretador(Apresentacao a, GestTrazAqui c, Login l) {
        boolean r = true;
        int command;
        String encCode;

        while (r) {
            a.printMenuVoluntario();
            command = (int) in.lerDouble(a,"Escolha a sua opção:", 0, 4);

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
                    encCode = c.encomendaStandBy(l.getCode());
                    if(!encCode.equals("")) {
                        c.removeUserStandBy(c.getEncUser(encCode), encCode);
                        if (in.lerSN(a,"Pretender aceitar a entrega da encomenda " + encCode + " ao utilizador " + c.getEncUser(encCode) + "(S/N)")) {
                            c.entregarEncomenda(encCode, l.getCode());
                            a.printEncomendaEntregueVol(c.getEncUser(encCode), c.getEncUserName(encCode), c.getEncTime(encCode));

                            c.addUserNotificacao(c.getEncUser(encCode), a.notificacaoVoluntarioAceite(l.getCode()), 1, "");
                            c.addUserNotificacao(c.getEncUser(encCode), a.notificacaoEntregaVoluntario(l.getCode(), encCode), 2, l.getCode());
                            c.addEstafetaNotificacao(l.getCode(), a.notificacaoEntregaAoUtilizador(c.getEncUser(encCode), encCode), 1, "");
                        }
                        else {
                            c.removerEnc(l.getCode(), encCode);
                            a.printEncRecusada();

                            c.addUserNotificacao(c.getEncUser(encCode), a.notificacaoVoluntarioRecusado(l.getCode()), 1, "");
                        }
                        c.setEstafetaOccup(l.getCode(),false);
                    }
                    else
                        a.printSemEncomendas();

                    break;

                case 3:
                    LocalDateTime min = in.lerData(a,"Intruza a 1º data de tipo(2018-12-02T10:15)");
                    LocalDateTime max = in.lerData(a,"Intruza a 2º data de tipo(2018-12-02T10:15)");
                    a.printEncomendas("Lista de Entregas da Transportadora", c.getEncomendasEstafeta(l.getCode(),min,max));
                    break;

                case 4:
                    a.printEstafetaClassicacao(c.getEstafetaClassificacao(l.getCode()));
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
