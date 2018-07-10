package com.eisi.fia.ues.sv.examen2a;

public class Equipo
{
    private String codeq;
    private String nomeq;
    private int ganados;
    private int perdidos;
    private int empatados;

    public Equipo()
    {
    }

    public Equipo(String codeq, String nomeq, int ganados, int perdidos, int empatados)
    {
        this.codeq = codeq;
        this.nomeq = nomeq;
        this.ganados = ganados;
        this.perdidos = perdidos;
        this.empatados = empatados;
    }

    public String getCodeq() {
        return codeq;
    }

    public void setCodeq(String codeq) {
        this.codeq = codeq;
    }

    public String getNomeq() {
        return nomeq;
    }

    public void setNomeq(String nomeq) {
        this.nomeq = nomeq;
    }

    public int getGanados() {
        return ganados;
    }

    public void setGanados(int ganados) {
        this.ganados = ganados;
    }

    public int getPerdidos() {
        return perdidos;
    }

    public void setPerdidos(int perdidos) {
        this.perdidos = perdidos;
    }

    public int getEmpatados() {
        return empatados;
    }

    public void setEmpatados(int empatados) {
        this.empatados = empatados;
    }
}
