package service;

import dao.CategoryDAO;
import dao.RecordDAO;
import entity.Category;
import entity.Record;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CategoryService {
    CategoryDAO categoryDao = new CategoryDAO();
    RecordDAO recordDao = new RecordDAO();

    public List<Category> list() {
        List<Category> cs= categoryDao.list();
        for (Category c : cs) {
            List<Record> rs =recordDao.list(c.id);
            c.recordNumber=rs.size();
        }
        cs.sort(new Comparator<Category>() {
            @Override
            public int compare(Category c1, Category c2) {
                return c2.recordNumber - c1.recordNumber;
            }
        });

        return cs;
    }

    public void add(String name){
        Category c = new Category();
        c.setName(name);
        categoryDao.add(c);
    }

    public void update(int id, String name){
        Category category =  new Category();
        category.id = id;
        category.name = name;
        categoryDao.update(category);
    }

    public void delete(int id){
        categoryDao.delete(id);
    }


}
