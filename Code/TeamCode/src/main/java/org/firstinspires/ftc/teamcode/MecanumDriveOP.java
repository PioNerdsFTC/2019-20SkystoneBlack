package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class MecanumDriveOP extends LinearOpMode {

    //Wheels
    private DcMotor[] wheels = new DcMotor[4];

    //Joystick Values
    private double joyY;
    private double joyX;
    private double joyZ;

    RobotDrive robot = new RobotDrive();

    @Override
    public void runOpMode(){
        wheels[0] = hardwareMap.get(DcMotor.class,"FLeft");
        wheels[1] = hardwareMap.get(DcMotor.class,"FRight");
        wheels[2] = hardwareMap.get(DcMotor.class,"BLeft");
        wheels[3] = hardwareMap.get(DcMotor.class,"BRight");

        waitForStart();

        telemetry.addData("MyStatus", "Started");
        telemetry.update();

        while (opModeIsActive()) {

            joyY = -gamepad1.left_stick_y;
            joyX =  gamepad1.left_stick_x;
            joyZ = gamepad1.right_trigger + -gamepad1.left_trigger;

            robot.mecanumDrive(joyX, joyY, joyZ, true, 1.0, wheels);

            showWheelSpeeds(robot);

            telemetry.addData("MyStatus", "Running");
            telemetry.update();
        }
    }

    public void showWheelSpeeds(RobotDrive robot){
        for(int i = 0; i < robot.publicWheelSpeeds.length; i++){
            telemetry.addData("Motor Number: " + i, robot.publicWheelSpeeds[i]);
        }
    }
}
