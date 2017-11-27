/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import java.util.List;



/**
 *
 * @author marcos
 */
public interface Repositorio<T extends Entidade> {
    
    public boolean cadastrar(T obj);
    public boolean editar(T obj);
    public boolean excluir(T obj);
    public List<T> buscar();
    public List<T> filtrar(T filtro);
    
}
