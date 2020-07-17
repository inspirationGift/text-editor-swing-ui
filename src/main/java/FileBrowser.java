import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileBrowser extends JPanel implements ActionListener {

    JLabel label;
    JButton newFile;
    JButton openFile;
    ButtonGroup bg;
    File directory;
    JPanel fileList;
    JTextField newTF;

    public FileBrowser(String dir) {
        uiInit(dir);
    }

    private void uiInit(String dir) {
        this.directory = new File(dir);
        this.directory.mkdir();
        this.label = new JLabel("File list:");
        this.newFile = new JButton("New file");
        this.openFile = new JButton("Open");
        this.fileList = new JPanel(new GridLayout(
                directory.listFiles().length + 3, 1));
        fileList.add(this.label);
        this.bg = new ButtonGroup();

        for (File file : this.directory.listFiles()) {
            JRadioButton radioButton = new JRadioButton(file.getName());
            radioButton.setActionCommand(file.getName());
            this.bg.add(radioButton);
            this.fileList.add(radioButton);
        }

        JPanel newPanel = new JPanel();
        this.newTF = new JTextField(10);
        newPanel.add(this.newTF);
        this.newFile.addActionListener(this);
        this.openFile.addActionListener(this);
        this.fileList.add(newFile);
        this.fileList.add(openFile);
        this.fileList.add(newPanel);
        add(fileList);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Login login = (Login) getParent();
        if (e.getSource() == openFile) {
            login.add(new Editor(this.directory.getName() + "\\" + bg.getSelection().getActionCommand()), "editor");
            login.cardLayout.show(login, "editor");
        }
        if (e.getSource() == newFile) {
            String file = this.directory.getName() + "\\" + newTF.getText() + ".txt";

            if (newTF.getText().length() > 0 && !(new File(file).exists())) {
                login.add(new Editor(file), "editor");
                login.cardLayout.show(login, "editor");
            }
        }
    }
}
