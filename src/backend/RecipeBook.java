package backend;

import com.google.gson.Gson;

import java.util.*;

public class RecipeBook {
    private ArrayList<Recipe> recipes;
    private int count;
    private int topId;

    public RecipeBook() {
        this.recipes = new ArrayList<>();
        this.count = 0;
        topId = 0;
    }

    // Add a recipe to the book
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        topId = Math.max(topId, recipe.getId());
        count++;
    }

    // Update a recipe in the book
    public void updateRecipe(int id, String name, String cookTime, String ingredients, String steps, String notes, int rating) {
        Recipe toUpdate = getRecipeById(id);
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

    // Upvote a recipe by ID
    public void upvoteRecipe(int id) {
        Recipe r = getRecipeById(id);
        int currentRating = r.getRating();
        r.setRating(currentRating+1);
    }

    // Downvote a recipe by ID
    public void downvoteRecipe(int id) {
        Recipe r = getRecipeById(id);
        int currentRating = r.getRating();
        r.setRating(currentRating-1);
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
        RecipeBook r = gson.fromJson(jsonStr, RecipeBook.class);
        Recipe.setIdCounter(r.topId+1);
        return r;
    }


    // Getters
    // Allow user to search for Recipe object by id field with for loop
    public Recipe getRecipeById(int id) {
        for (Recipe r : recipes) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    // Return ArrayList of all Recipe objects
    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    // Return number of Recipe objects in RecipeBook
    public int getCount() {
        return count;
    }
}
