package ru.gb.family_tree.view.commands;

import ru.gb.family_tree.view.ConsoleUI;

public class SetDivorce extends Command{
    public SetDivorce(ConsoleUI consoleUI) {
        super("Прекратить брак", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().setDivorce();

    }
}