package frontend;

import backend.*;
import javax.swing.table.AbstractTableModel;
import java.util.*;

public class RecipeTableModel extends AbstractTableModel {
    private ArrayList<Recipe> recipes;
    private String[] columnNames = { "Id", "Name", "Cook Time", "Rating", "Ingredients", "Steps", "Notes", "Date" };

    public RecipeTableModel(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public int getRowCount() {
        return recipes.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Recipe recipe = recipes.get(rowIndex);
        switch (columnIndex) {
            case 0: return recipe.getId();
            case 1: return recipe.getRecipeName();
            case 2: return recipe.getTotalCookTime();
            case 3: return recipe.getRating();
            case 4: return recipe.getIngredients();
            case 5: return recipe.getSteps();
            case 6: return recipe.getNotes();
            case 7: return recipe.getDate();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
}