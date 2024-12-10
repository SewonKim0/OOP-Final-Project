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
        try {
            recipeBook = fileSystem.load();
        }
        catch (IOException ex) {
            recipeBook = new RecipeBook();
        }
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
        return recipeBook.getRecipes();
    }

    public void saveRecipe(String name, String cookTime, String ingredients, String steps, String notes, int rating) {
        Recipe r = new Recipe(name, cookTime, rating, ingredients, steps, notes);
        recipeBook.addRecipe(r);
    }

    public List<Recipe> searchRecipe(String anyString) {
        return recipeBook.searchForRecipe(anyString);
    }

    public void deleteRecipe(int id) {
        recipeBook.deleteRecipe(id);
    }
}