package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public class JasonAutonomous {
    Autobot robot = new Autobot();
    private ElapsedTime runtime = new ElapsedTime();

    static final double     FORWARD_SPEED = 0.6;
    static final double     TURN_SPEED    = 0.5;



    // 1. Drive straight for X seconds
    robot.leftDrive.setPower(FORWARD_SPEED);
    robot.rightDrive.setPower(FORWARD_SPEED);
    runtime.reset();
    while (opModeIsActive() && (runtime.seconds() < 3.0)) {
        telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
        telemetry.update();
    }

    // 2. Pick up block

    // 3. Turn Left 90 degrees
    robot.leftDrive.setPower(-TURN_SPEED);
    robot.rightDrive.setPower(+TURN_SPEED);
    runtime.reset();
    while (opModeIsActive() && (runtime.seconds() < 1.3)) {
        telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
        telemetry.update();
    }
    // 4. Move forward X seconds
    robot.leftDrive.setPower(FORWARD_SPEED);
    robot.rightDrive.setPower(FORWARD_SPEED);
    runtime.reset();
    while (opModeIsActive() && (runtime.seconds() < 3.0)) {
        telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
        telemetry.update();
    }
    // 5. Put down block

}
