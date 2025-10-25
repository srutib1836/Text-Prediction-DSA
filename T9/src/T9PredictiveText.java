/*port javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class T9PredictiveText extends JFrame {

    private final TrieNode root;
    private final JTextField inputField;
    private final JLabel predictionLabel;
    private String currentNumericSequence = "";
    private List<String> currentPredictions = new ArrayList<>();
    private int predictionIndex = 0;

    private static final Map<Integer, String> KEYPAD_MAP = new HashMap<>();
    static {
        KEYPAD_MAP.put(2, "abc");
        KEYPAD_MAP.put(3, "def");
        KEYPAD_MAP.put(4, "ghi");
        KEYPAD_MAP.put(5, "jkl");
        KEYPAD_MAP.put(6, "mno");
        KEYPAD_MAP.put(7, "pqrs");
        KEYPAD_MAP.put(8, "tuv");
        KEYPAD_MAP.put(9, "wxyz");
    }

    // --- UTILITY ---
    
    
    private int charToInt(char keyChar) {
        // Use Character.getNumericValue() for robust char-to-int conversion
        int digit = Character.getNumericValue(keyChar);
        if (digit >= 0 && digit <= 9) {
            return digit;
        }
        return -1;
    }

    // --- SETUP and GUI (Unchanged) ---

    public T9PredictiveText() {
        super("T9 Predictive Text Simulator (Trie)");
        root = new TrieNode();
        loadDictionary("dictionary.txt"); 
        
        inputField = new JTextField(20);
        inputField.setEditable(false);
        predictionLabel = new JLabel("Prediction: ");
        predictionLabel.setFont(new Font("Monospaced", Font.BOLD, 16));

        setupGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
    }

    private void setupGUI() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(inputField);
        topPanel.add(predictionLabel);
        add(topPanel, BorderLayout.NORTH);

        JPanel keypadPanel = new JPanel(new GridLayout(4, 3, 5, 5));
        
        for (int i = 1; i <= 9; i++) {
            keypadPanel.add(createKeypadButton(i));
        }
        
        keypadPanel.add(createSpecialButton("DEL", "DEL"));
        keypadPanel.add(createKeypadButton(0));
        keypadPanel.add(createSpecialButton("#", "NEXT"));
        
        add(keypadPanel, BorderLayout.CENTER);
    }

    // --- CORE LOGIC (Mapping & Insertion) ---

    private int charToDigit(char c) {
        c = Character.toLowerCase(c);
        for (Map.Entry<Integer, String> entry : KEYPAD_MAP.entrySet()) {
            if (entry.getValue().indexOf(c) != -1) {
                return entry.getKey();
            }
        }
        return -1;
    }

    private void insertWord(String word) {
        TrieNode current = root;
        for (char letter : word.toCharArray()) {
            int digit = charToDigit(letter);

            if (digit == -1) { 
                return; 
            }

            if (current.children[digit] == null) {
                current.children[digit] = new TrieNode();
            }
            current = current.children[digit];
        }
        current.words.add(word); 
    }

    // --- FIX IS HERE: Prediction Logic ---

    private List<String> findPredictions(String sequence) {
        if (sequence.isEmpty()) return new ArrayList<>();

        TrieNode current = root;
        for (char keyChar : sequence.toCharArray()) {
            // FIX: Use the robust helper function
            int digit = charToInt(keyChar); 

            if (digit < 0 || digit > 9 || current.children[digit] == null) {
                return new ArrayList<>(); // Path does not exist
            }
            current = current.children[digit];
        }
        return current.words; // Returns the list of ALL words (textonyms)
    }

    private void updatePrediction() {
        currentPredictions = findPredictions(currentNumericSequence);
        predictionIndex = 0;
        
        if (currentPredictions.isEmpty()) {
            predictionLabel.setText("Prediction: ---");
        } else {
            predictionLabel.setText("Prediction: " + currentPredictions.get(0));
        }
    }
    
    // --- Dictionary Loading & GUI Events (Unchanged) ---

    private void loadDictionary(String filename) {
        try {
            File file = new File(filename); 
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().toLowerCase().trim();
                if (!word.isEmpty()) {
                    insertWord(word);
                }
            }
            scanner.close();
            System.out.println("Dictionary loaded successfully.");
        } catch (FileNotFoundException e) {
            System.err.println("Error: dictionary.txt not found. Trie is empty.");
        }
    }
    
    private JButton createKeypadButton(int digit) {
        String label = String.valueOf(digit);
        if (KEYPAD_MAP.containsKey(digit)) {
            label += "\n(" + KEYPAD_MAP.get(digit) + ")";
        }
        
        JButton button = new JButton("<html><center>" + label.replace("\n", "<br>") + "</center></html>");
        button.addActionListener((ActionEvent e) -> handleKeypadPress(String.valueOf(digit)));
        return button;
    }

    private JButton createSpecialButton(String label, String actionCommand) {
        JButton button = new JButton(label);
        button.addActionListener((ActionEvent e) -> handleSpecialAction(actionCommand));
        return button;
    }

    private void handleKeypadPress(String digit) {
        currentNumericSequence += digit;
        inputField.setText(currentNumericSequence);
        updatePrediction(); 
    }

    private void handleSpecialAction(String command) {
        if (command.equals("NEXT")) { 
            if (currentPredictions.size() > 1) { 
                predictionIndex = (predictionIndex + 1) % currentPredictions.size(); 
                predictionLabel.setText("Prediction: " + currentPredictions.get(predictionIndex));
            }
        } else if (command.equals("DEL")) { 
            if (!currentNumericSequence.isEmpty()) {
                currentNumericSequence = currentNumericSequence.substring(0, currentNumericSequence.length() - 1);
                inputField.setText(currentNumericSequence);
                updatePrediction();
            }
        } else if (command.equals("0")) { 
            if (!currentPredictions.isEmpty()) {
                System.out.println("Word accepted: " + currentPredictions.get(predictionIndex));
            }
            currentNumericSequence = "";
            inputField.setText("");
            updatePrediction();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new T9PredictiveText());
    }
}
*/

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*; // for List, ArrayList, Map, etc.

