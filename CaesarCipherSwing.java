import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Caesar Cipher GUI Application
 * Allows user to encrypt and decrypt text using shift value
 * Developed using Java Swing
 */

public class CaesarCipherSwing extends JFrame implements ActionListener {

    // Input fields
    JTextField encryptInput, decryptInput, shiftField;

    // Output areas
    JTextArea encryptOutput, decryptOutput;

    // Buttons
    JButton encryptBtn, decryptBtn;

    // Constructor to set up GUI
    CaesarCipherSwing() {

        // Frame settings
        setTitle("Caesar Cipher - Encryption & Decryption");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window
        setLayout(new BorderLayout(10, 10));

        // ===== Top Panel (Shift Value) =====
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(220, 230, 240));

        JLabel shiftLabel = new JLabel("Shift Value:");
        shiftLabel.setFont(new Font("Arial", Font.BOLD, 14));

        shiftField = new JTextField(5);
        shiftField.setFont(new Font("Arial", Font.PLAIN, 14));

        topPanel.add(shiftLabel);
        topPanel.add(shiftField);

        add(topPanel, BorderLayout.NORTH);

        // ===== Main Panel =====
        JPanel mainPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ===== Encrypt Panel =====
        JPanel encryptPanel = new JPanel(new BorderLayout(5, 5));
        encryptPanel.setBorder(BorderFactory.createTitledBorder("🔐 Encrypt"));

        encryptInput = new JTextField();
        encryptInput.setFont(new Font("Arial", Font.PLAIN, 14));

        encryptOutput = new JTextArea(3, 20);
        encryptOutput.setFont(new Font("Arial", Font.PLAIN, 14));
        encryptOutput.setEditable(false);
        encryptOutput.setLineWrap(true);

        encryptBtn = new JButton("Encrypt");
        encryptBtn.setFont(new Font("Arial", Font.BOLD, 13));

        encryptPanel.add(new JLabel("Plain Text:"), BorderLayout.NORTH);
        encryptPanel.add(encryptInput, BorderLayout.CENTER);
        encryptPanel.add(encryptBtn, BorderLayout.EAST);
        encryptPanel.add(new JScrollPane(encryptOutput), BorderLayout.SOUTH);

        // ===== Decrypt Panel =====
        JPanel decryptPanel = new JPanel(new BorderLayout(5, 5));
        decryptPanel.setBorder(BorderFactory.createTitledBorder("🔓 Decrypt"));

        decryptInput = new JTextField();
        decryptInput.setFont(new Font("Arial", Font.PLAIN, 14));

        decryptOutput = new JTextArea(3, 20);
        decryptOutput.setFont(new Font("Arial", Font.PLAIN, 14));
        decryptOutput.setEditable(false);
        decryptOutput.setLineWrap(true);

        decryptBtn = new JButton("Decrypt");
        decryptBtn.setFont(new Font("Arial", Font.BOLD, 13));

        decryptPanel.add(new JLabel("Encrypted Text:"), BorderLayout.NORTH);
        decryptPanel.add(decryptInput, BorderLayout.CENTER);
        decryptPanel.add(decryptBtn, BorderLayout.EAST);
        decryptPanel.add(new JScrollPane(decryptOutput), BorderLayout.SOUTH);

        // Add panels to main panel
        mainPanel.add(encryptPanel);
        mainPanel.add(decryptPanel);

        add(mainPanel, BorderLayout.CENTER);

        // Button actions
        encryptBtn.addActionListener(this);
        decryptBtn.addActionListener(this);

        setVisible(true);
    }

    /*
     * Caesar Cipher logic
     * Shifts characters based on given value
     */
    public String caesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {

            // For uppercase letters
            if (Character.isUpperCase(c)) {
                result.append((char) ((c - 'A' + shift + 26) % 26 + 'A'));
            }
            // For lowercase letters
            else if (Character.isLowerCase(c)) {
                result.append((char) ((c - 'a' + shift + 26) % 26 + 'a'));
            }
            // For symbols and spaces
            else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /*
     * Handles button clicks
     */
    public void actionPerformed(ActionEvent e) {
        try {
            int shift = Integer.parseInt(shiftField.getText());

            if (e.getSource() == encryptBtn) {
                encryptOutput.setText(
                        caesarCipher(encryptInput.getText(), shift));
            }

            if (e.getSource() == decryptBtn) {
                decryptOutput.setText(
                        caesarCipher(decryptInput.getText(), -shift));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid shift value!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Main method
    public static void main(String[] args) {
        new CaesarCipherSwing();
    }
}
