/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author huyvh
 */
public interface GennericDAOInterface<T, ID> {

    List<T> findAll();

    Optional<T> findById(ID id);

    String create(T entity);

    String update(T entity);

}
