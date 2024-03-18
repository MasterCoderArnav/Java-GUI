import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class notepad extends JFrame implements ActionListener {
    Container c;
    JTextArea ta;
    JMenuBar mb;
    JMenu file, edit, help;
    JMenuItem newOpItem, open, save, exit, cut, copy, paste, selectAll, about;

    notepad() {
        this.setTitle("Notepad");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        c = this.getContentPane();

        ta = new JTextArea();
        c.add(ta, BorderLayout.CENTER);

        mb = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        newOpItem = new JMenuItem("New");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        exit = new JMenuItem("Exit");
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        about = new JMenuItem("About");
        file.add(newOpItem);
        file.add(open);
        file.add(save);
        file.add(exit);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        help.add(about);

        newOpItem.addActionListener(this);
        newOpItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        open.addActionListener(this);
        open.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        save.addActionListener(this);
        save.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        exit.addActionListener(this);
        exit.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_Q, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        cut.addActionListener(this);
        cut.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        copy.addActionListener(this);
        copy.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        paste.addActionListener(this);
        paste.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_V, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        selectAll.addActionListener(this);
        selectAll.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_A, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        about.addActionListener(this);

        mb.add(file);
        mb.add(edit);
        mb.add(help);

        this.setJMenuBar(mb);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new notepad();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newOpItem) {
            ta.setText("");
        } else if (e.getSource() == open || e.getSource() == save) {
            JFileChooser fc = new JFileChooser("/Users/arnavtripathi/Desktop/AMD Internship/Java AMD");
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.setMultiSelectionEnabled(false);
            fc.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
            fc.setFileFilter(filter);
            if (e.getSource() == open) {
                int i = fc.showOpenDialog(null);

                if (i == JFileChooser.APPROVE_OPTION) {
                    String path = fc.getSelectedFile().getAbsolutePath();
                    File file = new File(path);
                    try {
                        Scanner sc = new Scanner(file);
                        String data = "";
                        while (sc.hasNextLine()) {
                            data += sc.nextLine() + "\n";
                        }
                        sc.close();
                        ta.setText(data);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            } else {
                int i = fc.showSaveDialog(null);
                if (i == JFileChooser.APPROVE_OPTION) {
                    String path = fc.getSelectedFile().getAbsolutePath();
                    if (!path.endsWith(".txt")) {
                        path += ".txt";
                    }
                    File file = new File(path);

                    try {
                        file.createNewFile();
                        System.out.println("File created");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    file = new File(path);
                    try {
                        String data = ta.getText();
                        FileWriter fw = new FileWriter(file);
                        fw.write(data);
                        fw.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } else if (e.getSource() == selectAll) {
            ta.selectAll();
        } else if (e.getSource() == cut) {
            ta.cut();
        } else if (e.getSource() == copy) {
            ta.copy();
        } else if (e.getSource() == paste) {
            ta.paste();
        } else if (e.getSource() == about) {
            JOptionPane.showMessageDialog(this, "This is a simple notepad application made using Java Swing", "About",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == exit) {
            System.exit(0);
        }
    }

}
