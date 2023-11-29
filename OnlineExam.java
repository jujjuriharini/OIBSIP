import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class login extends JFrame implements ActionListener {
    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel;
    final JTextField textField1, textField2;

    login() {
        userLabel = new JLabel();
        userLabel.setText("    Username :");
        textField1 = new JTextField(15);
        passLabel = new JLabel();
        passLabel.setText("    Password :");
        textField2 = new JPasswordField(8);
        b1 = new JButton("   SUBMIT   ");
        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(userLabel);
        newPanel.add(textField1);
        newPanel.add(passLabel);
        newPanel.add(textField2);
        newPanel.add(b1);
        add(newPanel, BorderLayout.CENTER);
        b1.addActionListener(this);
        setTitle("Login Form ");
    }

    public void actionPerformed(ActionEvent ae) {
        String userValue = textField1.getText();
        String passValue = textField2.getText();
        if (!passValue.equals(""))
            new OnlineTestBegin(userValue);
        else {
            textField2.setText("Enter the Password");
            actionPerformed(ae);
        }
    }
}

class OnlineTestBegin extends JFrame implements ActionListener {
    JLabel l;
    JLabel l1;
    JRadioButton jb[] = new JRadioButton[6];
    JButton b1, b2, log;
    ButtonGroup bg;
    int count = 0, current = 0, x = 1, y = 1, now = 0;
    int m[] = new int[10];
    Timer timer = new Timer();

    OnlineTestBegin(String s) {
        super(s);
        l = new JLabel();
        l1 = new JLabel();
        add(l);
        add(l1);
        bg = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            jb[i] = new JRadioButton();
            add(jb[i]);
            bg.add(jb[i]);
        }
        b1 = new JButton("Save and Next");
        b2 = new JButton("Save for later");
        b1.addActionListener(this);
        b2.addActionListener(this);
        add(b1);
        add(b2);
        set();
        l.setBounds(30, 40, 450, 20);
        l1.setBounds(20, 20, 450, 20);
        jb[0].setBounds(50, 80, 100, 20);
        jb[1].setBounds(50, 110, 100, 20);
        jb[2].setBounds(50, 140, 100, 20);
        jb[3].setBounds(50, 170, 100, 20);
        b1.setBounds(95, 240, 140, 30);
        b2.setBounds(270, 240, 150, 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(300, 150);
        setVisible(true);
        setSize(600, 350);
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 300;

            public void run() {
                l1.setText("Time left: " + i);
                i--;
                if (i < 0) {
                    timer.cancel();
                    l1.setText("Time Out");
                }
            }
        }, 0, 1000);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            if (check())
                count = count + 1;
            current++;
            set();
            if (current == 9) {
                b1.setEnabled(false);
                b2.setText("Result");
            }
        }
        if (e.getActionCommand().equals("Save for later")) {
            JButton bk = new JButton("Review" + x);
            bk.setBounds(480, 20 + 30 * x, 100, 30);
            add(bk);
            bk.addActionListener(this);
            m[x] = current;
            x++;
            current++;
            set();
            if (current == 9)
                b2.setText("Result");
            setVisible(false);
            setVisible(true);
        }
        for (int i = 0, y = 1; i < x; i++, y++) {
            if (e.getActionCommand().equals("Review" + y)) {
                if (check())
                    count = count + 1;
                now = current;
                current = m[y];
                set();
                ((JButton) e.getSource()).setEnabled(false);
                current = now;
            }
        }
        if (e.getActionCommand().equals("Result")) {
            if (check())
                count = count + 1;
            current++;
            JOptionPane.showMessageDialog(this, "Score =" + count);
            System.exit(0);
        }
    }

    void set() {
        jb[4].setSelected(true);
        if (current == 0) {
            l.setText("Q1: Which of the following is not a Java features?");
            jb[0].setText("Dynamic");
            jb[1].setText("Architecture Neural");
            jb[2].setText("Use of pointers");
            jb[3].setText("Object-oriented");
        }
        if (current == 1) {
            l.setText("Q2: The \\u0021 article referred to as a ?");
            jb[0].setText("Unicode escape sequence");
            jb[1].setText("Octal Escape");
            jb[2].setText("Hexadecimal");
            jb[3].setText("Line Feed");
        }
        if (current == 2) {
            l.setText("Q3:  _____ is used to find and fix bugs in the Java programs.");
            jb[0].setText("JVM");
            jb[1].setText("JRE ");
            jb[2].setText("JDK");
            jb[3].setText("JDB");
        }
        if (current == 3) {
            l.setText("Q4: Which of the following is a valid declaration of a char?");
            jb[0].setText("char ch = '\\utea';");
            jb[1].setText("char ca = 'tea';");
            jb[2].setText("char cr = \\u0223;");
            jb[3].setText("char cc = '\\itea';");
        }
        if (current == 4) {
            l.setText("Q5: What is the return type of the hashCode() method in the Object class?");
            jb[0].setText("Object");
            jb[1].setText("int");
            jb[2].setText("long");
            jb[3].setText("void");
        }
        if (current == 5) {
            l.setText("Q6: Evaluate the following Java expression, if x=3, y=5, and z=10:\r\n" + //
                    "\r\n" + //
                    "++z + y - y + z + x++");
            jb[0].setText("24");
            jb[1].setText("23");
            jb[2].setText("20");
            jb[3].setText("25");
        }
        if (current == 6) {
            l.setText(
                    "Q7: Which of the following tool is used to generate API documentation in HTML format from doc comments in source code? ");
            jb[0].setText("java tool");
            jb[1].setText("javaw command");
            jb[2].setText("Javadoc tool");
            jb[3].setText("javah command");
        }
        if (current == 7) {
            l.setText("Q8: Which of the following creates a List of 3 visible items and multiple selections abled?");
            jb[0].setText("new List(false, 3)");
            jb[1].setText("enew List(3, true)");
            jb[2].setText("new List(true, 3)");
            jb[3].setText("new List(3, false)");
        }
        if (current == 8) {
            l.setText(
                    "Q9: Which method of the Class.class is used to determine the name of a class represented by the class object as a String?");
            jb[0].setText("getClass()");
            jb[1].setText("intern()");
            jb[2].setText("getName()");
            jb[3].setText("toString()");
        }
        if (current == 9) {
            l.setText("Q10:  In which process, a local variable has the same name as one of the instance variables?");
            jb[0].setText("Serialization");
            jb[1].setText("Variable Shadowing");
            jb[2].setText("Abstaction");
            jb[3].setText("Multi-threading");
        }
        l.setBounds(30, 40, 450, 20);
        for (int i = 0, j = 0; i <= 90; i += 30, j++)
            jb[j].setBounds(50, 80 + i, 200, 20);
    }

    boolean check() {
        if (current == 0)
            return (jb[2].isSelected());
        if (current == 1)
            return (jb[0].isSelected());
        if (current == 2)
            return (jb[3].isSelected());
        if (current == 3)
            return (jb[3].isSelected());
        if (current == 4)
            return (jb[1].isSelected());
        if (current == 5)
            return (jb[3].isSelected());
        if (current == 6)
            return (jb[2].isSelected());
        if (current == 7)
            return (jb[1].isSelected());
        if (current == 8)
            return (jb[2].isSelected());
        if (current == 9)
            return (jb[1].isSelected());
        return false;
    }
}

class OnlineExam {
    public static void main(String args[]) {
        try {
            login form = new login();
            form.setSize(600, 300);
            form.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
