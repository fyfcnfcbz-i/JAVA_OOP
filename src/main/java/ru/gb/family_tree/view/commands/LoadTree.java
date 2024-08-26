package ru.gb.family_tree.view.commands;

import ru.gb.family_tree.view.ConsoleUI;

public class LoadTree extends Command{

    public LoadTree(ConsoleUI consoleUI) {
        super("Загрузить семейное древо", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().loadTree();

    }

}
