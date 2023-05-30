package br.univali.desgarra.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import br.univali.desgarra.usuario.Cliente;
import br.univali.desgarra.usuario.Pelucia;
import br.univali.desgarra.usuario.Vendedor;

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private static final String DATABASE_NAME = "desgarra";
    private static final String TABLE_CIDADE = "cidade";
    private static final String TABLE_CLIENTE = "cliente";
    private static final String TABLE_COR = "cor";
    private static final String TABLE_DOACAO = "doacao";
    private static final String TABLE_ENDERECO = "endereco";
    private static final String TABLE_ESTADO = "estado";
    private static final String TABLE_INSTITUICAO = "instituicao";
    private static final String TABLE_ITEM_DOACAO = "item_doacao";
    private static final String TABLE_ITEM_TROCA = "item_troca";
    private static final String TABLE_ITEM_VENDA = "item_venda";
    private static final String TABLE_PELUCIA = "pelucia";
    private static final String TABLE_TEFEFONE = "telefone";
    private static final String TABLE_TROCA = "troca";
    private static final String TABLE_USUARIO = "usuario";
    private static final String TABLE_VENDA = "venda";
    private static final String TABLE_VENDEDOR = "vendedor";

    private static final String CREATE_TABLE_CIDADE = "CREATE TABLE " + TABLE_CIDADE + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100), " +
            "id_estado INTEGER, " +
            "CONSTRAINT fk_estado FOREIGN KEY (id_estado) REFERENCES estado (id))";

    private static final String CREATE_TABLE_CLIENTE = "CREATE TABLE " + TABLE_CLIENTE + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_usuario INTEGER, " +
            "CPF VARCHAR(14), " +
            "data_nascimento DATE, " +
            "CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id))";

    private static final String CREATE_TABLE_COR = "CREATE TABLE " + TABLE_COR + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(50))";

    private static final String CREATE_TABLE_DOACAO = "CREATE TABLE " + TABLE_DOACAO + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_usuario INTEGER, " +
            "id_instituicao INTEGER, " +
            "data_entrega TIMESTAMP, " +
            "status BIT, " +
            "CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id), " +
            "CONSTRAINT fk_instituicao FOREIGN KEY (id_instituicao) REFERENCES instituicao (id))";

    private static final String CREATE_TABLE_ENDERECO = "CREATE TABLE " + TABLE_ENDERECO + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_cidade INTEGER, " +
            "longradouro VARCHAR(30), " +
            "numero SMALLINT, " +
            "bairro VARCHAR(30), " +
            "cep VARCHAR(10), " +
            "CONSTRAINT fk_cidade FOREIGN KEY (id_cidade) REFERENCES cidade (id))";

    private static final String CREATE_TABLE_ESTADO = "CREATE TABLE " + TABLE_ESTADO + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(50), " +
            "UF VARCHAR(2))";

    private static final String CREATE_TABLE_INSTITUICAO = "CREATE TABLE " + TABLE_INSTITUICAO + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_endereco INTEGER, " +
            "nome VARCHAR(100), " +
            "CNPJ VARCHAR(18), " +
            "CONSTRAINT fk_endereco FOREIGN KEY (id_endereco) REFERENCES endereco (id))";

    private static final String CREATE_TABLE_ITEM_DOACAO = "CREATE TABLE " + TABLE_ITEM_DOACAO + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_pelucia INTEGER, " +
            "id_doacao INTEGER, " +
            "id_cor INTEGER, " +
            "quantidade INTEGER, " +
            "CONSTRAINT fk_pelucia FOREIGN KEY (id_pelucia) REFERENCES pelucia (id), " +
            "CONSTRAINT fk_doacao FOREIGN KEY (id_doacao) REFERENCES doacao (id), " +
            "CONSTRAINT fk_cor FOREIGN KEY (id_cor) REFERENCES cor (id))";

    private static final String CREATE_TABLE_ITEM_TROCA = "CREATE TABLE " + TABLE_ITEM_TROCA + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_pelucia INTEGER, " +
            "id_troca INTEGER, " +
            "id_cor INTEGER, " +
            "quantidade INTEGER, " +
            "CONSTRAINT fk_pelucia FOREIGN KEY (id_pelucia) REFERENCES pelucia (id), " +
            "CONSTRAINT fk_troca FOREIGN KEY (id_troca) REFERENCES troca (id), " +
            "CONSTRAINT fk_cor FOREIGN KEY (id_cor) REFERENCES cor (id))";

    private static final String CREATE_TABLE_ITEM_VENDA = "CREATE TABLE " + TABLE_ITEM_VENDA + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_pelucia INTEGER, " +
            "id_venda INTEGER, " +
            "id_cor INTEGER, " +
            "quantidade INTEGER, " +
            "CONSTRAINT fk_pelucia FOREIGN KEY (id_pelucia) REFERENCES pelucia (id), " +
            "CONSTRAINT fk_venda FOREIGN KEY (id_venda) REFERENCES venda (id), " +
            "CONSTRAINT fk_cor FOREIGN KEY (id_cor) REFERENCES cor (id))";

    private static final String CREATE_TABLE_PELUCIA = "CREATE TABLE " + TABLE_PELUCIA + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(50), " +
            "valor DECIMAL(6,2), " +
            "descricao VARCHAR(300), " +
            "tamanho DECIMAL,"+
            "data_aquisicao DATE)";

    private static final String CREATE_TABLE_TELEFONE = "CREATE TABLE " + TABLE_TEFEFONE + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_usuario INTEGER, " +
            "numero VARCHAR(15), " +
            "tipo TINYINT, " +
            "CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id))";

    private static final String CREATE_TABLE_TROCA = "CREATE TABLE " + TABLE_TROCA + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_usuairo1 INTEGER, " +
            "id_usuairo2 INTEGER, " +
            "data_troca TIMESTAMP, " +
            "status BIT, " +
            "CONSTRAINT fk_usuairo1 FOREIGN KEY (id_usuairo1) REFERENCES usuairo (id), " +
            "CONSTRAINT fk_usuairo2 FOREIGN KEY (id_usuairo2) REFERENCES usuairo (id))";

    private static final String CREATE_TABLE_USUARIO = "CREATE TABLE " + TABLE_USUARIO + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_endereco INTEGER, " +
            "nome VARCHAR(50), " +
            "email VARCHAR(100), " +
            "senha VARCHAR(16), " +
            "CONSTRAINT fk_endereco FOREIGN KEY (id_endereco) REFERENCES endereco (id))";

    private static final String CREATE_TABLE_VENDA = "CREATE TABLE " + TABLE_VENDA + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_usuairo1 INTEGER, " +
            "id_usuairo2 INTEGER, " +
            "data_pedido DATE, " +
            "valor_total DECIMAL (10,2), " +
            "desconto DECIMAL(10,2), " +
            "forma_pagamento TINYINT(1), " +
            "status BIT, " +
            "CONSTRAINT fk_usuairo1 FOREIGN KEY (id_usuairo1) REFERENCES usuairo (id), " +
            "CONSTRAINT fk_usuairo2 FOREIGN KEY (id_usuairo2) REFERENCES usuairo (id))";

    private static final String CREATE_TABLE_VENDEDOR = "CREATE TABLE " + TABLE_VENDEDOR + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_usuairo INTEGER, " +
            "RG VARCHAR(9), " +
            "chave_pix VARCHAR(40), " +
            "CONSTRAINT fk_usuairo FOREIGN KEY (id_usuairo) REFERENCES usuairo (id))";


    private static final String DROP_TABLE_CIDADE = "DROP TABLE IF EXISTS " + TABLE_CIDADE;
    private static final String DROP_TABLE_CLIENTE = "DROP TABLE IF EXISTS " + TABLE_CLIENTE;
    private static final String DROP_TABLE_COR = "DROP TABLE IF EXISTS " + TABLE_COR;
    private static final String DROP_TABLE_DOACAO = "DROP TABLE IF EXISTS " + TABLE_DOACAO;
    private static final String DROP_TABLE_ENDERECO = "DROP TABLE IF EXISTS " + TABLE_ENDERECO;
    private static final String DROP_TABLE_ESTADO = "DROP TABLE IF EXISTS " + TABLE_ESTADO;
    private static final String DROP_TABLE_INSTITUICAO = "DROP TABLE IF EXISTS " + TABLE_INSTITUICAO;
    private static final String DROP_TABLE_ITEM_DOACAO = "DROP TABLE IF EXISTS " + TABLE_ITEM_DOACAO;
    private static final String DROP_TABLE_ITEM_TROCA = "DROP TABLE IF EXISTS " + TABLE_ITEM_TROCA;
    private static final String DROP_TABLE_ITEM_VENDA = "DROP TABLE IF EXISTS " + TABLE_ITEM_VENDA;
    private static final String DROP_TABLE_PELUCIA = "DROP TABLE IF EXISTS " + TABLE_PELUCIA;
    private static final String DROP_TABLE_TELEFONE = "DROP TABLE IF EXISTS " + TABLE_TEFEFONE;
    private static final String DROP_TABLE_TROCA = "DROP TABLE IF EXISTS " + TABLE_TROCA;
    private static final String DROP_TABLE_USUARIO = "DROP TABLE IF EXISTS " + TABLE_USUARIO;
    private static final String DROP_TABLE_VENDA = "DROP TABLE IF EXISTS " + TABLE_VENDA;
    private static final String DROP_TABLE_VENDEDOR = "DROP TABLE IF EXISTS " + TABLE_VENDEDOR;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CIDADE);
        db.execSQL(CREATE_TABLE_CLIENTE);
        db.execSQL(CREATE_TABLE_COR);
        db.execSQL(CREATE_TABLE_DOACAO);
        db.execSQL(CREATE_TABLE_ENDERECO);
        db.execSQL(CREATE_TABLE_ESTADO);
        db.execSQL(CREATE_TABLE_INSTITUICAO);
        db.execSQL(CREATE_TABLE_ITEM_DOACAO);
        db.execSQL(CREATE_TABLE_ITEM_TROCA);
        db.execSQL(CREATE_TABLE_ITEM_VENDA);
        db.execSQL(CREATE_TABLE_PELUCIA);
        db.execSQL(CREATE_TABLE_TELEFONE);
        db.execSQL(CREATE_TABLE_TROCA);
        db.execSQL(CREATE_TABLE_USUARIO);
        db.execSQL(CREATE_TABLE_VENDA);
        db.execSQL(CREATE_TABLE_VENDEDOR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_CIDADE);
        db.execSQL(DROP_TABLE_CLIENTE);
        db.execSQL(DROP_TABLE_COR);
        db.execSQL(DROP_TABLE_DOACAO);
        db.execSQL(DROP_TABLE_ENDERECO);
        db.execSQL(DROP_TABLE_ESTADO);
        db.execSQL(DROP_TABLE_INSTITUICAO);
        db.execSQL(DROP_TABLE_ITEM_DOACAO);
        db.execSQL(DROP_TABLE_ITEM_TROCA);
        db.execSQL(DROP_TABLE_ITEM_VENDA);
        db.execSQL(DROP_TABLE_PELUCIA);
        db.execSQL(DROP_TABLE_TELEFONE);
        db.execSQL(DROP_TABLE_TROCA);
        db.execSQL(DROP_TABLE_USUARIO);
        db.execSQL(DROP_TABLE_VENDA);
        db.execSQL(DROP_TABLE_VENDEDOR);
        onCreate(db);
    }

    public void closeDBConnection() {
        db.close();
    }

    /* Início CRUD Cliente */
    public long createCliente (Cliente c) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id_usuario",c.getId_usuario());
        cv.put("CPF", c.getCPF());
        cv.put("data_nascimento", c.getData_nascimento());
        long id = db.insert(TABLE_CLIENTE, null, cv);
        db.close();
        return id;
    }

    public long updateCliente (Cliente c) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("CPF", c.getCPF());
        cv.put("data_nascimento", c.getData_nascimento());
        long id = db.update(TABLE_CLIENTE, cv, "_id = ?", new String[]{String.valueOf(c.getId())});
        db.close();
        return id;
    }

    public long deleteCliente (Cliente c) {
        db = this.getWritableDatabase();
        long id = db.delete(TABLE_CLIENTE, "_id = ?", new String[]{String.valueOf(c.getId())});
        db.close();
        return id;
    }

    public Cliente getByIdCliente (int id) {
        db = this.getReadableDatabase();
        String[] columns = {"_id", "CPF", "id_usuario","data_nascimento"};
        Cursor data = db.query(TABLE_CLIENTE, columns, "_id = ?", new String[]{String.valueOf(id)}, null, null, null);
        data.moveToFirst();
        Cliente c = new Cliente();
        c.setId(data.getInt(0));
        c.setCPF(data.getString(1));
        c.setId_usuario(data.getInt(2));
        c.setData_nascimento(data.getString(3));
        data.close();
        db.close();
        return c;
    }

    public Cursor getAllCliente () {
        db = this.getReadableDatabase();
        String[] columns = {"_id", "CPF", "data_nascimento"};
        return db.query(TABLE_CLIENTE, columns, null, null, null, null, null);
    }

    public void getAllCPFCliente (ArrayList<Integer> listClienteId, ArrayList<String> listClienteCPF) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"_id", "CPF"};
        Cursor data = db.query(TABLE_CLIENTE, columns, null, null, null,
                null, "CPF");
        while (data.moveToNext()) {
            int idColumnIndex = data.getColumnIndex("_id");
            listClienteId.add(Integer.parseInt(data.getString(idColumnIndex)));
            int cpfColumnIndex = data.getColumnIndex("CPF");
            listClienteCPF.add(data.getString(cpfColumnIndex));
        }
        db.close();
    }
    /* Fim CRUD Cliente */

    /* Início CRUD Vendedor */
    public long createVendedor(Vendedor v) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("RG", v.getRG());
        cv.put("chave_pix",v.getChave_pix());
        long id = db.insert(TABLE_VENDEDOR, null, cv);
        db.close();
        return id;
    }

    public long updateVendedor(Vendedor v) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id_usuario",v.getId_usuario());
        cv.put("RG", v.getRG());
        cv.put("chave_pix",v.getChave_pix());
        long rows = db.update(TABLE_VENDEDOR, cv, "_id = ?", new String[]{String.valueOf(v.getId())});
        db.close();
        return rows;
    }

    public long deleteVendedor(Vendedor v) {
        db = this.getWritableDatabase();
        long rows = db.delete(TABLE_VENDEDOR, "_id = ?", new String[]{String.valueOf(v.getId())});
        db.close();
        return rows;
    }

    public Vendedor getByIdVendedor (int id) {
        db = this.getWritableDatabase();
        String[] columns = {"_id", "RG","id_usuario", "chave_pix"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_VENDEDOR, columns, "_id = ?", args, null,
                null, null);
        data.moveToFirst();
        Vendedor v = new Vendedor();
        v.setId(data.getInt(0));
        v.setRG(data.getString(1));
        v.setId_usuario(data.getInt(2));
        v.setChave_pix(data.getString(3));
        data.close();
        db.close();
        return v;
    }

    public Cursor getAllVendedor () {
        db = this.getWritableDatabase();
        String[] columns = {"_id", "RG", "chave_pix"};
        return db.query(TABLE_VENDEDOR, columns, null, null, null,
                null, "RG");
    }

    public void getAllNameVendedor (ArrayList<Integer> listVendedorId, ArrayList<String> listVendedorRG) {
        db = this.getWritableDatabase();
        String[] columns = {"_id", "nome"};
        Cursor data = db.query(TABLE_CLIENTE, columns, null, null, null,
                null, "nome");
        while (data.moveToNext()) {
            int idColumnIndex = data.getColumnIndex("_id");
            listVendedorId.add(Integer.parseInt(data.getString(idColumnIndex)));
            int rgColumnIndex = data.getColumnIndex("RG");
            listVendedorRG.add(data.getString(rgColumnIndex));
        }
        db.close();
    }
    /* Fim CRUD Vendedor */

    /* Início CRUD Bebê */
    public long createPelucia(Pelucia p) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", p.getNome());
        cv.put("valor", p.getValor());
        cv.put("descricao", p.getDescricao());
        cv.put("tamanho", p.getTamanho());
        cv.put("data_aquisicao", p.getData_aquisicao());
        long id = db.insert(TABLE_PELUCIA, null, cv);
        db.close();
        return id;
    }

    public long updatePelucia(Pelucia p) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", p.getNome());
        cv.put("valor", p.getValor());
        cv.put("descricao", p.getDescricao());
        cv.put("tamanho", p.getTamanho());
        cv.put("data_aquisicao", p.getData_aquisicao());
        long rows = db.update(TABLE_PELUCIA, cv, "_id = ?", new String[]{String.valueOf(p.getId())});
        db.close();
        return rows;
    }

    public long deletePelucia(Pelucia b) {
        db = this.getWritableDatabase();
        long rows = db.delete(TABLE_PELUCIA, "_id = ?", new String[]{String.valueOf(b.getId())});
        db.close();
        return rows;
    }

    public Pelucia getByIdPelucia (int id) {
        db = this.getWritableDatabase();
        String[] columns = {"_id", "nome", "valor", "descricao", "tamanho", "data_aquisicao"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_PELUCIA, columns, "_id = ?", args, null,
                null, null);
        data.moveToFirst();
        Pelucia p = new Pelucia();
        p.setId(data.getInt(0));
        p.setNome(data.getString(1));
        p.setValor(data.getFloat(2));
        p.setDescricao(data.getString(3));
        p.setTamanho(data.getFloat(4));
        p.setData_aquisicao(data.getString(5));
        data.close();
        db.close();
        return p;
    }

    public Cursor getAllPelucia () {
        db = this.getWritableDatabase();
        String[] columns = {"_id", "nome", "valor"};
        return db.query(TABLE_PELUCIA, columns, null, null, null,
                null, "nome");
    }
    /* Fim CRUD Pelucia */
}
