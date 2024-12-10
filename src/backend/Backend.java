package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Backend {
	private String currentPath;

    public Backend(String path) {
        this.currentPath = path;
    }
    
    // Save the RecipeBook to a file
//    public void save(RecipeBook recipeBook) throws IOException {
//        try (FileWriter writer = new FileWriter(currentPath)) {
//            writer.write(recipeBook.toJson());
//        }
//    }
    public void save(String data) throws IOException {
        try (FileWriter writer = new FileWriter(currentPath)) {
            writer.write(data);
        }
    }
    
	// Load the RecipeBook from a file
    public String load() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(currentPath))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            return jsonBuilder.toString();
        }
    }
    
    
}
