package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.command.button.Button;
import com.seattlesolvers.solverslib.command.button.Trigger;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import java.util.function.DoubleSupplier;

public class Controls {

    private GamepadEx controller;

    public Controls(Gamepad controller) {
        this.controller = new GamepadEx(controller);
    }

    public DoubleSupplier driveTranslationY() {
        return controller::getLeftY;
    }

    public DoubleSupplier driveTranslationX() {
        return controller::getLeftX;
    }

    public DoubleSupplier driveRotation() {
        return controller::getRightX;
    }

    public Trigger resetYaw() {
        return controller.getGamepadButton(GamepadKeys.Button.BACK);
    }

}
