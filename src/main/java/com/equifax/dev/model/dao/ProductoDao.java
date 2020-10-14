package com.equifax.dev.model.dao;

import java.util.List;

public interface ProductoDao {

	public List<Object> findAllActiveProdByCliente(Long cliId);

}