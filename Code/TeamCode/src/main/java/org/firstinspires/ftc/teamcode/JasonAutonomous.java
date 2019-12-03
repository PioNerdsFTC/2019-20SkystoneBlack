package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="JasonAuto", group="Pushbot")
public class JasonAutonomous extends LinearOpMode{
    Autobot robot = new Autobot();
    private ElapsedTime runtime = new ElapsedTime();
    ModernRoboticsI2cGyro gyro = null;
    static final double FORWARD_SPEED = 0.6;
    static final double TURN_SPEED    = 0.5;

    public void runOpMode() {
        gyro = (ModernRoboticsI2cGyro)hardwareMap.gyroSensor.get("gyro");
        double initialDriveForwardTime = 1.5;
        double secondDriveForwardTime = 3.0;
        double initialGyroValue = gyro.getHeading();

        // 1. Drive straight for X seconds
        robot.FLeft.setPower(FORWARD_SPEED);
        robot.FRight.setPower(FORWARD_SPEED);
        robot.BLeft.setPower(FORWARD_SPEED);
        robot.BRight.setPower(FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < initialDriveForwardTime)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // 2. Pick up block

        // 3. Turn Left 90 degrees
        robot.FLeft.setPower(-TURN_SPEED);
        robot.FRight.setPower(+TURN_SPEED);
        robot.BLeft.setPower(-TURN_SPEED);
        robot.BRight.setPower(+TURN_SPEED);
        runtime.reset();
        while (opModeIsActive() && (gyro.getHeading() < initialGyroValue + 90)) {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        // 4. Move forward X seconds
        robot.FLeft.setPower(FORWARD_SPEED);
        robot.FRight.setPower(FORWARD_SPEED);
        robot.BLeft.setPower(FORWARD_SPEED);
        robot.BRight.setPower(FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < secondDriveForwardTime)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        // 5. Put down block
    }
}
