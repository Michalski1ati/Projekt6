package silnia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FactorialWindow extends JFrame implements WindowListener, ActionListener, FactorialResultListener {

    private JTextArea text;
    private JTextField startelements;
    private JTextField repeats;

    private Thread[] t;

    public FactorialWindow() {

        setBounds(100, 100, 600, 500);
        setLayout(null);

        addWindowListener(this);

        text = new JTextArea();
        text.setBounds(10, 90, 560, 360);
        text.setText("Kliknij start");
        add(text);

        startelements = new JTextField();
        startelements.setBounds(10, 10, 275, 30);
        startelements.setText("Liczba");
        add(startelements);

        repeats = new JTextField();
        repeats.setBounds(295, 10, 275, 30);
        repeats.setText("Ilość powtórzeń");
        add(repeats);

        JButton button = new JButton("Start test");
        button.setBounds(10, 50, 275, 30);
        button.setActionCommand("start");
        button.addActionListener(this);
        add(button);

        button = new JButton("Przerwij");
        button.setBounds(295, 50, 275, 30);
        button.setActionCommand("przerwij");
        button.addActionListener(this);
        add(button);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getActionCommand().equals("start")) {

            int val = 0;
            int rep = 0;

            try {
                val = Integer.parseInt(startelements.getText().trim());
                rep = Integer.parseInt(repeats.getText().trim());
            } catch (NumberFormatException e1) {
                text.setText("zła liczba");
                return;
            }

            t = new Thread[2];

            FactorialIterationRunnable itr = new FactorialIterationRunnable();
            itr.setValue(val, rep);
            itr.addFactorialResultListener(this);

            FactorialRecursionRunnable rer = new FactorialRecursionRunnable();
            rer.setValue(val, rep);
            rer.addFactorialResultListener(this);

            t[0] = new Thread(itr);
            t[1] = new Thread(rer);

            t[0].start();
            t[1].start();

            text.setText("");

        } else if (e.getActionCommand().equals("przerwij")) {
            t[0].interrupt();
            t[1].interrupt();

            try {
                Thread.sleep(200);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            if (!t[0].isAlive() && !t[1].isAlive()) {
                text.append("\nPrzerwano");
            }
        }

    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosed(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosing(WindowEvent e) {
        // TODO Auto-generated method stub
        System.exit(0);
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void valueCalculated(String s) {
        // TODO Auto-generated method stub
        text.append("\n" + s);
    }

}
