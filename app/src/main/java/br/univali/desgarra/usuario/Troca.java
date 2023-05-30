package br.univali.desgarra.usuario;

public class Troca {
    private int id, id_usuario1,id_usuario2;
    private String data_troca, status;

    public Troca(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario1() {
        return id_usuario1;
    }

    public void setId_usuario1(int id_usuario1) {
        this.id_usuario1 = id_usuario1;
    }

    public int getId_usuario2() {
        return id_usuario2;
    }

    public void setId_usuario2(int id_usuario2) {
        this.id_usuario2 = id_usuario2;
    }

    public String getData_troca() {
        return data_troca;
    }

    public void setData_troca(String data_troca) {
        this.data_troca = data_troca;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
