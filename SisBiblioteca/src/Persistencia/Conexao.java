/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author marcos
 */
public class Conexao {
 
    public static void iniciar() throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
    }
    public static Connection criarConexao() throws SQLException{
        return (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/dbbiblioteca","root","root");
    }
    
}
