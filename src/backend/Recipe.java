package backend;

import java.util.Date;

public class Recipe {
	protected static int idCounter = 0; // static variable to keep track of ids
    private int id; // instance variable for the unique id of each Recipe object
    private String name;
    private String cookTime;
    private int rating;
    private String ingredients;
    private String steps;
    private String notes;
    private Date date;

    public Recipe(String name, String cookTime, String ingredients, String steps, String notes, int rating) {
        this.id = idCounter++; // assigns this.id the current value of idCounter, then increments idCounter by 1
		this.name = name;
        this.cookTime = cookTime;
        this.rating = rating;
        this.ingredients = ingredients;
        this.steps = steps;
        this.notes = notes;
        this.date = new Date(); // assign current date and time
    }

	// default constructor
	public Recipe() {
		this.id = idCounter++;
		this.name = "";
		this.cookTime = "";
		this.rating = 3;
		this.ingredients = "";
		this.steps = "";
		this.notes = "";
		this.date = new Date();
	}

	public int getId() {
		return id;
	}

	// id should not be changed after it is given, no set method

	// idCounter is protected because it must be updated according to topId in RecipeBook
	protected int getIdCounter() { return idCounter; }

	protected static void setIdCounter(int newCount) { idCounter = newCount; }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCookTime() {
		return cookTime;
	}

	public void setCookTime(String totalCookTime) {
		this.cookTime = cookTime;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		if (rating >= 0 && rating <= 5) { // Rating cannot be greater than 5 or less than 0
            this.rating = rating;
        }
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", cookTime=" + cookTime + ", rating="
				+ rating + ", ingredients=" + ingredients + ", steps=" + steps + ", notes=" + notes + ", date=" + date
				+ "]";
	}

}
