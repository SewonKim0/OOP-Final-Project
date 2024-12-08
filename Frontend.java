import javax.swing.*;
import java.awt.*;

public class Frontend extends JFrame {
    // private Controller controller;
    private JPanel contentPanel;
    private JTextField searchField;
    private JButton searchBtn;
    private JTable recipeList;
    private JButton newRecipeBtn;
    private JButton saveBtn;
    private JButton deleteBtn;
    private JComboBox<Integer> ratingBox;
    private JPanel recipeForm;
    private JTextField recipeNameField;
    private JTextField recipeCookTimeField;
    private JTextField recipeIngredientsField;
    private JTextField recipeStepsField;
    private JTextField recipeNotesField;

    public Frontend() {
        setTitle("Recipe Manager");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // initialize fields
        contentPanel = new JPanel();
        searchField = new JTextField(20);
        searchBtn = new JButton("Search");
        recipeList = new JTable();
        newRecipeBtn = new JButton("New Recipe");
        saveBtn = new JButton("Save");
        deleteBtn = new JButton("Delete");
        ratingBox = new JComboBox<>(new Integer[]{null, 1, 2, 3, 4, 5});
        recipeForm = new JPanel(new GridLayout(6, 2));
        recipeNameField = new JTextField(20);
        recipeCookTimeField = new JTextField(20);
        recipeIngredientsField = new JTextField(20);
        recipeStepsField = new JTextField(20);
        recipeNotesField = new JTextField(20);

        // add components
        setLayout(new BorderLayout());
        // search bar
        JPanel searchPanel = new JPanel();
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);
        // buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newRecipeBtn);
        buttonPanel.add(saveBtn);
        buttonPanel.add(deleteBtn);
        // main recipes container
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(searchPanel, BorderLayout.NORTH); // 1: search bar
        contentPanel.add(new JScrollPane(recipeList), BorderLayout.CENTER); // 2: recipes
        contentPanel.add(buttonPanel, BorderLayout.SOUTH); // 3: buttons
        // recipe form
        recipeForm.add(new JLabel("Name:"));
        recipeForm.add(recipeNameField);
        recipeForm.add(new JLabel("Cook Time:"));
        recipeForm.add(recipeCookTimeField);
        recipeForm.add(new JLabel("Ingredients:"));
        recipeForm.add(recipeIngredientsField);
        recipeForm.add(new JLabel("Steps:"));
        recipeForm.add(recipeStepsField);
        recipeForm.add(new JLabel("Notes:"));
        recipeForm.add(recipeNotesField);
        recipeForm.add(new JLabel("Rating:"));
        recipeForm.add(ratingBox);
        // add containers to frame
        add(contentPanel, BorderLayout.CENTER);
        add(recipeForm, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        Frontend frontend = new Frontend();
        frontend.setVisible(true);
    }
}