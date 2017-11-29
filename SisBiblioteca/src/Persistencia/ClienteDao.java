/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;


import Aplicacao.Cliente;
import Aplicacao.ClienteRepositorio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcos
 */
public class ClienteDao extends DaoGenerica<Cliente> implements ClienteRepositorio {

    public ClienteDao() throws SQLException {
        setConsultaCadastrar("INSERT INTO usuario(nome,telefone,cpf,email)VALUES(?,?,?,?)");
        setConsultaEditar("UPDATE filial SET uf = ?,cidade = ?,bairro = ?,rua = ?,numEstabelicimento = ? WHERE id = ?");
        setConsultaExcluir("DELETE FROM filial WHERE id = ?");
        setConsultaBuscar("SELECT id,nome,telefone,cpf,email FROM usuario");
        setConsultaFiltrar("select id,uf,cidade,bairro,rua,numEstabelicimento from filial ");
    }
    
    @Override
    protected Cliente preencherObjeto(ResultSet resultado) {
        try {
            Cliente tmp = new Cliente();
            tmp.setId(resultado.getInt(1));
            tmp.setNome(resultado.getString(2));
            tmp.setTelefone(resultado.getString(3));
            tmp.setCpf(resultado.getString(4));
            tmp.setEmail(resultado.getString(5));
            return tmp;
        } catch (SQLException ex) {
            System.out.println(ex + " FILIAL DAO PREENCHER OBJETO");
        }
        
        return null;
    }

    @Override
    protected void preencherConsulta(PreparedStatement sql, Cliente obj) {
        try {
            sql.setString(1, obj.getNome());
            sql.setString(2, obj.getTelefone());
            sql.setString(3, obj.getCpf());
            sql.setString(4, obj.getEmail());
            if(obj.getId() > 0) sql.setInt(5,obj.getId());
        } catch (SQLException ex) {
            System.out.println(ex + " FILIAL DAO PREENCHER CONSULTA");
        }
    }

    @Override
    protected void preencherFiltros(Cliente filtro) {
        if (filtro.getId() > 0) {
            adicionarFiltro("id", "=");
        }
        if (filtro.getNome() != null) {
            adicionarFiltro("nome", "=");
        }
        if (filtro.getCpf()!= null) {
            adicionarFiltro("cpf", "=");
        }
    }

    @Override
    protected void preencherParametros(PreparedStatement sql, Cliente filtro) {
        try {
            int cont = 1;
            if (filtro.getId() > 0) {
                sql.setInt(cont, filtro.getId());
                cont++;
            }
            if (filtro.getNome()!= null) {
                sql.setString(cont, filtro.getNome());
                cont++;
            }
            if (filtro.getCpf()!= null) {
                sql.setString(cont, filtro.getCpf());
                cont++;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
 