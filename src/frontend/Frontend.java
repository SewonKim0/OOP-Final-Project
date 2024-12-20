package frontend;

import controller.Controller;
import backend.Recipe;
import java.util.List;
import java.util.ArrayList;

import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frontend extends JFrame {
    private Controller controller;
    private JPanel contentPanel;
    private JTextField searchField;
    private JButton searchBtn;
    private JTable recipeList;
    private RecipeTableModel tableModel;
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
    private boolean newRecipeClicked = false;

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
        tableModel = new RecipeTableModel(controller.getRecipes());
        // implement sort by column
        recipeList = new JTable(tableModel);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(recipeList.getModel());
        recipeList.setRowSorter(rowSorter);

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
        // table entry selected event
        recipeList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = recipeList.getSelectedRow();
                    if (selectedRow != -1) {
                        // get recipe list from
                        List<Recipe> recipes = ((RecipeTableModel) recipeList.getModel()).getRecipes();
                        Recipe selectedRecipe = recipes.get(selectedRow);
                        recipeSelected(selectedRecipe);
                    }
                }
            }
        });
    }

    /**
     * Display recipes in the JTable with the latest RecipeBook data
     *  - called when the frontend is first loaded
     *  - called when save button clicked
     */
    private void displayRecipes() {
        // get latest recipes
        ArrayList<Recipe> recipes = controller.getRecipes();
        tableModel.updateData(recipes);

        // clear search field
        searchField.setText("");
    }

    /**
     * Event handler for when a recipe is selected in the JTable
     *   - populate the recipe form with the selected recipe's data
     * @param recipe the selected recipe
     */
    private void recipeSelected(Recipe recipe) {
        // set newRecipeClicked to false
        newRecipeClicked = false;
        // populate recipe form
        recipeNameField.setText(recipe.getName());
        recipeCookTimeField.setText(recipe.getCookTime());
        recipeIngredientsField.setText(recipe.getIngredients());
        recipeStepsField.setText(recipe.getSteps());
        recipeNotesField.setText(recipe.getNotes());
        ratingLabel.setText(String.valueOf(recipe.getRating()));
    }

    private void searchBtnClicked() {
        // get query
        String query = searchField.getText();

        // search
        ArrayList<Recipe> searchResults = controller.searchRecipe(query);
        
        // display results
        // RecipeTableModel tableModel = new RecipeTableModel(searchResults);
        // recipeList.setModel(tableModel);
        tableModel.updateData(searchResults);
    }

    private void newBtnClicked() {
        // set newRecipeClicked to true
        newRecipeClicked = true;

        // clear all fields in recipeForm
        recipeNameField.setText("");
        recipeCookTimeField.setText("");
        recipeIngredientsField.setText("");
        recipeStepsField.setText("");
        recipeNotesField.setText("");
        ratingLabel.setText("3");

    }

    private void saveBtnClicked() {
        // update recipe
        String name = recipeNameField.getText();
        String cookTime = recipeCookTimeField.getText();
        String ingredients = recipeIngredientsField.getText();
        String steps = recipeStepsField.getText();
        String notes = recipeNotesField.getText();
        int rating = Integer.parseInt(ratingLabel.getText());

        // validate recipe
        if(!controller.validateRecipe(name, ingredients, steps)){
            JOptionPane.showMessageDialog(this, "Required Fields cannot be empty!", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (newRecipeClicked) {
            // add new recipe to controller
            try {
                controller.addNewRecipe(name, cookTime, ingredients, steps, notes, rating);
            } catch (Exception e) {
                e.printStackTrace();
            }
            newRecipeClicked = false;
        }
        else{
            // get recipe id
            int selectedRow = recipeList.getSelectedRow();
            if (selectedRow == -1) {
                System.out.println("No recipe selected to save");
                return;
            }
            List<Recipe> recipes = ((RecipeTableModel) recipeList.getModel()).getRecipes();
            int id = recipes.get(selectedRow).getId();

            // update
            try {
                controller.updateRecipe(id, name, cookTime, ingredients, steps, notes, rating);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // refresh table (with latest data)
        displayRecipes();

        // confirm save
        JOptionPane.showMessageDialog(this, "Recipe saved successfully!", "Save Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteBtnClicked() {
        // get current selected recipe
        int selectedRow = recipeList.getSelectedRow();
        if (selectedRow == -1) {
            System.out.println("No recipe selected to delete");
            return;
        }
        List<Recipe> recipes = ((RecipeTableModel) recipeList.getModel()).getRecipes();
        int id = recipes.get(selectedRow).getId();

        // delete
        try {
            controller.deleteRecipe(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // refresh table (with latest data)
        displayRecipes();

        // confirm delete
        JOptionPane.showMessageDialog(this, "Recipe deleted successfully!", "Delete Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    private void upVoteBtnClicked() {
        // if no recipe selected: ignore
        int selectedRow = recipeList.getSelectedRow();
        if (selectedRow == -1) {
            System.out.println("No recipe selected to rate");
            return;
        }

        // get curr rating
        int currRating = Integer.parseInt(ratingLabel.getText());
        if (currRating < 5) {
            currRating++;
        }
        ratingLabel.setText(String.valueOf(currRating));
    }

    private void downVoteBtnClicked() {
        // if no recipe selected: ignore
        int selectedRow = recipeList.getSelectedRow();
        if (selectedRow == -1) {
            System.out.println("No recipe selected to rate");
            return;
        }

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