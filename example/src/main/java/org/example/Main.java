package org.example;

import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //inputData();
        //simpleArray();
        //sortPerson();
        String userName="root";
        String password="123456";
        String host = "localhost";
        String port = "3306";
        String database = "java_spu111";
        String strConn ="jdbc:mariadb://"+host+":"+port+"/"+database;
        //createCategory(strConn, userName, password);
        //insertCategory(strConn, userName, password);
        var list = listCategories(strConn, userName, password);
        for(var c : list) {
            System.out.println(c);
        }
    }

    private static void createCategory(String strConn, String userName, String password)
    {
        try(Connection conn = DriverManager.getConnection(strConn,userName,password)) {
            Statement statement = conn.createStatement();
            // SQL query to create the "category" table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS categories ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "name VARCHAR(255) NOT NULL,"
                    + "description TEXT"
                    + ")";
            // Execute the SQL query
            statement.execute(createTableSQL);
            // Close the resources
            statement.close();
            System.out.println("В БД створено таблицю categories");
        }
        catch(Exception ex) {
            System.out.println("Error connection: "+ex.getMessage());
        }
    }

    private static List<CategoryItem> listCategories(String strConn, String userName, String password)
    {
        try(Connection conn = DriverManager.getConnection(strConn,userName,password)) {
            Statement statement = conn.createStatement();
            // SQL query to select all categories
            String selectQuery = "SELECT * FROM categories";
            // Create a PreparedStatement
            PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);

            // Execute the SELECT query
            ResultSet resultSet = preparedStatement.executeQuery();

            List<CategoryItem> list = new ArrayList<>();
            // Process the ResultSet
            while (resultSet.next()) {
                CategoryItem category = new CategoryItem();
                // Retrieve data from the current row
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
                list.add(category);
            }
            // Close the resources
            resultSet.close();
            preparedStatement.close();
            return list;
        }
        catch(Exception ex) {
            System.out.println("Помилка читання списку даних: "+ex.getMessage());
            return null;
        }
    }

    private static void insertCategory(String strConn, String userName, String password) {
        Scanner input = new Scanner(System.in);
        CategoryCreate categoryCreate = new CategoryCreate();
        System.out.println("Вкажіть назву категорії:");
        categoryCreate.setName(input.nextLine());
        System.out.println("Вкажіть опис категорії:");
        categoryCreate.setDescription(input.nextLine());

        try(Connection conn = DriverManager.getConnection(strConn,userName,password)) {
            Statement statement = conn.createStatement();
            String insertQuery = "INSERT INTO categories (name, description) VALUES (?, ?)";
            // Create a PreparedStatement
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
            // Set values for the placeholders
            preparedStatement.setString(1, categoryCreate.getName());
            preparedStatement.setString(2, categoryCreate.getDescription());
            // Execute the SQL query
            int rowsAffected = preparedStatement.executeUpdate();
            // Close the resources
            preparedStatement.close();
            System.out.println("Rows affected: " + rowsAffected);
            System.out.println("Category inserted successfully.");
        }
        catch(Exception ex) {
            System.out.println("Помилка створення категорії: "+ex.getMessage());
        }
    }

    private static void sortPerson() {
        Person[] list = {
                new Person("Іван","Мельник"),
                new Person("Мальвіна","Морква"),
                new Person("Петро","Підкаблучник"),
                new Person("Олег","Гризун"),
        };
        System.out.println("---Звичайни список--");
        for (var item : list) {
            System.out.println(item);
        }
        Arrays.sort(list);
        System.out.println("---Сортований список--");
        for (var item : list) {
            System.out.println(item);
        }
    }
    private static void simpleArray() {
        Scanner scanner = new Scanner(System.in);
        int n = 10;
        int []mas = new int[10];
        for (int i=0;i<n;i++)
            mas[i]=getRandom(-5, 30);

        System.out.println("\n----Звичайний масив----");
        for(int item : mas) {
            System.out.printf("%d\t",item);
        }
        Arrays.sort(mas);
        System.out.println("\n----Відсортований масив масив----");
        for(int item : mas) {
            System.out.printf("%d\t",item);
        }
        int count=0;
        for (int item : mas) {
            if(item>=0)
                count++;
        }
        System.out.println("\nКількість додатніх чисел: "+count);
    }

    private static int getRandom(int min, int max) {
        // Create an instance of the Random class
        Random random = new Random();
        // Generate a random value between min (inclusive) and max (exclusive)
        return random.nextInt(max - min) + min;
    }

    public static void inputData() {
        Scanner input = new Scanner(System.in);
        System.out.println("Як Вас звать?");
        String name = input.nextLine();
        System.out.println("Привіт "+name+"!");
    }
}