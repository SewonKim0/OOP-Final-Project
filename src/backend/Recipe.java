package backend;

import java.util.Date;

public class Recipe {
	private static int idCounter = 0; // static variable to keep track of ids
    private int id; // instance variable for the unique id of each Recipe object
    private String recipeName;
    private String totalCookTime;
    private int rating;
    private String ingredients;
    private String steps;
    private String notes;
    private Date date;

    public Recipe(String recipeName, String totalCookTime, int rating, String ingredients, String steps, String notes) {
        this.id = idCounter++; // assigns this.id the current value of idCounter, then increments idCounter by 1
        this.recipeName = recipeName;
        this.totalCookTime = totalCookTime;
        this.rating = rating;
        this.ingredients = ingredients;
        this.steps = steps;
        this.notes = notes;
        this.date = new Date(); // assign current date and time
    }

	// default constructor
	public Recipe() {
		this.id = idCounter++;
		this.recipeName = "";
		this.totalCookTime = "";
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

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getTotalCookTime() {
		return totalCookTime;
	}

	public void setTotalCookTime(String totalCookTime) {
		this.totalCookTime = totalCookTime;
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

}
