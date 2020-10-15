package com.equifax.dev.model.dao;

import java.util.List;

import com.equifax.dev.model.entity.Productos;

public interface ProductoDao {

	public List<Productos> findAllActiveProdByCliente(Long cliId);

}