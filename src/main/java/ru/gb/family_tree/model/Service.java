package ru.gb.family_tree.model;


import ru.gb.family_tree.model.family_tree.FamilyTree;
import ru.gb.family_tree.model.human.Gender;
import ru.gb.family_tree.model.human.Human;
import ru.gb.family_tree.model.writer.FileHandler;

import java.time.LocalDate;


public class Service {

    private int idHuman;
    private FamilyTree<Human> familyTree;
    private final static String filePath = "src/main/resources/tree.txt";


    public Service() {
        this.familyTree = new FamilyTree<>();
        this.idHuman = 0;
    }

    // Добавление человека в семейное древо
    public void addHuman(String name, Gender gender, LocalDate birthDate, LocalDate deathDate, Human father, Human mother) {
        Human human = new Human(name, gender, birthDate, deathDate, father, mother);
        human.setId(idHuman++);
        familyTree.add(human);
        if (father != null && !father.getChildren().contains(human)) {
            father.addChild(human);
        }
        if (mother != null && !mother.getChildren().contains(human)) {
            mother.addChild(human);
        }
    }

    // Сортировка по имени
    public void sortByName() {
        familyTree.sortByName();
    }

    // Сортировка по дате рождения
    public void sortByBirthDate() {
        familyTree.sortByBirthDate();
    }

    // Получение информации о всех людях в древе
    public String getHumansInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Семейное древо:\n");

        for (Human human : familyTree) {
            stringBuilder.append(human);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }


//    // Установка брака между двумя людьми
//    public void setWedding(long humanId1, long humanId2) {
//        Human human1 = familyTree.getById(humanId1);
//        Human human2 = familyTree.getById(humanId2);
//        if (human1 != null && human2 != null) {
//            familyTree.setWedding(human1, human2);
//        }
//    }



    // Сохранение семейного древа
    public void saveTree() {
        FileHandler fileHandler = new FileHandler(filePath);
        fileHandler.save(familyTree);
    }

    // Загрузка семейного древа
    public void loadTree() {
        FileHandler fileHandler = new FileHandler(filePath);
        familyTree = (FamilyTree<Human>) fileHandler.read();

    }

}
