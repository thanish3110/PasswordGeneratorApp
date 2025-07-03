import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class PasswordGeneratorApp extends JFrame {

    private JTextField lengthField;
    private JCheckBox useUpper, useLower, useNumbers, useSpecial;
    private JButton generateButton;
    private JTextField resultField;

    public PasswordGeneratorApp() {
        setTitle("Password Generator");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 1, 5, 5));

        // Components
        add(new JLabel("Enter Password Length:"));
        lengthField = new JTextField();
        add(lengthField);

        useUpper = new JCheckBox("Include Uppercase Letters (A-Z)");
        useLower = new JCheckBox("Include Lowercase Letters (a-z)");
        useNumbers = new JCheckBox("Include Numbers (0-9)");
        useSpecial = new JCheckBox("Include Special Characters (!@#$...)");
        add(useUpper);
        add(useLower);
        add(useNumbers);
        add(useSpecial);

        generateButton = new JButton("Generate Password");
        add(generateButton);

        resultField = new JTextField();
        resultField.setEditable(false);
        add(resultField);

        // Action listener
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generatePassword();
            }
        });
    }

    private void generatePassword() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String special = "!@#$%^&*()_+-=[]{}|;:',.<>?/";

        StringBuilder characterSet = new StringBuilder();

        if (useUpper.isSelected()) characterSet.append(upper);
        if (useLower.isSelected()) characterSet.append(lower);
        if (useNumbers.isSelected()) characterSet.append(numbers);
        if (useSpecial.isSelected()) characterSet.append(special);

        int length;
        try {
            length = Integer.parseInt(lengthField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!");
            return;
        }

        if (characterSet.length() == 0 || length <= 0) {
            JOptionPane.showMessageDialog(this, "Please select at least one option and a valid length!");
            return;
        }

        StringBuilder password = new StringBuilder();
        Random rand = new Random();

        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(characterSet.length());
            password.append(characterSet.charAt(index));
        }

        resultField.setText(password.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PasswordGeneratorApp().setVisible(true));
    }
}