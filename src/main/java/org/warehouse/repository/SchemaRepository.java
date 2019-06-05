package org.warehouse.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.warehouse.model.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;

@Component
public class SchemaRepository {

    private static final String CREATE_EXTERNAL_TABLE_QUERY = "CREATE EXTERNAL TABLE {1}_ext ({2}) USING (DATAOBJECT '/export/home/nz/{1}.csv' DELIMITER ';');";
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS {1}_tab ({2});";
    private static final String DROP_EXTERNAL_TABLE_QUERY = "DROP TABLE {1}_ext IF EXISTS;";
    private static final String DROP_TABLE_QUERY = "DROP TABLE {1}_tab IF EXISTS;";
    private static final String INSERT_EXTERNAL = "INSERT INTO {1}_tab select * from {1}_ext;";
    private static final Class[] CLASSES = new Class[]{Category.class, Location.class, Manager.class, Product.class, Sales.class, Shop.class, Transaction.class};

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createExternalTables() {
        for (Class aClass : CLASSES) {
            jdbcTemplate.execute(setParameters(CREATE_EXTERNAL_TABLE_QUERY, aClass));
        }
    }

    public void dropExternalTables() {
        for (Class aClass : CLASSES) {
            jdbcTemplate.execute(setParameters(DROP_EXTERNAL_TABLE_QUERY, aClass));
        }
    }

    public void createTables() {
        for (Class aClass : CLASSES) {
            jdbcTemplate.execute(setParameters(CREATE_TABLE_QUERY, aClass));
        }
    }

    public void dropTables() {
        for (Class aClass : CLASSES) {
            jdbcTemplate.execute(setParameters(DROP_TABLE_QUERY, aClass));
        }
    }

    public void insertExternalIntoTables() {
        for (Class aClass : CLASSES) {
            jdbcTemplate.execute(setParameters(INSERT_EXTERNAL, aClass));
        }
    }

    private String setParameters(String query, Class aClass) {
        StringBuilder properties = new StringBuilder();
        for (Field field : aClass.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            Class<?> type = field.getType();
            if (type == int.class || type == Integer.class) {
                properties.append(field.getName()).append(" int, ");
            } else if (type == String.class) {
                properties.append(field.getName()).append(" varchar(255), ");
            } else if (type == float.class || type == Float.class) {
                properties.append(field.getName()).append(" float4, ");
            } else if (type == double.class || type == Double.class) {
                properties.append(field.getName()).append(" float8, ");
            } else if (type == LocalDate.class) {
                properties.append(field.getName()).append(" date, ");
            }
        }
        properties.delete(properties.length() - 2, properties.length() - 1);
        return StringUtils.replaceEach(query, new String[]{"{1}", "{2}"},
                new String[]{aClass.getSimpleName(), properties.toString()});
    }
}
