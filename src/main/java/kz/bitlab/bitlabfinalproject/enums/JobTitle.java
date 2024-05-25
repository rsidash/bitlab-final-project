package kz.bitlab.bitlabfinalproject.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JobTitle {
    MANAGER("Manager"),
    COACH("Coach");

    private final String title;
}
