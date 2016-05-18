package ua.goit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.actions.Action;
import ua.goit.actions.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.*;
import java.util.List;


public class RemakingText {

    private JPanel panel;
    private JFrame frame;
    private JTextArea ta;
    private JFrame history;
    private JTextArea area;
    private JPanel historyPanel;

    private Map<String, Action> actions = new HashMap<>();
    private List<Calculation> calculations = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(RemakingText.class);
    private File historyFile;

    private boolean isNeedLog = false;

    private void fillMap() {

        actions.put("+", new AddingAction());
        actions.put("-", new SubtractionAction());
        actions.put("*", new MultiplyingAction());
        actions.put("/", new DivisionAction());
        actions.put("sqrt", new SquareRootAction());
        actions.put("pow", new PowerAction());
        actions.put("!", new FactorialAction());
        actions.put("log", new LogarithmAction());
    }

    private Calculator calculator = new SpecificCalculator(actions);

    public void go() {

        fillMap();
        setUpCalcWindow();
    }

    public void setUpCalcWindow() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel back = new JPanel(layout);
        back.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        back.setBackground(Color.DARK_GRAY);

        Box buttonBox = new Box(BoxLayout.X_AXIS);

        JButton start = new JButton("Open");
        start.addActionListener(new MyStartListener());
        buttonBox.add(start);

        JButton going = new JButton("Get Result!");
        going.addActionListener(new MyGoListener());
        buttonBox.add(going);

        JButton save = new JButton("Save");
        save.addActionListener(new MySaveListener());
        buttonBox.add(save);

        JButton history = new JButton("History");
        history.addActionListener(new MyHistoryListener());
        buttonBox.add(history);

        JCheckBox logBox = new JCheckBox("Add logging time");
        logBox.addItemListener(new MyItemListener());

        panel = new JPanel();
        back.add(panel);

        ta = new JTextArea();
        JScrollPane pane = new JScrollPane(ta);
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        back.add(BorderLayout.CENTER, pane);
        back.add(BorderLayout.SOUTH, buttonBox);
        back.add(BorderLayout.NORTH, logBox);

        frame.getContentPane().add(back);
        frame.setBounds(600, 400, 330, 300);
        frame.setVisible(true);
    }

    public class MyGoListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {
                String str = ta.getText();
                Calculation calculation = new Calculation();

                LOGGER.info("Getting formula's answer...");
                String result = calculator.getAnswer(str);
                LOGGER.info("Answer is received");

                calculation.setFormula(str);
                calculation.setAnswer(result);
                calculation.setDate(new Date());

                calculations.add(calculation);
                setHistoryFile();

                ta.setText("");

                if (isNeedLog) {

                    ta.append(String.valueOf(calculation.getDate()));
                    ta.append("\n");
                    ta.append(result);
                } else {
                    ta.append(result);
                }
            } catch (Exception ex) {

                if (isNeedLog) {
                    LOGGER.warn("You wrote the incorrect formula!");
                }
                ta.setText("Please, write the correct formula \ninstead this message!");
            }
        }
    }


    public class MyStartListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            JFileChooser dialog = new JFileChooser();
            dialog.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
            dialog.showOpenDialog(frame);
            File file = dialog.getSelectedFile();

            LOGGER.info("Opening a file to read a formula...");

            try (FileInputStream inF = new FileInputStream(file)) {

                BufferedReader an = new BufferedReader(new InputStreamReader(inF));
                ta.setText("");
                String str;

                for (int i = 0; i < file.length(); i++) {
                    str = an.readLine();

                    if (str != null) {
                        ta.append(str + "\n");
                    } else {
                        break;
                    }
                }

                LOGGER.info("Successfully read.");
            } catch (Exception ex) {

                JOptionPane.showMessageDialog(frame, "Any text file was not chosen!");
                LOGGER.warn("There is no any file was read.");
            }

        }
    }

    public class MySaveListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            JFileChooser dialog = new JFileChooser();
            dialog.showSaveDialog(frame);
            File file = dialog.getSelectedFile();
            dialog.setVisible(true);

            LOGGER.info("Saving result to file...");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(ta.getText());

                LOGGER.info("Successfully saved.");
            } catch (Exception ex) {

                JOptionPane.showMessageDialog(frame, "Any text file was not chosen to save result!");
                LOGGER.warn("Saving was interrupted.");
            }
        }
    }

    public class MyHistoryListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            setHistoryPanel();

            LOGGER.info("Opening the operations history.");

            int i = 1;
            for (Calculation calc : calculations) {
                if (isNeedLog) {
                    area.append("[" + i + "]     ");
                    area.append(String.valueOf(calc.getDate()));
                    area.append("\n");
                } else {
                    area.append("[" + i + "]     ");
                }
                area.append("         " + calc.getFormula() + "         = " + calc.getAnswer() + '\n');
                i++;
            }
        }
    }

    private void setHistoryPanel() {

        history = new JFrame("History");
        history.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel back = new JPanel(layout);
        back.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        back.setBackground(Color.DARK_GRAY);

        historyPanel = new JPanel();
        back.add(historyPanel);

        area = new JTextArea();
        JScrollPane pane = new JScrollPane(area);
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        back.add(BorderLayout.CENTER, pane);

        history.getContentPane().add(back);
        history.setBounds(550, 350, 330, 300);
        history.setVisible(true);
    }

    public class MyItemListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            LOGGER.info("Parameter 'Logging time' was changed to " + isNeedLog);

            isNeedLog = !isNeedLog;
        }
    }

    public void setHistoryFile() {

        historyFile = new File("history.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(historyFile))) {

            for (Calculation calc : calculations) {
                writer.write(String.valueOf(calc.getDate() + "  " + calc.getFormula() + " = " + calc.getAnswer()));
                writer.newLine();
            }
            LOGGER.info("History successfully saved.");

        } catch (Exception ex) {

            LOGGER.warn("Saving was interrupted.");
        }

    }
}



