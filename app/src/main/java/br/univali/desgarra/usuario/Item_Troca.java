package br.univali.desgarra.usuario;

public class Item_Troca {
    private int id, id_troca, id_pelucia, id_cor,quantidade;

    public Item_Troca(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_troca() {
        return id_troca;
    }

    public void setId_troca(int id_troca) {
        this.id_troca = id_troca;
    }

    public int getId_pelucia() {
        return id_pelucia;
    }

    public void setId_pelucia(int id_pelucia) {
        this.id_pelucia = id_pelucia;
    }

    public int getId_cor() {
        return id_cor;
    }

    public void setId_cor(int id_cor) {
        this.id_cor = id_cor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
