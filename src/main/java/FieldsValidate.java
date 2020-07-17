import javax.swing.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.StringTokenizer;

public class FieldsValidate {
    private static JTextField userName;
    private static String password;

    static boolean isUserName(JTextField field) {
        String user = field.getText();
        userName = field;
        try (BufferedReader bf = new BufferedReader(new FileReader("password.txt"))) {
            String line = bf.readLine();
            while (line != null) {
                StringTokenizer st = new StringTokenizer(line);
                if (user.equals(st.nextToken())) {
                    password = st.nextToken();
                    return true;
                }
                line = bf.readLine();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

    private static String encryptPassword(JPasswordField p1) {
        StringBuffer sb = new StringBuffer();
        MessageDigest md;
        String pass = new String(p1.getPassword());
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(pass.getBytes());
            byte[] byteData = md.digest();
            for (byte byteDatum : byteData) sb.append(Integer.toString((byteDatum & 0xFF) + 0x100, 16).substring(1));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void writeToFile(String str) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("password.txt", true))) {
            if (userName != null && password != null)
                bw.write(userName.getText() + " " + str + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void savePassword(JTextField user, JPasswordField p1) {
        writeToFile(encryptPassword(p1));
    }

    public static boolean isUserPassword(JPasswordField pass) {
        return Objects.equals(encryptPassword(pass), password);
    }
}
