package controller;

import backend.Backend;
import backend.Recipe;
import backend.RecipeBook;

import java.io.IOException;
import java.util.*;

/*
This class serves as the connection between the frontend UI, the recipe classes, and the disk / file system.
 */
public class Controller {
    private Backend fileSystem;
    private RecipeBook recipeBook;

    /*
    constructor sets the file system path
    sets RecipeBook class member variable to null
    private because of singleton pattern
     */
    private Controller() {
        fileSystem = new Backend("../backend/recipe_book.json");
        recipeBook = null; // read from disk will take a long time, only call when necessary
    }

    /*
    singleton pattern: this class can only have one instance
    check if instance of class already exists
    if it does, return existing instance, otherwise create one
     */
    private static Controller instance = null;
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    /*
    called by frontend UI to fill JTable
    if RecipeBook class member has not been populated yet, read from file system
    call RecipeBook class getRecipes() method
     */
    public ArrayList<Recipe> getRecipes() {
        if (recipeBook == null) {
            recipeBook = readRecipeBookFromFile();
        }
        return recipeBook.getRecipes();
    }

    /*
    called by frontend UI the "New Recipe" button is pressed (for a blank new recipe template)
    creates a blank Recipe object and adds it to the RecipeBook
    saves to file system
     */
    public void addNewRecipe() throws IOException {
        Recipe r = new Recipe();
        recipeBook.addRecipe(r);
        writeRecipeBookToFile();
    }

    /*
    called by frontend UI when the "Save" button is pressed
    calls RecipeBook updateRecipe() method
    saves to file system
     */
    public void updateRecipe(int id, String name, String cookTime, String ingredients, String steps, String notes, int rating) throws IOException {
        recipeBook.updateRecipe(id, name, cookTime, ingredients, steps, notes, rating);
        writeRecipeBookToFile();
    }

    /*
    called by frontend UI when the "Search" button is pressed
    calls RecipeBook searchForRecipe() method
     */
    public ArrayList<Recipe> searchRecipe(String anyString) {
        return recipeBook.searchForRecipe(anyString);
    }

    /*
    called by frontend UI when the "Delete" button is pressed
    calls RecipeBook deleteRecipe() method
    saves to file system
     */
    public void deleteRecipe(int id) throws IOException {
        recipeBook.deleteRecipe(id);
        writeRecipeBookToFile();
    }

    /*
    called by frontend UI when the "Up Vote" button is pressed
    calls RecipeBook upvoteRecipe() method
    saves to file system
     */
    public void upvoteRecipe(int id) throws IOException {
        recipeBook.upvoteRecipe(id);
        writeRecipeBookToFile();
    }

    /*
    called by frontend UI when the "Down Vote" button is pressed
    calls RecipeBook downvoteRecipe() method
    saves to file system
     */
    public void downvoteRecipe(int id) throws IOException {
        recipeBook.downvoteRecipe(id);
        writeRecipeBookToFile();
    }

    /*
    save current version of RecipeBook class member variable to file system
     */
    public void writeRecipeBookToFile() throws IOException {
        fileSystem.save(recipeBook.toJson());
    }

    /*
    reads most recent saved version of RecipeBook class member variable from file system
     */
    public RecipeBook readRecipeBookFromFile() {
        RecipeBook temp = null;
        try {
            temp = RecipeBook.fromJson(fileSystem.load());
        }
        catch (IOException ex) {
            temp = new RecipeBook();
        }
        return temp;
    }
}