package com.company;

public class Login {
    private String code;
    private String password;
    private String tipoConta;
    private String nome;

    public Login(String code,String password,String tipoConta,String nome) {
        this.code = code;
        this.password = password;
        this.tipoConta = tipoConta;
        this.nome = nome;
    }

    public Login(Login l) {
        this.code = l.getCode();
        this.password = l.getPassword();
        this.tipoConta = l.getTipoConta();
        this.nome = l.getNome();
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public String getPassword() {
        return password;
    }

    public String getCode() {
        return code;
    }

    public String getNome() {
        return nome;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Login clone() {
        return new Login(this);
    }
}
