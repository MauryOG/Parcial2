package com.eisi.fia.ues.sv.examen2a;

public class PartidosClausura
{
    private String numfecha;
    private String codeq;
    private int golesafavor;
    private int golesencontra;
    private String codrival;

    public PartidosClausura()
    {
    }

    public PartidosClausura(String numfecha, String codeq, int golesafavor, int golesencontra, String codrival)
    {
        this.numfecha = numfecha;
        this.codeq = codeq;
        this.golesafavor = golesafavor;
        this.golesencontra = golesencontra;
        this.codrival = codrival;
    }

    public String getNumfecha() {
        return numfecha;
    }

    public void setNumfecha(String numfecha) {
        this.numfecha = numfecha;
    }

    public String getCodeq() {
        return codeq;
    }

    public void setCodeq(String codeq) {
        this.codeq = codeq;
    }

    public int getGolesafavor() {
        return golesafavor;
    }

    public void setGolesafavor(int golesafavor) {
        this.golesafavor = golesafavor;
    }

    public int getGolesencontra() {
        return golesencontra;
    }

    public void setGolesencontra(int golesencontra) {
        this.golesencontra = golesencontra;
    }

    public String getCodrival() {
        return codrival;
    }

    public void setCodrival(String codrival) {
        this.codrival = codrival;
    }
}
