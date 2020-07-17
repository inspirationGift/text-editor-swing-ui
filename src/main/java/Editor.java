import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Editor extends JPanel implements ActionListener {
    File file;
    JButton save;
    JButton saveClose;
    JTextArea text;

    public Editor(String s) {
        this.file = new File(s);
        build();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try (FileWriter fw = new FileWriter(this.file)) {
            fw.write(text.getText());
            if (e.getSource() == saveClose) {
                Login login = (Login) getParent();
                login.cardLayout.show(login, "fb");
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void build() {

        System.out.println(file);
        this.save = new JButton("Save");
        this.saveClose = new JButton("Save and Close");
        this.text = new JTextArea(20, 40);

        this.save.addActionListener(this);
        this.saveClose.addActionListener(this);

        if (file.exists()) {
            try (BufferedReader input = new BufferedReader(new FileReader(file))) {
                String line = input.readLine();
                while (line != null) {
                    this.text.append(line + "\n");
                    line = input.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        add(this.save);
        add(this.saveClose);
        add(this.text);
    }
}
