package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TeleopTesting extends LinearOpMode {

    //Wheels
    private DcMotor[] wheels = new DcMotor[4];

    //Joystick Values
    private double joyY;
    private double joyX;
    private double joyZ;

    private RobotDrive robot;

    @Override
    public void runOpMode() {
        //Connect the wheels int the configuration to the wheels in the code
        wheels[0] = hardwareMap.get(DcMotor.class,"FLeft");
        wheels[1] = hardwareMap.get(DcMotor.class,"FRight");
        wheels[2] = hardwareMap.get(DcMotor.class,"BLeft");
        wheels[3] = hardwareMap.get(DcMotor.class,"BRight");

        //Make an instance of the RobotDrive Class
        RobotDrive robot = new RobotDrive();

        //Wait for the human driver to press the play button
        waitForStart();

        while (opModeIsActive()) {

            //Define Variables for joystick values

            //A negative is placed before those two values because
            //pushing a stick_y upward yields the uncommon negative value
            joyY = -gamepad1.left_stick_y;
            joyX =  gamepad1.left_stick_x;
//            joyZ = -gamepad1.right_stick_y; //Maybe...
            joyZ = gamepad1.right_trigger + -gamepad1.left_trigger;

            //Drive Mecanum wheels with the wheels defined.
            robot.mecanumDrive(joyX, joyY, joyZ, wheels);
        }
    }
}