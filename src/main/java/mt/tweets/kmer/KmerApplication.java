package mt.tweets.kmer;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import mt.tweets.kmer.tweets.SearchTweets;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static javafx.collections.FXCollections.observableArrayList;

@SpringBootApplication
public class KmerApplication extends Application {

    private PieChart chart;

    private static ObservableList<PieChart.Data> data = observableArrayList();

    private static ObservableList<XYChart.Series<Integer, Integer>> lineChartData = observableArrayList();

    private static XYChart.Series<String, Number> series = new XYChart.Series<>();

    public static void main(String[] args) {

        SpringApplication.run(KmerApplication.class, args);

        launch(args);
    }


    public static XYChart.Series<String, Number> resultData() {
        SearchTweets.getTweets("Etoudi2018").entrySet().forEach(r ->
                series.getData().add(new XYChart.Data<>(r.getKey(), r.getValue())));

        return series;
    }


    private Parent createContent() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of Tweets");
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Etoudi 2018 Line Chart");
        lineChart.getData().add(resultData());
        return lineChart;
    }

   /* private Parent createContent() {
        chart = new PieChart(resultData());
        chart.setClockwise(false);
//        setupAnimation();
        return chart;
    }*/


    private void setupAnimation() {

        data.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " ", data.pieValueProperty(), " retweets"
                        )
                )
        );

        data.forEach(pieData -> {
            System.out.println(pieData.getName() + ": " + pieData.getPieValue());
            pieData.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                Bounds b1 = pieData.getNode().getBoundsInLocal();
                double newX = (b1.getWidth()) / 2 + b1.getMinX();
                double newY = (b1.getHeight()) / 2 + b1.getMinY();
                // Make sure pie wedge location is reset
                pieData.getNode().setTranslateX(0);
                pieData.getNode().setTranslateY(0);

                // Show the BoundsInLocal of the selected wedge with a rectangle
//                rectangle.setTranslateX(newX);
//                rectangle.setTranslateY(newY);
//                rectangle.setWidth(b1.getWidth());
//                rectangle.setHeight(b1.getHeight());

                // Create the animation
                TranslateTransition tt = new TranslateTransition(
                        Duration.millis(1500), pieData.getNode());
                tt.setByX(newX);
                tt.setByY(newY);
                tt.setAutoReverse(true);
                tt.setCycleCount(2);
                tt.play();
            });
        });
    }



    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setTitle("Etoudi 2018");
        primaryStage.show();
    }
}
