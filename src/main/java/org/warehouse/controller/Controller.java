package org.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.warehouse.repository.CategoryRepository;
import org.warehouse.repository.SchemaRepository;

@Component
public class Controller {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SchemaRepository schemaRepository;

    public void createExternalSchema() {
        schemaRepository.createExternalTables();
    }

    public void dropExternalSchema() {
        schemaRepository.dropExternalTables();
    }
}
