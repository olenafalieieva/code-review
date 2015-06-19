package ua.goit.servlets.dao;

import java.util.HashMap;
import java.util.Map;
import ua.goit.servlets.model.Category;

public class CategoriesList {
    static Map<Integer, Category> categoriesMap = new HashMap<Integer, Category>();
	public static Map<Integer, Category> getAllCategories() {
		final Category it = new Category(1, "IT");
		{
			it.addProject(1, "IT.1");
			it.addProject(2, "IT.2");
			it.addProject(3, "IT.3");
		}
		final Category art = new Category(2, "ART");
		{
			art.addProject(1, "Art.1");
			art.addProject(2, "Art.2");
			art.addProject(3, "Art.3");
		}
		
		categoriesMap.put(it.getID(), it);
		categoriesMap.put(art.getID(), art);
		return categoriesMap;
	}
}