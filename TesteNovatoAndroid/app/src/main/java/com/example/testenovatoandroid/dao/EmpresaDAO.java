package com.example.testenovatoandroid.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.testenovatoandroid.model.Funcionario;
import java.util.ArrayList;

public class EmpresaDAO {

    private BancoListaOpenHelper openHelper;
    private SQLiteDatabase db;

    public EmpresaDAO(Context context) {
        openHelper = new BancoListaOpenHelper(context, "empresa", null, 1);
    }

    public void abrir() {
        db = openHelper.getWritableDatabase();
    }

    public void fechar() {
        if (db != null) {
            db.close();
        }
    }

    public void insert(Funcionario funcionario) {
        ContentValues values = new ContentValues();
        values.put("nome", funcionario.getNome());
        values.put("sobrenome", funcionario.getSobrenome());
        values.put("datanascimento", funcionario.getDataNascimento());
        values.put("cargo", funcionario.getCargo());
        values.put("nota", funcionario.getNota());
        abrir();
        db.insert("empresa", null, values);
        fechar();

    }

    public long totalFuncionario() {
        Cursor cursor = db.rawQuery("SELECT count(*) as quantidade FROM empresa", null);
        int quantidadeFuncionario = 0;
        while (cursor.moveToNext()) {
            quantidadeFuncionario = cursor.getInt(cursor.getColumnIndex("quantidade"));
        }
        cursor.close();
        return quantidadeFuncionario;
    }

    public long mediaNota() {
        Long totalDeFuncionario = totalFuncionario();
        Cursor cursor = db.rawQuery("SELECT SUM(nota) as total FROM empresa", null);
        int total = 0;
        int media =0;
        while (cursor.moveToNext()) {
            total = cursor.getInt(cursor.getColumnIndex("total"));
        }
        cursor.close();
          if(total>0 && totalDeFuncionario>0){
              media = (int) (total / totalDeFuncionario);}
        return media;
    }

    public long totalFuncionarioComercial() {
        Cursor cursor = db.rawQuery("SELECT count(*) as quantidade FROM empresa where cargo = 'Comercial'", null);
        int quantidade = 0;
        while (cursor.moveToNext()) {
            quantidade = cursor.getInt(cursor.getColumnIndex("quantidade"));
        }
        cursor.close();
        return quantidade;
    }

    public long totalFuncionarioDesenvolvimento() {
        Cursor cursor = db.rawQuery("SELECT COUNT(*) as quantidadeDesenvolvimento FROM empresa where cargo ='Desenvolvimento'", null);
        int quantidadeDesenvolvimento = 0;
        while (cursor.moveToNext()) {
            quantidadeDesenvolvimento = cursor.getInt(cursor.getColumnIndex("quantidadeDesenvolvimento"));
        }
        cursor.close();
        return quantidadeDesenvolvimento;
    }

    public long totalFuncionarioSuporte() {
        Cursor cursor = db.rawQuery("SELECT COUNT(*) as quantidadeSuporte FROM empresa where cargo ='Suporte T.'", null);
        long quantidadeSuporte = 0;
        while (cursor.moveToNext()) {
            quantidadeSuporte = cursor.getInt(cursor.getColumnIndex("quantidadeSuporte"));
        }
        cursor.close();
        return quantidadeSuporte;
    }

    public long totalFuncionarioAdministrativo() {
        Cursor cursor = db.rawQuery("SELECT COUNT(*) as quantidadeAdministrativo FROM empresa where cargo ='Administrativo'", null);
        long quantidadeAdministrativo = 0;
        while (cursor.moveToNext()) {
            quantidadeAdministrativo = cursor.getInt(cursor.getColumnIndex("quantidadeAdministrativo"));
        }
        cursor.close();
        return quantidadeAdministrativo;
    }

    public ArrayList<Funcionario> selectAll() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        abrir();
        Cursor cursor = db.query("empresa", null, null, null, null, null, "nome");

        while (cursor.moveToNext()) {
            Funcionario funcionario = new Funcionario();
            funcionario.setId(cursor.getInt(cursor.getColumnIndex("id")));
            funcionario.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            funcionario.setSobrenome(cursor.getString(cursor.getColumnIndex("sobrenome")));
            funcionario.setDataNascimento(cursor.getString(cursor.getColumnIndex("datanascimento")));
            funcionario.setCargo(cursor.getString(cursor.getColumnIndex("cargo")));
            funcionario.setNota(cursor.getInt(cursor.getColumnIndex("nota")));
            funcionarios.add(funcionario);
        }
        cursor.close();
        return funcionarios;
    }

    public void delete(Funcionario funcionario){
        abrir();
        int registro = db.delete("empresa", "id = "+ funcionario.getId(),null);
        fechar();
    }


}
