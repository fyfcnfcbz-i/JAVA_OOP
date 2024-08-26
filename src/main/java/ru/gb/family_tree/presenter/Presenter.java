package ru.gb.family_tree.presenter;

import ru.gb.family_tree.model.Service;
import ru.gb.family_tree.model.human.Gender;
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
    public void addHuman(String name, Gender gender, LocalDate birthDate, LocalDate deathDate, String father, String mother) {
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