/**
 * Final error-free version of T9 Predictive Text.
 * Works with TrieNode.java in same folder.
 */
public class T9PredictiveText extends JFrame {

    private final TrieNode root = new TrieNode();
    private final JTextField inputField = new JTextField(20);
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> predictionList = new JList<>(listModel);

    private String currentSequence = "";
    private java.util.List<String> currentPredictions = new ArrayList<>();

    private static final Map<Integer, String> KEYPAD_MAP = new HashMap<>();

    static {
        KEYPAD_MAP.put(2, "abc");
        KEYPAD_MAP.put(3, "def");
        KEYPAD_MAP.put(4, "ghi");
        KEYPAD_MAP.put(5, "jkl");
        KEYPAD_MAP.put(6, "mno");
        KEYPAD_MAP.put(7, "pqrs");
        KEYPAD_MAP.put(8, "tuv");
        KEYPAD_MAP.put(9, "wxyz");
    }

    public T9PredictiveText() {
        super("T9 Predictive Text");
        setLayout(new BorderLayout(8, 8));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 550);
        setLocationRelativeTo(null);

        // Top section
        inputField.setEditable(false);
        inputField.setFont(new Font("Monospaced", Font.PLAIN, 18));

        predictionList.setFont(new Font("Monospaced", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(predictionList);

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.add(inputField, BorderLayout.NORTH);
        topPanel.add(scrollPane, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // Keypad
        JPanel keypad = new JPanel(new GridLayout(4, 3, 5, 5));
        for (int i = 1; i <= 9; i++) {
            keypad.add(createKeypadButton(i));
        }
        keypad.add(createButton("DEL", e -> deleteDigit()));
        keypad.add(createButton("0 (OK)", e -> acceptWord()));
        keypad.add(createButton("# (NEXT)", e -> nextWord()));
        add(keypad, BorderLayout.CENTER);

        // Load dictionary
        loadDictionary("dictionary.txt");
        setVisible(true);
    }

    private JButton createKeypadButton(int digit) {
        String label = digit + " (" + KEYPAD_MAP.getOrDefault(digit, "") + ")";
        return createButton(label, e -> addDigit(digit));
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton btn = new JButton("<html><center>" + text.replace("\n", "<br>") + "</center></html>");
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.addActionListener(listener);
        return btn;
    }

    private void addDigit(int digit) {
        currentSequence += digit;
        inputField.setText(currentSequence);
        updatePredictions();
    }

    private void deleteDigit() {
        if (!currentSequence.isEmpty()) {
            currentSequence = currentSequence.substring(0, currentSequence.length() - 1);
            inputField.setText(currentSequence);
            updatePredictions();
        }
    }

    private void acceptWord() {
        if (!currentPredictions.isEmpty()) {
            String word = predictionList.getSelectedValue();
            JOptionPane.showMessageDialog(this, "Word accepted: " + word);
        }
        currentSequence = "";
        inputField.setText("");
        updatePredictions();
    }

    private void nextWord() {
        if (!currentPredictions.isEmpty()) {
            int nextIndex = (predictionList.getSelectedIndex() + 1) % currentPredictions.size();
            predictionList.setSelectedIndex(nextIndex);
            predictionList.ensureIndexIsVisible(nextIndex);
        }
    }

    private void loadDictionary(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            int count = 0;
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().trim().toLowerCase();
                if (!word.isEmpty()) {
                    insertWord(word);
                    count++;
                }
            }
            System.out.println("Loaded " + count + " words from dictionary.");
        } catch (FileNotFoundException e) {
            System.err.println("dictionary.txt not found!");
        }
    }

    private void insertWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int digit = charToDigit(c);
            if (digit == -1) return;
            if (node.children[digit] == null) node.children[digit] = new TrieNode();
            node = node.children[digit];
        }
        node.words.add(word);
    }

    private int charToDigit(char c) {
        for (Map.Entry<Integer, String> entry : KEYPAD_MAP.entrySet()) {
            if (entry.getValue().indexOf(c) != -1) return entry.getKey();
        }
        return -1;
    }

    private int charToInt(char keyChar) {
        int digit = Character.getNumericValue(keyChar);
        return (digit >= 0 && digit <= 9) ? digit : -1;
    }

    private java.util.List<String> findPredictions(String seq) {
        if (seq.isEmpty()) return new ArrayList<>();
        TrieNode node = root;
        for (char c : seq.toCharArray()) {
            int digit = charToInt(c);
            if (digit < 0 || node.children[digit] == null) return new ArrayList<>();
            node = node.children[digit];
        }
        java.util.List<String> result = new ArrayList<>();
        collectAll(node, result);
        return result;
    }

    private void collectAll(TrieNode node, java.util.List<String> result) {
        if (node == null) return;
        result.addAll(node.words);
        for (TrieNode child : node.children) {
            collectAll(child, result);
        }
    }

    private void updatePredictions() {
        listModel.clear();
        currentPredictions = findPredictions(currentSequence);
        if (currentPredictions.isEmpty()) {
            listModel.addElement("--- No matches ---");
        } else {
            for (String w : currentPredictions) listModel.addElement(w);
            predictionList.setSelectedIndex(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(T9PredictiveText::new);
    }
}
