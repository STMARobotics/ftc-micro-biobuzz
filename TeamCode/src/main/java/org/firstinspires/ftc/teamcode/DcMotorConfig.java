package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class DcMotorConfig {

    private DcMotor.Direction direction;
    private DcMotor.ZeroPowerBehavior zeroPowerBehavior;
    private DcMotor.RunMode mode;

    public DcMotorConfig withDirection(DcMotor.Direction direction) {
        this.direction = direction;
        return this;
    }

    public DcMotorConfig withZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior) {
        this.zeroPowerBehavior = zeroPowerBehavior;
        return this;
    }

    public DcMotorConfig withMode(DcMotor.RunMode mode) {
        this.mode = mode;
        return this;
    }

    public void applyTo(DcMotor... motors) {
        for (DcMotor motor : motors) {
            if (direction != null) {
                motor.setDirection(direction);
            }
            if (zeroPowerBehavior != null) {
                motor.setZeroPowerBehavior(zeroPowerBehavior);
            }
            if (mode != null) {
                motor.setMode(mode);
            }
        }
    }

}
