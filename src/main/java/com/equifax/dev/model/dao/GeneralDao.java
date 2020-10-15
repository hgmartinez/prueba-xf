package com.equifax.dev.model.dao;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.equifax.dev.model.entity.Productos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Transactional
public class GeneralDao {

    @Autowired
    HibernateTemplate hibernateTemplate;
    @Autowired(required=false)
    Productos p;

    public List<Productos> findByHQuery(String hqlQuery, Object[] values) throws Exception {
        List<Productos> resultado = new ArrayList<>();
        List<?> items = Collections.emptyList();
        try {
            Query<?> query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hqlQuery);
            for(int i = 0; i<values.length; i++) {
                query.setParameter(i, values[i]);
            }
            items = query.list();
            for(Object o: items) {
            	p = (Productos) o;
            	resultado.add(p);
            }
        }
        catch (Exception e) {
            String message = "No se pudo ejecutar consulta";
            System.out.println(message + ": " + e.getMessage());
            throw new Exception(message);
        }
        
        return resultado;
    }
}
