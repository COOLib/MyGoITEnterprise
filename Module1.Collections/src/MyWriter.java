import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class MyWriter {

    private int one = 10000;
    private int two = 100000;
    private int three = 1000000;
    private int iterations = 100;
    private int rowLength = 8;
    private String[] lists = {"ArrayList", "LinkedList", "HashSet", "TreeSet"};
    private String[] checkPoint = {"", "", "", "", "", "", "", ""};
    private String[][] columnNames = {{"", "add", "get", "remove", "contains", "populate", "iter.add", "iter.remove"}};

    public void outToDisplay() {

        JFrame frame = new JFrame("Time in nanoseconds");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[][] data = getRows(one);
        String[][] secondData = getRows(two);
        String[][] thirdData = getRows(three);

        JTable names = new JTable(columnNames, checkPoint);
        JTable table = new JTable(data, checkPoint);
        JTable secondTable = new JTable(secondData, checkPoint);
        JTable thirdTable = new JTable(thirdData, checkPoint);


        arraysToFile(data, secondData, thirdData);

        JPanel panel = new JPanel();
        panel.add(names);
        panel.add(table);
        panel.add(secondTable);
        panel.add(thirdTable);
        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(620, 270));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void arraysToFile(String[][] data, String[][] secondData, String[][] thirdData) {

        File file = new File("Results.txt");
        try (FileWriter writer = new FileWriter(file)) {

            for (int i = 0; i < columnNames[0].length; i++) {
                if (columnNames[0][i].length() < 12) {
                    while (columnNames[0][i].length() < 12) {
                        columnNames[0][i] = " " + columnNames[0][i] + " ";
                    }
                }
                writer.write(columnNames[0][i]);
                writer.flush();
            }
            writer.write("\r\n");
            writer.write("Lists size is 10k");
            writer.write("\r\n");
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 8; ++j) {
                    writer.write(data[i][j]);
                    writer.flush();
                }
                writer.write("\r\n");
            }
            writer.write("Lists size is 100k");
            writer.write("\r\n");
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 8; ++j) {
                    writer.write(secondData[i][j]);
                    writer.flush();
                }
                writer.write("\r\n");
            }
            writer.write("Lists size is 1000k");
            writer.write("\r\n");
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 8; ++j) {
                    writer.write(thirdData[i][j]);
                    writer.flush();
                }
                writer.write("\r\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[][] getRows(int size) {

        Formatter formatter = new Formatter();

        String[][] data = new String[4][8];
        int rand = (int) (Math.random() * size);

        long data1[] = fillFirstRow(size, rand);
        long data2[] = fillSecondRow(size, rand);
        long data3[] = fillThirdRow(size);
        long data4[] = fillFourthRow(size);

        for (int i = 0; i < data.length; i++) {
            data[i][0] = lists[i];
        }
        for (int j = 1; j < data[0].length; j++) {
            data[0][j] = String.valueOf(formatter.format("%12d",data1[j]));
            formatter = new Formatter();
        }
        for (int j = 1; j < data[0].length; j++) {
            data[1][j] = String.valueOf(formatter.format("%12d",data2[j]));
            formatter = new Formatter();
        }
        for (int j = 1; j < data[0].length; j++) {
            data[2][j] = String.valueOf(formatter.format("%12d",data3[j]));
            formatter = new Formatter();
        }
        for (int j = 1; j < data[0].length; j++) {
            data[3][j] = String.valueOf(formatter.format("%12d",data4[j]));
            formatter = new Formatter();
        }

        return data;
    }

    private long[] fillFirstRow(int size, int index) {

        List<Integer> first = new ArrayList<>();
        long[] data = new long[rowLength];

        data[5] = ListComparator.arrayListPopulateTime(first, iterations, size);
        ListComparator.populate(first, size);
        data[1] = ListComparator.addingTime(first, iterations, index);
        data[2] = ListComparator.indexGettingTime(first, iterations, index);
        data[3] = (ListComparator.removingTime(first, iterations, index));
        data[4] = ListComparator.containingTime(first, iterations, index);
        data[6] = ListComparator.iterAddingTime(first, iterations, index);
        data[7] = ListComparator.iterRemovingTime(first, iterations);

        return data;
    }

    private long[] fillSecondRow(int size, int index) {

        long[] data = new long[rowLength];
        List<Integer> second = new LinkedList<>();
        data[5] = ListComparator.linkedListPopulateTime(second, iterations, size);
        ListComparator.populate(second, size);
        data[1] = ListComparator.addingTime(second, iterations, index);
        data[2] = ListComparator.indexGettingTime(second, iterations, index);
        data[3] = ListComparator.removingTime(second, iterations, index);
        data[4] = ListComparator.containingTime(second, iterations, index);
        data[6] = ListComparator.iterAddingTime(second, iterations, index);
        data[7] = ListComparator.iterRemovingTime(second, iterations);

        return data;
    }

    private long[] fillThirdRow(int size) {

        long[] data = new long[rowLength];

        Set<Integer> third = new HashSet<>();
        SetComparator.populate(third, size);
        data[1] = SetComparator.addingTime(third, iterations, size + 5);
        data[3] = SetComparator.removingTime(third, iterations, size / 2);
        data[4] = SetComparator.containingTime(third, iterations, size / 2);
        data[5] = SetComparator.hashPopulateTime(third, iterations, size);

        return data;
    }

    private long[] fillFourthRow(int size) {

        long[] data = new long[rowLength];

        Set<Integer> fourth = new TreeSet<>();
        SetComparator.populate(fourth, size);
        data[1] = SetComparator.addingTime(fourth, iterations, size + 5);
        data[3] = SetComparator.removingTime(fourth, iterations, size / 2);
        data[4] = SetComparator.containingTime(fourth, iterations, size / 2);
        data[5] = SetComparator.treePopulateTime(fourth, iterations, size);

        return data;
    }
}
