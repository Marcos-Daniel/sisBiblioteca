/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentacao;


import Aplicacao.ClienteRepositorio;
import Aplicacao.LivroRepositorio;
import Persistencia.ClienteDao;
import Persistencia.LivroDao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcos
 */
public class GerenciadorDeReferencias {

    private static ClienteRepositorio daoClientes;
    private static LivroRepositorio daoLivros;

    public static ClienteRepositorio getClientes() {
        try {
            if (daoClientes == null) {
                daoClientes = new ClienteDao();
            }
            return daoClientes;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    public static LivroRepositorio getLivros() {
        try {
            if (daoLivros == null) {
                daoLivros = new LivroDao();
            }
            return daoLivros;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
