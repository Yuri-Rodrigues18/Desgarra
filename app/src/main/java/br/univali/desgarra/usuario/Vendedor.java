package br.univali.desgarra.usuario;

public class Vendedor {
    private int id, id_usuario;
    private String RG,chave_pix;

    public Vendedor(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getChave_pix() {
        return chave_pix;
    }

    public void setChave_pix(String chave_pix) {
        this.chave_pix = chave_pix;
    }
}
