/*
 * Programación III - Gráfico de Linea
 */
package ProgramacionIII.util;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 *  Gráfico de Línea
 * 
 * @author Mariano A. Fernandez <fernandez.mariano.a@gmail.com>
 */
public class LineChart extends JFrame {

    private static final long serialVersionUID = 1L;

    public LineChart(String p_applicationTitle, String p_chartTitle, int[] p_valores) {
        super(p_applicationTitle);
        // This will create the dataset
        XYDataset dataset = createDataset(p_valores);
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset, p_chartTitle, false);
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
        // add it to our application
        setContentPane(chartPanel);

    }
    
    public LineChart(String p_applicationTitle, String p_chartTitle, int[][] p_valores) {
        super(p_applicationTitle);
        // This will create the dataset
        XYDataset dataset = createDataset(p_valores);
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset, p_chartTitle, true);
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
        // add it to our application
        setContentPane(chartPanel);

    }

    private XYDataset createDataset(int[] p_valores) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(this.createSerie("Default", p_valores));
        return dataset;
    }
    
    private XYDataset createDataset(int[][] p_valores) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        for(int v_idx=0; v_idx < p_valores.length; v_idx++) {
            dataset.addSeries(this.createSerie("Serie " + v_idx, p_valores[v_idx]));
        }
        return dataset;
    }
    
    private XYSeries createSerie(String v_label, int[] p_valores) {
        XYSeries v_serie = new XYSeries(v_label);
        for(int v_idx=0; v_idx<p_valores.length; v_idx++) {
            v_serie.add(v_idx, p_valores[v_idx]);
        }
        return v_serie;
    }

    private JFreeChart createChart(XYDataset dataset, String title, boolean p_incl_legend) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                title, // chart title
                "Ejecución",
                "miliseg.",
                dataset, // data
                PlotOrientation.VERTICAL,
                p_incl_legend, // include legend
                true,
                false
        );

        XYPlot v_linePlot = (XYPlot)chart.getPlot();
        NumberAxis domain = (NumberAxis) v_linePlot.getDomainAxis();
        domain.setVerticalTickLabels(true);
        
        return chart;

    }

    public void showChart() {
        this.pack();
        this.setVisible(true);
    }
}
