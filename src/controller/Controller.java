package controller;

import backend.Backend;
import backend.Recipe;
import backend.RecipeBook;

import java.io.IOException;
import java.util.*;

public class Controller {
    private Backend fileSystem;
    private RecipeBook recipeBook;

    private Controller() {
        fileSystem = new Backend("../backend/recipe_book.json");
        recipeBook = null; // read from disk will take a long time, only call when necessary
    }

    // singleton pattern
    // this class can only have one instance
    private static Controller instance = null;
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public ArrayList<Recipe> getRecipes() {
        if (recipeBook == null) {
            recipeBook = readRecipeBookFromFile();
        }
        return recipeBook.getRecipes();
    }

    // called by Frontend to add a blank new recipe
    public void addNewRecipe() throws IOException {
        Recipe r = new Recipe();
        recipeBook.addRecipe(r);
        writeRecipeBookToFile();
    }

    public void updateRecipe(int id, String name, String cookTime, String ingredients, String steps, String notes, int rating) throws IOException {
        recipeBook.updateRecipe(id, name, cookTime, ingredients, steps, notes, rating);
        writeRecipeBookToFile();
    }

    public List<Recipe> searchRecipe(String anyString) {
        return recipeBook.searchForRecipe(anyString);
    }

    public void deleteRecipe(int id) throws IOException {
        recipeBook.deleteRecipe(id);
        writeRecipeBookToFile();
    }

    public void writeRecipeBookToFile() throws IOException {
        fileSystem.save(recipeBook.toJson());
    }

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