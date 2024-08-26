package ru.gb.family_tree.view.commands;

import ru.gb.family_tree.view.ConsoleUI;

public class AddHuman extends Command{

    public AddHuman(ConsoleUI consoleUI) {
        super("Добавить человека в семейное древо", consoleUI);
}

    @Override
    public void execute() {
        getConsoleUI().addHuman();

    }
}
