package ru.gb.family_tree;

import ru.gb.family_tree.family_tree.FamilyTree;
import ru.gb.family_tree.human.Gender;
import ru.gb.family_tree.human.Human;
import ru.gb.family_tree.writer.FileHandler;

import java.time.LocalDate;

public class Main {
    final static String filePath = "src/main/java/ru/gb/family_tree/writer/tree.txt";

    public static void main(String[] args) {
        FamilyTree tree = load();
//        FamilyTree tree = testTree();
//        save(tree);

        System.out.println(tree);
    }

    private static FamilyTree load(){
        FileHandler fileHandler = new FileHandler(filePath);
        return (FamilyTree) fileHandler.read();
    }

    private static void save(FamilyTree familyTree){
        FileHandler fileHandler = new FileHandler(filePath);
        fileHandler.save(familyTree);
    }

    private static FamilyTree testTree(){
        FamilyTree tree = new FamilyTree();

        Human vasya = new Human("Василий", Gender.Male, LocalDate.of(1963,12,10));
        Human masha = new Human("Мария", Gender.Female, LocalDate.of(1963,9,15));

        tree.add(vasya);
        tree.add(masha);
        tree.setWedding(vasya, masha);

        Human christina = new Human("Кристина", Gender.Female, LocalDate.of(1988,7,5), vasya, masha);
        Human semyon = new Human("Семен", Gender.Male, LocalDate.of(1991,1,25), vasya, masha);

        tree.add(christina);
        tree.add(semyon);

        Human grandMother = new Human("Лариса", Gender.Female, LocalDate.of(1945,9,1));
        grandMother.addChild(vasya);

        tree.add(grandMother);
        return tree;

    }

}
