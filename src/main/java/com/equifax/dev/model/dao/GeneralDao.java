package com.equifax.dev.model.dao;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Component
@Transactional
public class GeneralDao {

    @Autowired
    HibernateTemplate hibernateTemplate;

    public List<?> findByHQuery(String hqlQuery, Object[] values) throws Exception {
        List<?> items = Collections.emptyList();
        try {
            Query<?> query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hqlQuery);
            for(int i = 0; i<values.length; i++) {
                query.setParameter(i, values[i]);
            }
            items = query.list();
        }
        catch (Exception e) {
            String message = "No se pudo ejecutar consulta";
            System.out.println(message + ": " + e.getMessage());
            throw new Exception(message);
        }
        return items;
    }
}
