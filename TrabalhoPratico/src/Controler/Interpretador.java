package Controler;

import Model.*;
import View.Apresentacao;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Interpretador {
    Apresentacao a = new Apresentacao();

    public Login login(GestTrazAqui c) {
        Scanner s = new Scanner(System.in);
        String user, pass;

        a.printMessage("Introduza o usercode: ");
        user = s.nextLine();

        if(c.containsUser(user)) {
            a.printMessage("Introduza a password: ");
            pass = s.nextLine();

            if(c.containsPassword(user, pass))
                return c.getLogin(user);
        }

        return null;
    }

    public Utilizador registarUtilizador(String code, String nome){
        Scanner s = new Scanner(System.in);
        double price;

        Coordenadas cr = lerCoordenada();
        price = lerDouble("Introduza o Preco Máximo: ",0,1000000);

        return new Utilizador(code, nome, cr, price, new ArrayList<>());
    }

    public Estafeta registarEstafeta(String code, String nome, String type) {
        Scanner s = new Scanner((System.in));
        double raio, velocidade;
        String medic;
        boolean isMedic;

        Coordenadas cr = lerCoordenada();
        raio = lerDouble("Introduza o seu raio da ação: ",0,100000);
        velocidade = lerDouble("Introduza a sua velocidade média: ",0,100000);

        a.printMessage("Pode transportar encomendas médicas? (S/N): ");
        medic = s.nextLine();

        isMedic = medic.equals("S");

        return new Estafeta(code, nome, cr, raio, velocidade, true, isMedic, 0, type);
    }

    public Transportadora registarTransportadora(Estafeta e) {
        Scanner s = new Scanner(System.in);
        int nif;
        double taxaKm, taxaPeso;

        nif = (int)lerDouble("Introduza o seu NIF: ",0,1000000);
        taxaKm = lerDouble("Introduza a sua taxa por Km: ",0,1000000);
        taxaPeso = lerDouble("Introduza a sua taxa por Kg: ",0,1000000);

        return new Transportadora(e.getCode(), e.getName(), e.getGps(), e.getRaio(), e.getVelocidade(), e.isFree(), e.isMedic(), e.getClassificacao(), nif, taxaKm, taxaPeso, 0);
    }

    public Loja registarLoja(String code, String nome) {
        Scanner s = new Scanner(System.in);
        String queue;
        boolean hasQueue;

        Coordenadas cr = lerCoordenada();

        a.printMessage("A loja tem informação sobre a fila de espera? (S/N): ");
        queue = s.nextLine();

        hasQueue = queue.equals("S");

        if (hasQueue) {
            double queueTime = lerDouble("Qual é o tempo médio de espera em fila?: ",0,1000);
            return new Loja(code, nome, cr, hasQueue, queueTime, new ArrayList<>());
        }

        return new Loja(code, nome, cr, hasQueue, -1, new ArrayList<>());
    }

    public boolean registar(GestTrazAqui c) {
        Scanner s = new Scanner(System.in);
        Login l = new Login();
        String code, nome, pass, tipo;

        a.printMessage("Introduza o nome completo: ");
        nome = s.nextLine();

        do {
            a.printMessage("Introduza o tipo de conta (Voluntario / Transportadora / Utilizador / Loja): ");
            tipo = s.nextLine();
        } while(!(tipo.equals("Voluntario") || tipo.equals("Transportadora") || tipo.equals("Utilizador") || tipo.equals("Loja")));

        if(!c.containsNameAndType(nome, tipo)) {
            l.setNome(nome);
            l.setTipoConta(tipo);

            a.printMessage("Introduza a password: ");
            pass = s.nextLine();

            l.setPassword(pass);

            do { code = c.generateCode(tipo); } while(c.containsUser(code));

            l.setCode(code);

            switch(tipo) {
                case "Voluntario":
                    c.addEstafeta(registarEstafeta(code, nome, "Voluntario"));
                    break;
                case "Transportadora":
                    Estafeta e = registarEstafeta(code, nome, "Transportadora");
                    c.addEstafeta(registarTransportadora(e));
                    break;
                case "Utilizador":
                    c.addUser(registarUtilizador(code, nome));
                    break;
                case "Loja":
                    c.addLoja(registarLoja(code, nome));
                    break;
            }

            a.printMessageLn("Código de acesso: " + l);

            c.addLogin(l);

            return true;
        }

        return false;
    }

    public void menuUtilizador(GestTrazAqui c, Login l) {
        boolean r=true;
        Scanner s = new Scanner(System.in);
        String line,code,encCode;
        double pontuacao = 0;

        while(r) {
            a.printMenuUtilizador();
            line = s.nextLine();

            switch(line) {
                case "1":
                    List<String> list = c.getEncReady(l.getCode());
                    System.out.println(list);
                    System.out.println("Escolha uma encomenda:");
                    encCode = s.nextLine();
                    if(list.contains(encCode)) {
                        code = c.escolheEstafeta(encCode);
                        c.entregarEncomenda(encCode,code);
                        System.out.println("A sua encomenda foi entregue pelo "+ c.getEstafetaType(code) +" " + c.getEstafetaName(code));
                        System.out.println("Pretende classificar a entrega?(S/N)");
                        if(s.nextLine().toUpperCase().equals("S"))
                            pontuacao = lerDouble("Introduza a classificação (0/10)",0,10);
                            c.classificarEstafeta(pontuacao,code);
                    }
                    else
                        System.out.println("Encomenda não está disponivel!");
                    break;

                case "2":
                    int res = (int)lerDouble("Escolha um tipo (1-Voluntários|2-Transportadoras|3-Ambos)",1,3);//escolheVoluntarioTransportadora();
                    LocalDateTime min = lerData("Intruza a 1º data de tipo(2018-12-02T10:15)");
                    LocalDateTime max = lerData("Intruza a 2º data de tipo(2018-12-02T10:15)");
                    System.out.println(c.getUserEncbyData(l.getCode(),res,min,max));
                    break;

                case "3":
                    a.printMessageLn("Lojas disponíveis:");
                    a.printMessage(c.getLojas().toString());
                    String loja = lerString("Introduza o código da loja para fazer a encomenda:", c);
                    a.printMessageLn("Produtos disponíveis:");
                    a.printMessage(c.getProdutosLoja(loja).toString());
                    String[] linha = lerLinhaEncomenda("Escolhas os produtos, seguidos da quantidade separados por espaço e os produtos por \" | \"\n Por exemplo: p9 2.1 | p10 3 | p11 3.2", c, loja);
                    Encomenda enc = registaEncomenda(linha, c, l.getCode(), loja);
                    c.addEncomenda(enc);
                    c.aceitarEncomenda(enc.getEncCode());
                    a.printMessageLn("A sua encomenda foi registada e precisa de ser solicitada.");
                    break;

                case "B":
                    r=false;
                    break;

                default:
                    a.printMessageLn("Comando inválido");
            }
        }
    }

    public void menuVoluntario(GestTrazAqui c, Login l) {
        boolean r=true;
        Scanner s = new Scanner(System.in);
        String line;

        while(r) {
            a.printMenuVoluntario();
            line = s.nextLine();

            switch(line) {
                case "1":
                    c.setEstafetaFree(l.getCode());
                    a.printMessage("Está disponivel para entregar encomendas");
                    break;

                case "Q":
                    r=false;
                    break;

                default:
                    a.printMessageLn("Comando inválido");
            }
        }
    }

    public void menuTransportadora(GestTrazAqui c, Login l) {
        boolean r=true;
        Scanner s = new Scanner(System.in);
        String line;

        while(r) {
            a.printMenuTransportadora();
            line = s.nextLine();

            switch(line) {
                case "1":
                    c.setEstafetaFree(l.getCode());
                    a.printMessage("Está disponivel para entregar encomendas");
                    break;

                case "Q":
                    r=false;
                    break;

                default:
                    a.printMessageLn("Comando inválido");
            }
        }
    }

    public void menuLoja(GestTrazAqui c, Login l) {
        boolean r=true;
        Scanner s = new Scanner(System.in);
        String line;

        while(r) {
            a.printMenuLoja();
            line = s.nextLine();

            switch(line) {
                case "1":
                    break;

                case "Q":
                    r=false;
                    break;

                default:
                    a.printMessageLn("Comando inválido");
            }
        }
    }

    public void menu(GestTrazAqui c, Login l) {
        switch (l.getTipoConta()) {
            case "Utilizador":
                menuUtilizador(c, l);
                break;
            case "Voluntario":
                menuVoluntario(c, l);
                break;
            case "Transportadora":
                menuTransportadora(c, l);
                break;
            case "Loja":
                menuLoja(c, l);
                break;
        }
    }

    public void interpretador(GestTrazAqui c) {
        boolean r=true;
        Scanner s = new Scanner(System.in);
        String line;
        Login l;

        a.welcome();
        s.nextLine();

        while(r) {
            a.printMenu();
            line = s.nextLine();

            switch(line){
                case "1":
                    if((l = login(c))!= null) {
                        a.printMessageLn("Login efetuado com sucesso");
                        menu(c, l);
                    }

                    else
                        a.printMessageLn("Dados inválidos");
                    break;

                case "2":
                    if(registar(c)) {
                        a.printMessageLn("Registo efetuado com sucesso");
                        a.printMessageLn("Efetue Login para continuar");
                    }

                    else
                        a.printMessageLn("Dados inválidos");
                    break;

                case "Q":
                    r = false;
                    break;

                default:
                    a.printMessageLn("Comando Inválido");
                    break;
            }
        }

        s.close();
    }

    public double lerDouble(String message,int min,int max){
        Scanner s = new Scanner(System.in);
        double n = -1;

        do{
            a.printMessage(message);
            try {
                String line = s.nextLine();
                n = Double.parseDouble(line);
            } catch (NumberFormatException nfe) {
                a.printMessage(nfe.getMessage());
                n = -1;
            }
        } while (n < min || n > max);

        return n;
    }

    public LocalDateTime lerData(String message){
        Scanner s = new Scanner(System.in);
        boolean val = true;
        LocalDateTime data = null;

        do{
            a.printMessage(message);
            try {
                data = data.parse(s.nextLine());
                val = false;
            } catch (DateTimeParseException dtpe) {
                a.printMessage("Data inválida");
            }
        } while (val);

        return data;
    }

    public Coordenadas lerCoordenada(){
        Scanner s = new Scanner(System.in);
        String[] line;
        double lat,lon = 0;

        do{
            a.printMessage("Introduza a latitude ([-90,90]) e a longitude ([-180,180]), por exemplo: 30 20");
            line = s.nextLine().split(" ",2);
            try{
                lat = Double.parseDouble(line[0]);
                if(line.length == 2)
                    lon = Double.parseDouble(line[1]);
            } catch (NumberFormatException nfe) {
                a.printMessage(nfe.getMessage());
                lat = 100;
            }
        } while (line.length != 2 || lat < -90 || lat > 90 || lon < -180 || lon > 180);

        return new Coordenadas(lat,lon);
    }

    public String lerString(String message, GestTrazAqui c){
        Scanner s = new Scanner(System.in);
        String line;


        do{
            a.printMessage(message);
            line = s.nextLine();
        } while (!c.containsLoja(line));

        return line;
    }

    public String[] lerLinhaEncomenda(String message, GestTrazAqui c, String storeCode){
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

    public boolean linhaEncomendaValida(String[] line, String storeCode, GestTrazAqui c) {
        String[] tmp;

        for (String s: line) {
            tmp = s.split(" ");

            if (!c.containsProdutoLoja(storeCode, tmp[0]))
                return false;
        }
        return true;
    }

    public Encomenda registaEncomenda(String[] linhaPartida, GestTrazAqui c, String userCode, String storeCode) {
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

    public LinhaEncomenda registaLinhaEncomenda(String prodCode, double quantidade, GestTrazAqui c) { // Atualizar preço
        return new LinhaEncomenda(prodCode, c.getProdName(prodCode), quantidade, 0);// bump
    }
}
