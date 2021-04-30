package project;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Driver {private void writeData() {String line = "";
        try {BufferedWriter writer = new BufferedWriter(new FileWriter("Beca.txt"));for (int i = 1; i <= 40; i++) {
                int size = (int)(3.1415926*i*i);
                line = Double.valueOf(String.valueOf(i))
                        + " "+ size
                        + " "+ (BigInteger.valueOf(size).isProbablePrime(100) ? "yes" : "no")
                        + " "+ (BigInteger.valueOf(size).isProbablePrime(100) ? "true" : "false")
                        + "\n";writer.write(line);} writer.close();} catch (IOException e) { e.printStackTrace();}
    }private void initAndShowGUI(Double accuracy, Double precision) {
        JFrame myFrame = new JFrame();
        myFrame.setTitle("project-part1");
        Container contentPane = myFrame.getContentPane();
        contentPane.setLayout(new GridLayout(10, 10));
        contentPane.add(new Label("the accuracy: "));
        String acc = new DecimalFormat("##.##").format(accuracy);
        contentPane.add(new Label(acc));
        String pre = new DecimalFormat("##.##").format(precision);
        contentPane.add(new Label("the preciation: "));
        contentPane.add(new Label(pre)); myFrame.pack();myFrame.setVisible(true);myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }public static void main(String args[]) {
        Driver driver = new Driver();
        driver.writeData();
        DummyPredictor dummyPredictor = new DummyPredictor();
        ArrayList<DataPoint> dataPoints = dummyPredictor.readData("Beca.txt");
        System.out.println(dummyPredictor.test(new DataPoint(Double.valueOf(130), Double.valueOf(99999), "Bad", false)));
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        driver.initAndShowGUI(dummyPredictor.getAccuracy(dataPoints), dummyPredictor.getPrecision(dataPoints));
                    }
                }
        );
    }
}
