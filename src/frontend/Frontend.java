package frontend;

import controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frontend extends JFrame {
    private Controller controller;
    private JPanel contentPanel;
    private JTextField searchField;
    private JButton searchBtn;
    private JTable recipeList;
    private JButton newRecipeBtn;
    private JButton saveBtn;
    private JButton deleteBtn;
    private JButton upVoteBtn;
    private JButton downVoteBtn;
    private JLabel ratingLabel;
    private JPanel recipeForm;
    private JTextField recipeNameField;
    private JTextField recipeCookTimeField;
    private JTextArea recipeIngredientsField;
    private JTextArea recipeStepsField;
    private JTextArea recipeNotesField;

    public Frontend() {
        // get controller instance
        controller = Controller.getInstance();

        setTitle("Recipe Manager");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // initialize fields
        contentPanel = new JPanel();
        searchField = new JTextField(20);
        searchBtn = new JButton("Search");

        // recipe
        RecipeTableModel tableModel = new RecipeTableModel(controller.getRecipes());
        recipeList = new JTable(tableModel);

        newRecipeBtn = new JButton("New Recipe");
        saveBtn = new JButton("Save");
        deleteBtn = new JButton("Delete");
        upVoteBtn = new JButton("Up Vote");
        downVoteBtn = new JButton("Down Vote");
        ratingLabel = new JLabel("3");
        recipeForm = new JPanel();
        recipeForm.setLayout(new BoxLayout(recipeForm, BoxLayout.Y_AXIS));
        recipeNameField = new JTextField(20);
        recipeCookTimeField = new JTextField(20);
        recipeIngredientsField = new JTextArea(3, 20);
        recipeStepsField = new JTextArea(3, 20);
        recipeNotesField = new JTextArea(3, 20);

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
        buttonPanel.add(upVoteBtn);
        buttonPanel.add(downVoteBtn);
        // main recipes container
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(searchPanel, BorderLayout.NORTH); // 1: search bar
        contentPanel.add(new JScrollPane(recipeList), BorderLayout.CENTER); // 2: recipes
        contentPanel.add(buttonPanel, BorderLayout.SOUTH); // 3: buttons
        // recipe form
        JLabel ratingLabelText = new JLabel("Rating: ");
        JPanel horizontalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        horizontalPanel.add(ratingLabelText);
        horizontalPanel.add(ratingLabel);
        recipeForm.add(horizontalPanel);
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        recipeForm.add(nameLabel);
        recipeForm.add(recipeNameField);
        JLabel cookTimeLabel = new JLabel("Cook Time:");
        cookTimeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        recipeForm.add(cookTimeLabel);
        recipeForm.add(recipeCookTimeField);
        JLabel ingredientsLabel = new JLabel("Ingredients:");
        ingredientsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        recipeForm.add(ingredientsLabel);
        recipeForm.add(new JScrollPane(recipeIngredientsField));
        JLabel stepsLabel = new JLabel("Steps:");
        stepsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        recipeForm.add(stepsLabel);
        recipeForm.add(new JScrollPane(recipeStepsField));
        JLabel notesLabel = new JLabel("Notes:");
        notesLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        recipeForm.add(notesLabel);
        recipeForm.add(new JScrollPane(recipeNotesField));

        // add borders to both containers
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        recipeForm.setBorder(new EmptyBorder(10, 10, 10, 10));
        // add containers to frame
        add(contentPanel, BorderLayout.CENTER);
        add(recipeForm, BorderLayout.SOUTH);

        // button click: event listener methods
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBtnClicked();
            }
        });
        newRecipeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newBtnClicked();
            }
        });
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveBtnClicked();
            }
        });
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBtnClicked();
            }
        });
        upVoteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upVoteBtnClicked();
            }
        });
        downVoteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downVoteBtnClicked();
            }
        });
    }

    private void displayRecipes() {
    }

    private void searchBtnClicked() {
        System.out.println("Search button clicked");
    }

    private void newBtnClicked() {
        System.out.println("New button clicked");
    }

    private void saveBtnClicked() {
        System.out.println("Save button clicked");
    }

    private void deleteBtnClicked() {
        System.out.println("Delete button clicked");
    }

    private void upVoteBtnClicked() {
        System.out.println("Upvote button clicked");
        // get curr rating
        int currRating = Integer.parseInt(ratingLabel.getText());
        if (currRating < 5) {
            currRating++;
        }
        ratingLabel.setText(String.valueOf(currRating));
    }

    private void downVoteBtnClicked() {
        System.out.println("Downvote button clicked");
        // get curr rating
        int currRating = Integer.parseInt(ratingLabel.getText());
        if (currRating > 1) {
            currRating--;
        }
        ratingLabel.setText(String.valueOf(currRating));
    }

    public static void main(String[] args) {
        Frontend frontend = new Frontend();
        frontend.setVisible(true);
    }
}