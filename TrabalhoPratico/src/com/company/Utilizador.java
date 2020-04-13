package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utilizador {
    private String username;
    private String email;
    private String password;
    private List<Encomenda> entregas;


     //--------------------------------------------------------------Construtores--------------------------------------------------------------------------\\

    public Utilizador() {
        this.username = new String();
        this.email = new String();
        this.password = new String();
        this.entregas = new ArrayList<>();
    }

    public Utilizador(String username, String email, String password, List<Encomenda> entregas) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.entregas = entregas;
        setEntregas(entregas);
    }

    public Utilizador(Utilizador user) {
        this.username = user.username;
        this.email = user.email;
        this.password = user.password;
        setEntregas(user.getEntregas());
    }

     //--------------------------------------------------------------Getters e Setters--------------------------------------------------------------------------\\
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Encomenda> getEntregas() {
        List<Encomenda> ent = new ArrayList<>();

        for(Encomenda enc: this.entregas)
            ent.add(enc.clone());

        return ent;
    }

    public void setEntregas(List<Encomenda> ent) {
        this.entregas = new ArrayList<>();

        for(Encomenda enc: ent)
            this.entregas.add(enc.clone());
    }

    //--------------------------------------------------------------toString, equals e clone--------------------------------------------------------------------------\\


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Utilizador{");
        sb.append("username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", entregas=").append(entregas);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilizador that = (Utilizador) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(entregas, that.entregas);
    }

    public Utilizador clone() {
        return new Utilizador(this);
    }
}
