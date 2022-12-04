package com.bsuir.green.enums;

import java.util.Arrays;
import java.util.Optional;

public enum DetailType {
    BRAKE_SYSTEM("Тормозная система"),
    CLUTCH("Сцепление"),
    COOLING("Охлаждающая система"),
    SPEEDOMETER("Спидометр"),
    STEERING("Рулевая система"),
    SUSPENSION("Подвеска"),
    FUEL_SYSTEM("Система подачи топлива");
    public final String typeOfDetail;
    DetailType(String typeOfDetail){
        this.typeOfDetail = typeOfDetail;
    }

    public static Optional<DetailType> get(String label) {
        return Arrays.stream(DetailType.values())
                .filter(env -> env.typeOfDetail.equals(label))
                .findFirst();
    }
    public static String getLable(DetailType detailType) {
        return detailType.typeOfDetail;
    }
}
