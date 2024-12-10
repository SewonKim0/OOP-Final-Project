package backend;

import com.google.gson.Gson;

import java.util.*;

public class RecipeBook {
    private ArrayList<Recipe> recipes;
    private int count;

    public RecipeBook() {
        this.recipes = new ArrayList<>();
        this.count = 0;
    }

    // Add a recipe to the book
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        count++;
    }

    // Update a recipe in the book
    public void updateRecipe(int id, String name, String cookTime, String ingredients, String steps, String notes, int rating) {
        Recipe toUpdate = null;
        for (Recipe r : recipes) {
            if (r.getId() == id) {
                toUpdate = r;
                break;
            }
        }
        if (toUpdate != null) {
            toUpdate.setRecipeName(name);
            toUpdate.setTotalCookTime(cookTime);
            toUpdate.setIngredients(ingredients);
            toUpdate.setSteps(steps);
            toUpdate.setNotes(notes);
            toUpdate.setRating(rating);
            toUpdate.setDate(new Date());
        }
    }

    // Delete a recipe by ID
    public void deleteRecipe(int id) {
        recipes.removeIf(recipe -> recipe.getId() == id);
    }

    // Search for a recipe by name or ingredients
    public ArrayList<Recipe> searchForRecipe(String query) {
        ArrayList<Recipe> result = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getRecipeName().toLowerCase().contains(query.toLowerCase()) ||
                recipe.getIngredients().toLowerCase().contains(query.toLowerCase())) {
                result.add(recipe);
            }
        }
        return result;
    }

    // Convert RecipeBook to JSON
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    // Convert JSON to RecipeBook
    public static RecipeBook fromJson(String jsonStr) {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, RecipeBook.class);
    }


    // Getters
    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public int getCount() {
        return count;
    }
}
