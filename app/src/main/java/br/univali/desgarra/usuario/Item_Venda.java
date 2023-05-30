package br.univali.desgarra.usuario;

public class Item_Venda {
    private int id, id_venda, id_pelucia, id_cor,quantidade;

    public Item_Venda(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
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
