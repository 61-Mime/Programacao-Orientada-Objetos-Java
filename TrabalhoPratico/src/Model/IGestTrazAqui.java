package Model;

import java.util.List;
import java.util.Set;

public interface IGestTrazAqui {
    Utilizador getUser(String userCode);
    void setUser(Utilizador user);
    void addUser(Utilizador user);
    Estafeta getEstafeta(String code);
    void setEstafeta(Estafeta estafeta);
    void addEstafeta(Estafeta estafeta);
    Loja getLoja(String storeCode);
    void setLoja(Loja loja);
    void addLoja(Loja loja);
    Encomenda getEncomenda(String encCode);
    void setEncomenda(Encomenda enc);
    void addEncomenda(Encomenda encomenda);
    Login getLogin(String code);
    void setLogin(Login login);
    void addLogin(Login login);
    boolean containsUser(String code);
    boolean containsPassword(String code, String password);
    boolean containsNameAndType(String name, String type);
    void aceitarEncomenda(String encCode);
    Set<String> encomendasAceites();
    boolean isEncomendaAceite(String encCode);
    double precoEncomenda(String encCode,String transpCode);
    List<Estafeta> possiveisEstafetas(Encomenda enc);
    String escolheEstafeta(Encomenda enc);
}
