package com.example.timemaster.model;

public class FoldersData {

    public static Folder[] populateFolderData() {
        return new Folder[] {
           new Folder(1, 1, "Дом", "Желтый"),
           new Folder(2, 1, "Работа", "Розовый")
        };
    }
}
