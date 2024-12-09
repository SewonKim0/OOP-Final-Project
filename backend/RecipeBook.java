package backend;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson; // Assuming Gson library is used for JSON handling

public class RecipeBook {
    private List<Recipe> recipes;
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

    // Delete a recipe by ID
    public void deleteRecipe(int id) {
        recipes.removeIf(recipe -> recipe.getId() == id);
    }

    // Search for a recipe by name or ingredients
    public List<Recipe> searchForRecipe(String query) {
        List<Recipe> result = new ArrayList<>();
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
    public List<Recipe> getRecipes() {
        return recipes;
    }

    public int getCount() {
        return count;
    }
}
