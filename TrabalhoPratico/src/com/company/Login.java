package com.company;

public class Login {
    private String email;
    private String password;
    private String tipoConta;
    private String nome;

    public Login(String email,String password,String tipoConta,String nome) {
        this.email = email;
        this.password = password;
        this.tipoConta = tipoConta;
        this.nome = nome;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
