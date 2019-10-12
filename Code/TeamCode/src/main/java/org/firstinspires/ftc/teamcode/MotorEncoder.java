package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor;

public class MotorEncoder {
    private DcMotor targetMotor;
    private int currentPosition;
    public MotorEncoder(DcMotor input) {
        targetMotor = input;
        currentPosition = targetMotor.getCurrentPosition();
    }
    public int getCurrentPosition() {
        return targetMotor.getCurrentPosition();
    }
}
