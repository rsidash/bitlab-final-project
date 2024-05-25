package kz.bitlab.bitlabfinalproject.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlayingPosition {
    GOALKEEPER("Goalkeeper"),
    DEFENCEMAN("Defenceman"),
    LEFT_FORWARD("Left forward"),
    RIGHT_FORWARD("Right forward"),
    CENTER_FORWARD("Center forward");

    private final String title;
}
