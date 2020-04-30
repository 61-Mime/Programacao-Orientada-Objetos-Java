package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Aceite {

    private List<Encomenda> encomendasAceites;

    public Aceite() {
        this.encomendasAceites = new ArrayList<>();
    }

    public Aceite(List<Encomenda> encomendasAceites) {
        setEncomendasAceites(encomendasAceites);
    }

    public Aceite(Aceite a) {
        setEncomendasAceites(a.getEncomendasAceites());
    }

    public List<Encomenda> getEncomendasAceites() {
        List<Encomenda> res = new ArrayList<>();

        for(Encomenda e: this.encomendasAceites)
            res.add(e.clone());

        return res;
    }

    public void setEncomendasAceites(List<Encomenda> encomendasAceites) {
        this.encomendasAceites = new ArrayList<>();

        for (Encomenda e: encomendasAceites)
            this.encomendasAceites.add(e.clone());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Aceite{");
        sb.append("encomendasAceites=").append(encomendasAceites);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Aceite aceite = (Aceite) o;
        return Objects.equals(encomendasAceites, aceite.encomendasAceites);
    }

    public Aceite clone() {
        return new Aceite(this);
    }
}
