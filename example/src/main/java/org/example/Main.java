package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //inputData();
        //simpleArray();
        //sortPerson();
        //jdbc - ado.net
        
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