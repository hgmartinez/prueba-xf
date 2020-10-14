package com.equifax.dev.model.dao;

import com.equifax.dev.utils.DaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;

@Component
public class ProductoDaoImpl extends GeneralDao implements ProductoDao {

	@Autowired
	DaoUtils eUtils;

	@SuppressWarnings("unchecked")
	public List<Object> findAllActiveProdByCliente(Long cliId) {
		String hqlQuery = eUtils.getQByName("pdoCte.getAllActivesByCteId");
		String active = "1";
		try {
			List<Object> list = (List<Object>) findByHQuery(hqlQuery, new Object[]{cliId, active});
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Collections.EMPTY_LIST;
	}

}
