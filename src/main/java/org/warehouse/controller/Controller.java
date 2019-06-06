package org.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.warehouse.repository.CategoryRepository;
import org.warehouse.repository.ProductRepository;
import org.warehouse.repository.QuerySpecifications;
import org.warehouse.repository.SchemaRepository;

@Component
public class Controller {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SchemaRepository schemaRepository;

    public void createExternalSchema() {
        schemaRepository.createExternalTables();
    }

    public void dropExternalSchema() {
        schemaRepository.dropExternalTables();
    }

    public void createSchema() {
        schemaRepository.createTables();
    }

    public void dropSchema() {
        schemaRepository.dropTables();
    }

    public void insertExternal() {
        schemaRepository.insertExternalIntoTables();
    }

    public void specifications() {
        productRepository.findAll(QuerySpecifications.isCategoryId(2)).forEach(System.out::println);
    }
}
