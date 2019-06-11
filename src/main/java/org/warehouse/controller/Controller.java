package org.warehouse.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.warehouse.dto.ObservableDto;
import org.warehouse.repository.QueryRepository;
import org.warehouse.repository.SchemaRepository;

import java.io.*;
import java.util.List;

@Component
public class Controller {

    @Autowired
    private SchemaRepository schemaRepository;

    @Autowired
    private QueryRepository queryRepository;

    @FXML
    private TableView<ObservableList<String>> tableView;

    @FXML
    private TextArea description;

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

    public void showFirstDescription() {
        description.textProperty().setValue("Shop with highest average revenue per transaction.");
    }

    public void showSecondDescription() {
        description.textProperty().setValue("For each category query returns name of the shop where this category \nhas the highest sale value.");
    }

    public void showThirdDescription() {
        description.textProperty().setValue("");
    }

    public void showFourthDescription() {
        description.textProperty().setValue("Comparision of sales value from the first and the second half of the month.");
    }

    public void showFifthDescription() {
        description.textProperty().setValue("Ten best selling products.");
    }

    public void getFirstQuery() {
        List<ObservableDto> dtos = queryRepository.firstQuery();
        showTable(dtos);
    }

    public void getSecondQuery() {
        List<ObservableDto> dtos = queryRepository.secondQuery();
        showTable(dtos);
    }

    public void getThirdQuery() {
        List<ObservableDto> dtos = queryRepository.thirdQuery();
        showTable(dtos);
    }

    public void getFourthQuery() {
        List<ObservableDto> dtos = queryRepository.fourthQuery();
        showTable(dtos);
    }

    public void getFifthQuery() {
        List<ObservableDto> dtos = queryRepository.fifthQuery();
        showTable(dtos);
    }

    public void downloadTableAsCsv() throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(tableView.getScene().getWindow());
        if (file != null) {
            saveTableToFile(file);
        }
    }

    private void showTable(List<ObservableDto> dtos) {
        String[] columns = dtos.get(0).getColumns();
        tableView.getColumns().removeIf(a -> true);
        tableView.getItems().removeIf(a -> true);
        for (int i = 0; i < columns.length; i++) {
            final int finalIdx = i;
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(columns[i]);
            column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx)));
            tableView.getColumns().add(column);
        }
        for (ObservableDto dto : dtos) {
            tableView.getItems().add(dto.toObservableList());
        }
    }

    private void saveTableToFile(File file) throws IOException {
        try (Writer writer = new BufferedWriter(new FileWriter(file))) {
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
        }
    }
}
