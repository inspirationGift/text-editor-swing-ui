import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JPanel implements ActionListener {

    JLabel userNameLabel;
    JTextField userNameField;
    JLabel passwordLabel;
    JPasswordField passwordField;
    JPanel loginPanel;
    JPanel panel;
    JButton loginButton;
    JButton registryButton;
    CardLayout cardLayout;


    public Login() {
        createFormElements();
        addListeners();
        buildForm();
    }

    private void createFormElements() {
        this.userNameLabel = new JLabel("User name:");
        this.userNameField = new JTextField();
        this.passwordLabel = new JLabel("Password:");
        this.passwordField = new JPasswordField();
        this.loginPanel = new JPanel(new GridLayout(3, 2));
        this.panel = new JPanel();
        this.loginButton = new JButton("Login");
        this.registryButton = new JButton("Registry");
    }

    private void buildForm() {
        setLayout(new CardLayout());
        this.loginPanel.add(userNameLabel);
        this.loginPanel.add(userNameField);
        this.loginPanel.add(passwordLabel);
        this.loginPanel.add(passwordField);
        this.loginPanel.add(loginButton);
        this.loginPanel.add(registryButton);
        this.panel.add(this.loginPanel);
        add(this.panel, "login");
        this.cardLayout = (CardLayout) getLayout();
    }

    private void addListeners() {
        this.loginButton.addActionListener(this);
        this.registryButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            if (FieldsValidate.isUserName(this.userNameField)) {
                if (FieldsValidate.isUserPassword(this.passwordField)) {

                    add(new FileBrowser(userNameField.getText()), "fb");
                    cardLayout.show(this, "fb");

                } else {
                    System.out.println("Wrong password, please repeat");
                }
            } else {
                System.out.println("No such user, please register");
            }
        }
        if (e.getSource() == registryButton) {
            add(new RegistrationWindow(), "register");
            this.cardLayout.show(this, "register");
        }
    }
}
