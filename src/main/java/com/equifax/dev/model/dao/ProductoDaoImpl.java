package com.equifax.dev.model.dao;

import com.equifax.dev.model.entity.Productos;
import com.equifax.dev.utils.DaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductoDaoImpl extends GeneralDao implements ProductoDao {

	@Autowired
	DaoUtils eUtils;

	@SuppressWarnings("unchecked")
	public List<Productos> findAllActiveProdByCliente(Long cliId) {
		String hqlQuery = eUtils.getQByName("pdoCte.getAllActivesByCteId");
		String active = "i";
		List<Productos> list = new ArrayList<>();
		try {
			list = (List<Productos>) findByHQuery(hqlQuery, new Object[]{cliId, active});
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Collections.EMPTY_LIST;
	}

}
