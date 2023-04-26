package dev.khacdatdo.android_practice_2.model;

public enum TinhTrang {
    PENDING("Chưa hoàn thành"), PROGRESSING("Đang hoàn thành"), DONE("Đã hoàn thành");

    private String name;

    TinhTrang(String s) {
        this.name = s;
    }

    public String getName() {
        return name;
    }
}
