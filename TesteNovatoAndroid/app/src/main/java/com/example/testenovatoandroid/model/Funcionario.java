package com.example.testenovatoandroid.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Funcionario implements Serializable {

    private int id = 0;
    private String nome;
    private String sobrenome;
    private String dataNascimento;
    private String cargo;
    private int nota;


    public Funcionario(String nome, String sobrenome, String dataNascimento, String cargo, int nota) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
        this.nota = nota;

    }

    public Funcionario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    public boolean validandoID() {
        return id > 0;
    }
}
