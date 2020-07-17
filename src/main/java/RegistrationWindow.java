
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationWindow extends JPanel implements ActionListener {
    JLabel userL;
    JLabel passwordL;
    JLabel confirmL;
    JTextField userT;
    JPasswordField passwordT;
    JPasswordField confirmPassT;
    JButton completeRegistration;
    JButton cancel;
    JPanel panel;

    public RegistrationWindow() {
        formInit();
        buildForm();
        setListeners();
    }

    private void formInit() {
        this.userL = new JLabel("User Name");
        this.passwordL = new JLabel("Password");
        this.confirmL = new JLabel("Confirm Password");
        this.userT = new JTextField();
        this.passwordT = new JPasswordField();
        this.confirmPassT = new JPasswordField();
        this.completeRegistration = new JButton("Enter");
        this.cancel = new JButton("Back");
        this.panel = new JPanel();
    }

    private void buildForm() {
        panel.setLayout(new GridLayout(4, 2));
        panel.add(userL);
        panel.add(userT);
        panel.add(passwordL);
        panel.add(passwordT);
        panel.add(confirmL);
        panel.add(confirmPassT);
        panel.add(completeRegistration);
        panel.add(cancel);
        add(this.panel);
    }

    private void setListeners() {
        this.completeRegistration.addActionListener(this);
        this.cancel.addActionListener(this);
    }

    private boolean passCheck() {
        String s = new String(this.passwordT.getPassword());
        String s2 = new String(this.confirmPassT.getPassword());
        return (s.length() > 0 && s2.length() > 0) && (s.equals(s2));
    }

    private void leaveRegistrationForm() {
        Login login = (Login) getParent();
        login.cardLayout.show(login, "login");
    }

    public void actionPerformed(ActionEvent e) {
        if (this.completeRegistration == e.getSource() && (passCheck() && !FieldsValidate.isUserName(this.userT)))
            FieldsValidate.savePassword(this.userT, this.passwordT);
        if (this.cancel == e.getSource()) leaveRegistrationForm();
        leaveRegistrationForm();
    }
}
