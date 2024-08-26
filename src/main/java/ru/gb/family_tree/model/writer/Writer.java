package ru.gb.family_tree.model.writer;

import java.io.Serializable;

public interface Writer {
    boolean save(Serializable serializable);
    Object read();
}
