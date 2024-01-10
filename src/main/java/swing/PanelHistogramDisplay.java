package swing;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.Map;

public class PanelHistogramDisplay extends JPanel implements HistogramDisplay {

    @Override
    public void show(Histogram histogram) {
        add(new ChartPanel(chartOf(histogram)));
    }

    private JFreeChart chartOf(Histogram histogram) {
        return ChartFactory.createBarChart(
                histogram.Title(),
                histogram.XAxis(),
                histogram.YAxis(),
                dataset(histogram.data())
        );
    }

    private CategoryDataset dataset(Map<String, Integer> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String key : data.keySet()) {
            dataset.addValue(data.get(key), key, "");
        }
        return dataset;
    }
}
