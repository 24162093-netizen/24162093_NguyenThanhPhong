package vn.service.impl;

import java.util.List;

import vn.dao.CategoryDao;
import vn.entity.Category;
import vn.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDao();

    @Override
    public void insert(Category category) {
        categoryDao.insert(category);
    }

    @Override
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Override
    public void delete(int id) {
        categoryDao.delete(id);
    }

    @Override
    public Category findById(int id) {
        return categoryDao.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}