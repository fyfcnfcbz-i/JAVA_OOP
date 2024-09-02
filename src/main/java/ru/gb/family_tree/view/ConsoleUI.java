package ru.gb.family_tree.view;

import ru.gb.family_tree.model.human.Gender;
import ru.gb.family_tree.model.human.Human;
import ru.gb.family_tree.model.service.Service;
import ru.gb.family_tree.model.writer.FileHandler;
import ru.gb.family_tree.model.writer.Writer;
import ru.gb.family_tree.presenter.Presenter;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleUI implements View{

    private static final String INPUT_ERROR = "Вы ввели неверное значение";

    private Scanner scanner;
    private Presenter presenter;
    private boolean work;
    private MainMenu menu;





    public ConsoleUI() {

        presenter = new Presenter(this);
        scanner = new Scanner(System.in);
        work = true;
        menu = new MainMenu(this);

    }

    @Override
    public void start() {
        while (work) {
            printMenu();
            execute();
        }
    }


    @Override
    public void printAnswer(String text) {
        System.out.println(text);
    }

    public void getHumansListInfo() {
        presenter.getHumansListInfo();
    }

    public void addHuman() {

        System.out.println("Введите имя человека:");
        String name = scanner.nextLine();

        System.out.println("Укажите пол (Male/Female):");
        String genderString = scanner.nextLine().trim();
        Gender gender;
        try {
            gender = Gender.valueOf(genderString);
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректный ввод пола. Попробуйте снова.");
            return;
        }

        System.out.println("Укажите дату рождения (yyyy-mm-dd):");
        String birthDateString = scanner.nextLine();
        LocalDate birthDate = LocalDate.parse(birthDateString);

        System.out.println("Укажите дату смерти (yyyy-mm-dd) (или оставьте пустым):");
        String deathDateString = scanner.nextLine();
        LocalDate deathDate = deathDateString.isEmpty() ? null : LocalDate.parse(deathDateString);

        System.out.println("Введите имя отца (или оставьте пустым):");
        String fatherName = scanner.nextLine();
        Human father = fatherName.isEmpty() ? null : new Human(fatherName, gender, birthDate);

        System.out.println("Введите имя матери (или оставьте пустым):");
        String motherName = scanner.nextLine();
        Human mother = motherName.isEmpty() ? null : new Human(motherName, gender, birthDate);

        presenter.addHuman(name, gender, birthDate, deathDate, father, mother);

    }

    public void setWedding() {
        System.out.println("Введите ID первого человека:");
        long id1 = Long.parseLong(scanner.nextLine());
        System.out.println("Введите ID второго человека:");
        long id2 = Long.parseLong(scanner.nextLine());
        presenter.setWedding(id1, id2);
    }
    public void setDivorce() {
        System.out.println("Введите ID первого человека:");
        long id1 = Long.parseLong(scanner.nextLine());
        System.out.println("Введите ID второго человека:");
        long id2 = Long.parseLong(scanner.nextLine());
        presenter.setDivorce(id1, id2);
    }

    public void addParent() {
        System.out.println("Введите ID ребенка:");
        long childId = Long.parseLong(scanner.nextLine());
        System.out.println("Введите ID родителя:");
        long parentId = Long.parseLong(scanner.nextLine());
        presenter.addParent(childId, parentId);
    }

    public void addChild() {
        System.out.println("Введите ID родителя:");
        long parentId = Long.parseLong(scanner.nextLine());
        System.out.println("Введите ID ребенка:");
        long childId = Long.parseLong(scanner.nextLine());
        presenter.addChild(parentId, childId);
    }



    public void sortByName() {
        presenter.sortByName();
    }

    public void sortByBirthDate() {
        presenter.sortByBirthDate();
    }

    // Сохранение семейного древа
    public void saveTree() {
        presenter.saveTree();
    }

    // Загрузка семейного древа
    public void loadTree() {
        presenter.loadTree();
    }

    public void finish() {
        System.out.println("Приятно было пообщаться");
        work = false;
    }



    private void printMenu() {
        System.out.println(menu.menu());
    }

    private void execute() {
        String line = scanner.nextLine();
        if (checkTextForInt(line)) {
            int numCommand = Integer.parseInt(line);
            if (checkCommand(numCommand)) {
                menu.execute(numCommand);
            }
        }
    }

    private boolean checkTextForInt(String text) {
        if (text.matches("\\d+")) {
            return true;
        } else {
            inputError();
            return false;
        }
    }

    private boolean checkCommand(int numCommand) {
        if (numCommand <= menu.getSize() && numCommand > 0) {
            return true;
        } else {
            inputError();
            return false;
        }
    }

    private void inputError() {
        System.out.println(INPUT_ERROR);
    }
}
