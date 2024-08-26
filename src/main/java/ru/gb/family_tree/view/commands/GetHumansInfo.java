package ru.gb.family_tree.view.commands;

import ru.gb.family_tree.view.ConsoleUI;

public class GetHumansInfo extends Command {

    public GetHumansInfo(ConsoleUI consoleUI) {
        super("Получить весь список", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().getHumansListInfo();

    }
}
