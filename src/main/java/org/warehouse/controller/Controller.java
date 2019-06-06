package org.warehouse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.warehouse.repository.ProductRepository;
import org.warehouse.repository.QuerySpecifications;
import org.warehouse.repository.SchemaRepository;
import org.warehouse.repository.ShopRepository;

@Component
public class Controller {

    private ProductRepository productRepository;

    private ShopRepository shopRepository;

    private SchemaRepository schemaRepository;

    @FXML
    private TableView tableView;

    @Autowired
    public Controller(ProductRepository productRepository, ShopRepository shopRepository, SchemaRepository schemaRepository) {
        this.productRepository = productRepository;
        this.shopRepository = shopRepository;
        this.schemaRepository = schemaRepository;
    }

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
        System.out.println(shopRepository.findShopWithBestAverageIncome());
        productRepository.findAll(QuerySpecifications.isCategoryId(2)).forEach(System.out::println);
    }
}
