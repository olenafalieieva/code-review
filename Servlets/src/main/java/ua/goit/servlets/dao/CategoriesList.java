package ua.goit.servlets.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ua.goit.servlets.model.Category;


public class CategoriesList {

	public static Map<String, Category> newCategoryInstance() {
		Category IT = new Category(1, "IT");
		{
			IT.addProject(1, "IT.1");
			IT.addProject(2, "IT.2");
			IT.addProject(1, "IT.3");
		}
		Category art = new Category(2, "ART");
		{
			art.addProject(1, "Art.1");
			art.addProject(2, "Art.2");
			art.addProject(2, "Art.3");
		}

		Map<String, Category> categories = new  HashMap<String, Category>();
		{
			categories.put("IT", IT);
			categories.put("ART", art);
		}
		return categories;
	}

	public List<Category> getAll(Map<String, Category> categories) {
		List<Category> resultList = new ArrayList<Category>();
		Iterator<Map.Entry<String, Category>> entries = categories.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, Category> entry = entries.next();
			resultList.add(entry.getValue());
		}
		return resultList;
	}
}
