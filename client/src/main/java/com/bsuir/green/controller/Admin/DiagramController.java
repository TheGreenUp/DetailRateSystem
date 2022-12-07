package com.bsuir.green.controller.Admin;

import com.bsuir.green.utils.DialogUtils;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class DiagramController {
    @FXML
    private PieChart pieChart;
    @FXML
    private Button backButton;
    @FXML
    private Button saveDiagramBtn;
    private static int[] numbers = new int[2];
    private static String imagePath = "diagrams/diagram" + java.time.LocalDate.now() + "jpg";

    public void getNumbers(int[] nums) throws IOException {
        numbers = nums;
        init();
    }
    public void onSaveDiagram() throws IOException {
        saveToFile(pieChart);
        onBackButton();
        DialogUtils.showOk("Диаграмма успешно сохранена!", "Успех!");
    }

    public void onBackButton(){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
    private void init() {//todo это тоже костыль
        //region Заполняем значениями
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Сертифицированы", numbers[0]),
                        new PieChart.Data("Не подлежат сертификации", numbers[1])
                );
        //endregion
        //region Значения на диаграмме
        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " ", data.pieValueProperty()
                        )
                )
        );
        //endregion
        pieChart.getData().addAll(pieChartData);
    }
    public static void saveToFile(PieChart pieChart) throws IOException {
        Scene scene = new Scene(new Group(), 595, 400);
        ((Group) scene.getRoot()).getChildren().add(pieChart);
        WritableImage image = scene.snapshot(null);
        File file = new File(imagePath);
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "PNG", file);
    }
}
