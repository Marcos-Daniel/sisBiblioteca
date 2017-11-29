/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

/**
 *
 * @author marcos
 */
public class Cliente implements Entidade {
    
    private int id;
    private String nome;
    private String telefone;
    private String cpf;
    private String email;

    public Cliente() {
        
    }

    public Cliente(String nome, String telefone, String cpf, String email) {
        this.id=0;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
    }

    public Cliente(int i, String nome, Object object, String cpf, Object object0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int getId() {
            return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
        
}
