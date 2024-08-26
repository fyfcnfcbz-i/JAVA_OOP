package ru.gb.family_tree.model;


import ru.gb.family_tree.model.family_tree.FamilyTree;
import ru.gb.family_tree.model.human.Gender;
import ru.gb.family_tree.model.human.Human;
import ru.gb.family_tree.model.writer.FileHandler;

import java.time.LocalDate;
import java.util.List;


public class Service {

    private int idHuman;
    private FamilyTree<Human> familyTree;
    private final static String filePath = "src/main/resources/tree.txt";


    public Service() {
        this.familyTree = new FamilyTree<>();
        this.idHuman = 0;
    }

    // Добавление человека в семейное древо
    public void addHuman(String name, Gender gender, LocalDate birthDate, LocalDate deathDate, String fatherName, String motherName) {
        Human father = findHumanByName(fatherName);
        Human mother = findHumanByName(motherName);

        Human human = new Human(name, gender, birthDate, deathDate, father, mother);
        human.setId(idHuman++);

        familyTree.add(human);

        if (father != null) {
            father.addChild(human);
        }
        if (mother != null) {
            mother.addChild(human);
        }
    }
    public void addHuman(String name, Gender gender, LocalDate birthDate) {
        addHuman(name, gender, birthDate, null, null, null);
    }
    public void addHuman(String name, Gender gender, LocalDate birthDate, LocalDate deathDate) {
        addHuman(name, gender, birthDate, deathDate, null, null);
    }

    // Поиск человека по имени
    private Human findHumanByName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        List<Human> humans = familyTree.getByName(name);
        return humans.isEmpty() ? null : humans.get(0); // Предполагаем, что имя уникально или берем первого найденного
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
        try {
            fileHandler.save(familyTree);
            System.out.println("Древо успешно сохранено.");
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении древа: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Загрузка семейного древа
    public void loadTree() {
        FileHandler fileHandler = new FileHandler(filePath);
        FamilyTree<Human> loadedTree = (FamilyTree<Human>) fileHandler.read();
        if (loadedTree != null) {
            this.familyTree = loadedTree;
            this.idHuman = familyTree.getMaxId() + 1;  // Устанавливаем idHuman как максимальный существующий ID + 1
        } else {
            System.out.println("Не удалось загрузить дерево из файла. Создано новое пустое дерево.");
            this.familyTree = new FamilyTree<>();
            this.idHuman = 0; // Устанавливаем idHuman в 0, если дерево пустое
        }
    }

}
