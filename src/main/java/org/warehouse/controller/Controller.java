package org.warehouse.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.warehouse.dto.ShopDto;
import org.warehouse.repository.QueryRepository;
import org.warehouse.repository.SchemaRepository;

import java.io.*;

@Component
public class Controller {

    private static final String[] COL_NAMES = new String[]{"ID", "NAME", "LOCATION_ID", "MANAGER_ID"};

    @Autowired
    private SchemaRepository schemaRepository;

    @Autowired
    private QueryRepository queryRepository;

    @FXML
    private TableView<ObservableList<String>> tableView;

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

    public void getFirstQuery() {
        ShopDto shopDto = queryRepository.firstQuery();
        for (int i = 0; i < COL_NAMES.length; i++) {
            final int finalIdx = i;
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(COL_NAMES[i]);
            column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx)));
            tableView.getColumns().add(column);
        }
        tableView.getItems().add(shopDto.toObservableList());
    }

    public void getSecondQuery() {

    }

    public void downloadTableAsCsv() throws IOException {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(tableView.getScene().getWindow());
        if (file != null) {
            saveTableToFile(file);
        }
    }

    private void saveTableToFile(File file) throws IOException {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            StringBuilder columnBuilder = new StringBuilder();
            for (TableColumn<ObservableList<String>, ?> column : tableView.getColumns()) {
                columnBuilder.append(column.getText()).append(";");
            }
            columnBuilder.deleteCharAt(columnBuilder.length() - 1);
            columnBuilder.append("\n");
            writer.write(columnBuilder.toString());

            for (ObservableList<String> item : tableView.getItems()) {
                StringBuilder itemBuilder = new StringBuilder();
                for (String s : item) {
                    itemBuilder.append(s).append(";");
                }
                itemBuilder.deleteCharAt(itemBuilder.length() - 1);
                itemBuilder.append("\n");
                writer.write(itemBuilder.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            writer.flush();
            writer.close();
        }
    }
}
