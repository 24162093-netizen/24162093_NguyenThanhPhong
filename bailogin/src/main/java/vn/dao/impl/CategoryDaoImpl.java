package vn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.connection.DBconnection;
import vn.dao.CategoryDao;
import vn.model.Category;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public void insert(Category category) {

        String sql =
                "INSERT INTO category(cate_name, icons) "
                + "VALUES (?, ?)";

        try (
            Connection conn =
                    new DBconnection().getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    category.getName()
            );

            ps.setString(
                    2,
                    category.getIcon()
            );

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    @Override
    public void edit(Category category) {

        String sql =
                "UPDATE category "
                + "SET cate_name = ?, icons = ? "
                + "WHERE cate_id = ?";

        try (
            Connection conn =
                    new DBconnection().getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    category.getName()
            );

            ps.setString(
                    2,
                    category.getIcon()
            );

            ps.setInt(
                    3,
                    category.getId()
            );

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    @Override
    public void delete(int id) {

        String sql =
                "DELETE FROM category "
                + "WHERE cate_id = ?";

        try (
            Connection conn =
                    new DBconnection().getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    @Override
    public Category get(int id) {

        String sql =
                "SELECT * FROM category "
                + "WHERE cate_id = ?";

        try (
            Connection conn =
                    new DBconnection().getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                Category category =
                        new Category();

                category.setId(
                        rs.getInt("cate_id")
                );

                category.setName(
                        rs.getString("cate_name")
                );

                category.setIcon(
                        rs.getString("icons")
                );

                return category;
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public Category get(String name) {

        String sql =
                "SELECT * FROM category "
                + "WHERE cate_name = ?";

        try (
            Connection conn =
                    new DBconnection().getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)
        ) {

            ps.setString(1, name);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                Category category =
                        new Category();

                category.setId(
                        rs.getInt("cate_id")
                );

                category.setName(
                        rs.getString("cate_name")
                );

                category.setIcon(
                        rs.getString("icons")
                );

                return category;
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public List<Category> getAll() {

        List<Category> list =
                new ArrayList<>();

        String sql =
                "SELECT * FROM category";

        try (
            Connection conn =
                    new DBconnection().getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery()
        ) {

            while (rs.next()) {

                Category category =
                        new Category();

                category.setId(
                        rs.getInt("cate_id")
                );

                category.setName(
                        rs.getString("cate_name")
                );

                category.setIcon(
                        rs.getString("icons")
                );

                list.add(category);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return list;
    }

    @Override
    public List<Category> search(String keyword) {

        List<Category> list =
                new ArrayList<>();

        String sql =
                "SELECT * FROM category "
                + "WHERE cate_name LIKE ?";

        try (
            Connection conn =
                    new DBconnection().getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    "%" + keyword + "%"
            );

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Category category =
                        new Category();

                category.setId(
                        rs.getInt("cate_id")
                );

                category.setName(
                        rs.getString("cate_name")
                );

                category.setIcon(
                        rs.getString("icons")
                );

                list.add(category);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return list;
    }
}