/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Aplicacao.Entidade;
import Aplicacao.Repositorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcos
 */
public abstract class DaoGenerica <T extends Entidade> implements Repositorio<T>{
   
    protected Connection conn;
    private String consultaCadastrar;
    private String consultaEditar;
    private String consultaExcluir;
    private String consultaBuscar;
    private String consultaFiltrar;
    
    private String where;
    
    DaoGenerica(){
        try{
            Conexao.iniciar();
            conn = Conexao.criarConexao();
        }catch(ClassNotFoundException ex){
            System.out.println("Driver não encontrado");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        where = "";
    }
    
    protected abstract T preencherObjeto(ResultSet resultado);
    protected abstract void preencherConsulta(PreparedStatement sql,T obj);
    protected abstract void preencherFiltros(T filtro);
    protected abstract void preencherParametros(PreparedStatement sql,T filtro);

    @Override
    public boolean cadastrar(T obj) {
        try {
            PreparedStatement sql = conn.prepareStatement(getConsultaCadastrar());  
            preencherConsulta(sql, obj);
            sql.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex+"DG Cadastrar");
        }
        return false;
    }

    @Override
    public boolean editar(T obj) {
        try {
            PreparedStatement sql = conn.prepareStatement(getConsultaEditar());  
            preencherConsulta(sql, obj);
            sql.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex+"DG editar");
        }
        return false;
    }

    @Override
    public boolean excluir(T obj) {
        try {
            PreparedStatement sql = conn.prepareStatement(getConsultaExcluir());  
            sql.setInt(1, obj.getId());
            sql.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex+"DG excluir");
        }
        return false;
    }

    @Override
    public List<T> buscar() {
        List<T> todos= new ArrayList<>();
         try {
            PreparedStatement sql = conn.prepareStatement(getConsultaBuscar());  
            ResultSet resultado= sql.executeQuery();
            
            while(resultado.next()){
                T tmp = preencherObjeto(resultado);
                todos.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex+"DG buscar");
        }
        return todos; 
    }

    @Override
    public List<T> filtrar(T filtro) {
        List<T> ret = new ArrayList<>();
        preencherFiltros(filtro);
        
        if(where.length() > 0){
            where = "WHERE" + where;
        }
        
        try {
            PreparedStatement sql = conn.prepareStatement(getConsultaFiltrar() + where);
            preencherParametros(sql, filtro);
            ResultSet resultado= sql.executeQuery();
            
            while(resultado.next()){
                T tmp = preencherObjeto(resultado);
                ret.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex+"DG buscar");
        }
        this.where = "";
        return ret;
    }
    
    protected void adicionarFiltro(String campo, String operador){
        if(where.length() > 0){
            where = where + "and";
        }
        where = where +  campo +" " + operador + " ?";
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public String getConsultaCadastrar() {
        return consultaCadastrar;
    }

    public void setConsultaCadastrar(String consultaCadastrar) {
        this.consultaCadastrar = consultaCadastrar;
    }

    public String getConsultaEditar() {
        return consultaEditar;
    }

    public void setConsultaEditar(String consultaEditar) {
        this.consultaEditar = consultaEditar;
    }

    public String getConsultaExcluir() {
        return consultaExcluir;
    }

    public void setConsultaExcluir(String consultaExcluir) {
        this.consultaExcluir = consultaExcluir;
    }

    public String getConsultaBuscar() {
        return consultaBuscar;
    }

    public void setConsultaBuscar(String consultaBuscar) {
        this.consultaBuscar = consultaBuscar;
    }

    public String getConsultaFiltrar() {
        return consultaFiltrar;
    }

    public void setConsultaFiltrar(String consultaFiltrar) {
        this.consultaFiltrar = consultaFiltrar;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }
        
}
