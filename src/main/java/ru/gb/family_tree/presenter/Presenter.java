package ru.gb.family_tree.presenter;

import ru.gb.family_tree.model.service.Service;
import ru.gb.family_tree.model.human.Gender;
import ru.gb.family_tree.model.human.Human;
import ru.gb.family_tree.view.View;

import java.time.LocalDate;


public class Presenter {

    private View view;
    private Service service;

    public Presenter(View view) {
        this.view = view;
        service = new Service();
    }

    // Получение информации о всех людях в семейном древе
    public void getHumansListInfo() {
        String info = service.getHumansInfo();
        view.printAnswer(info);
    }

    // Добавление человека в семейное древо
    public void addHuman(String name, Gender gender, LocalDate birthDate, LocalDate deathDate, Human father, Human mother) {
        service.addHuman(name, gender, birthDate, deathDate, father, mother);
        view.printAnswer("Человек добавлен в семейное древо.");
        getHumansListInfo();
    }

    // Сортировка по имени
    public void sortByName() {
        service.sortByName();
        getHumansListInfo();
    }

    // Сортировка по дате рождения
    public void sortByBirthDate() {
        service.sortByBirthDate();
        getHumansListInfo();
    }

//    // Установка брака между двумя людьми
//    public void setWedding(Long humanId1, Long humanId2) {
//        service.setWedding(humanId1, humanId2);
//        view.printAnswer("Брак установлен.");
//        getHumansListInfo();
//    }

    // Установка брака между двумя людьми
    public void setWedding(long humanId1, long humanId2) {

        boolean success = service.setWedding(humanId1, humanId2);
        if (success) {
            view.printAnswer("Брак успешно установлен.");
        } else {
            view.printAnswer("Не удалось установить брак. Проверьте ID людей.");
        }
        getHumansListInfo();
    }
    // Развод между двумя людьми
    public void setDivorce(long humanId1, long humanId2) {

        boolean success = service.setDivorce(humanId1, humanId2);
        if (success) {
            view.printAnswer("Развод успешно установлен.");
        } else {
            view.printAnswer("Не удалось установить развод. Проверьте ID людей.");
        }
        getHumansListInfo();
    }

    public void addParent(long childId, long parentId) {
        boolean success = service.addParent(childId, parentId);
        if (success) {
            view.printAnswer("Родитель успешно добавлен.");
        } else {
            view.printAnswer("Не удалось добавить родителя. Проверьте ID.");
        }
        getHumansListInfo();
    }

    public void addChild(long parentId, long childId) {
        boolean success = service.addChild(parentId, childId);
        if (success) {
            view.printAnswer("Ребенок успешно добавлен.");
        } else {
            view.printAnswer("Не удалось добавить ребенка. Проверьте ID.");
        }
        getHumansListInfo();
    }


    // Сохранение семейного древа
    public void saveTree() {
        service.saveTree();
        view.printAnswer("Семейное древо сохранено.");
        getHumansListInfo();
    }

    // Загрузка семейного древа
    public void loadTree() {
        service.loadTree();
        view.printAnswer("Семейное древо загружено.");
        getHumansListInfo();
    }
}
