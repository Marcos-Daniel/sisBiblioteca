/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Aplicacao.Livro;
import Aplicacao.LivroRepositorio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcos
 */
public class LivroDao extends DaoGenerica<Livro> implements LivroRepositorio {

    public LivroDao() {
        setConsultaCadastrar("INSERT INTO filial(uf,cidade,bairro,rua,numEstabelicimento)VALUES(?,?,?,?,?)");
        setConsultaEditar("UPDATE filial SET uf = ?,cidade = ?,bairro = ?,rua = ?,numEstabelicimento = ? WHERE id = ?");
        setConsultaExcluir("DELETE FROM filial WHERE id = ?");
        setConsultaBuscar("SELECT id,uf,cidade,bairro,rua,numEstabelicimento FROM filial");
        setConsultaFiltrar("select id,uf,cidade,bairro,rua,numEstabelicimento from filial ");
    }
    
    @Override
    protected Livro preencherObjeto(ResultSet resultado) {
        try {
            Livro tmp = new Livro();
            tmp.setId(resultado.getInt(1));
            tmp.setTitulo(resultado.getString(2));
            tmp.setAutor(resultado.getString(3));
            return tmp;
        } catch (SQLException ex) {
            System.out.println(ex + " FILIAL DAO PREENCHER OBJETO");
        }
        
        return null;
    }

    @Override
    protected void preencherConsulta(PreparedStatement sql, Livro obj) {
        try {
            sql.setString(1, obj.getTitulo());
            sql.setString(2, obj.getAutor());
            if(obj.getId() > 0) sql.setInt(3,obj.getId());
        } catch (SQLException ex) {
            System.out.println(ex + " FILIAL DAO PREENCHER CONSULTA");
        }
    }

    @Override
    protected void preencherFiltros(Livro filtro) {
        if (filtro.getId() > 0) {
            adicionarFiltro("id", "=");
        }
        if (filtro.getTitulo()!= null) {
            adicionarFiltro("nome", "=");
        }
    }

    @Override
    protected void preencherParametros(PreparedStatement sql, Livro filtro) {
        try {
            int cont = 1;
            if (filtro.getId() > 0) {
                sql.setInt(cont, filtro.getId());
                cont++;
            }
            if (filtro.getTitulo()!= null) {
                sql.setString(cont, filtro.getTitulo());
                cont++;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
