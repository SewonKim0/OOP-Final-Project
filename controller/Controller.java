package controller;
import backend.*;

import java.io.IOException;
import java.util.*;

public class Controller {
    private Backend fileSystem;
    private RecipeBook recipeBook;

    private Controller() {
        fileSystem = new Backend("../backend/recipe_book.json");
        try {
            recipeBook = fileSystem.load()
        }
        catch (IOException) {
            recipeBook = new RecipeBook();
        }
    }

    // singleton pattern
    // this class can only have one instance
    private static Controller instance = null;
    public static controller.Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public List<Recipe> getRecipes() {
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