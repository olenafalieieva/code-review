package ua.goit.servlets.dao;

import java.util.ArrayList;
import java.util.List;
import ua.goit.servlets.model.Category;

public class CategoriesList {

	public static List<Category> newCategoryInstance() {
		final Category it = new Category("IT");
		{
			it.addProject(1, "IT.1");
			it.addProject(2, "IT.2");
			it.addProject(1, "IT.3");
		}
		final Category art = new Category("ART");
		{
			art.addProject(1, "Art.1");
			art.addProject(2, "Art.2");
			art.addProject(2, "Art.3");
		}
		List<Category> categoriesList = new ArrayList<Category>();
		categoriesList.add(it);
		categoriesList.add(art);
		return categoriesList;
	}
}