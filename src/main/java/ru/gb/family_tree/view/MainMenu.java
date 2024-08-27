package ru.gb.family_tree.view;

import ru.gb.family_tree.view.commands.*;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {

    private List<Command> commandList;

    public MainMenu(ConsoleUI consoleUI) {
        commandList = new ArrayList<>();
        commandList.add(new AddHuman(consoleUI));
        commandList.add(new GetHumansInfo(consoleUI));

        commandList.add(new SetWedding(consoleUI));
        commandList.add(new SetDivorce(consoleUI));

        commandList.add(new AddParent(consoleUI));
        commandList.add(new AddChild(consoleUI));

        commandList.add(new SortByName(consoleUI));
        commandList.add(new SortByBirthDate(consoleUI));
        commandList.add(new SaveTree(consoleUI));
        commandList.add(new LoadTree(consoleUI));
        commandList.add(new Finish(consoleUI));
    }

    public String menu() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Список команд:\n");
        for (int i = 0; i < commandList.size(); i++) {
            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            stringBuilder.append(commandList.get(i).getDescription());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public void execute(int choice) {
        if (choice > 0 && choice <= commandList.size()) {
            Command command = commandList.get(choice - 1);
            command.execute();
        } else {
            System.out.println("Неверный выбор. Пожалуйста, выберите правильный номер.");
        }
    }

    public int getSize() {
        return commandList.size();
    }
}


