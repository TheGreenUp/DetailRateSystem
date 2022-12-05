package com.bsuir.green.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public enum StuffRoles {
    ADMIN("Администратор"),
    SPECIALIST("Специалист");
    public final String stuffLabel;

    StuffRoles(String stuffLabel){
        this.stuffLabel = stuffLabel;
    }
    public static Optional<StuffRoles> get(String label) {
        return Arrays.stream(com.bsuir.green.enums.StuffRoles.values())
                .filter(env -> env.stuffLabel.equals(label))
                .findFirst();
    }
    public static String getLable(StuffRoles stuffRole) {
        return stuffRole.stuffLabel;
    }
    public static ArrayList<String> getLables() {//получаем массив всех лейблов
        ArrayList<String> stuffRolesLabels = new ArrayList<String>();
        for(int i = 0; i < StuffRoles.values().length; i ++) {
            stuffRolesLabels.add(getLable(StuffRoles.values()[i]));
        }
        return  stuffRolesLabels;
    }
}
