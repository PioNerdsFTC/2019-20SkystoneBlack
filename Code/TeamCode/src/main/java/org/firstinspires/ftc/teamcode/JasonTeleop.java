package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import java.util.Locale;

@TeleOp
public class JasonTeleop extends LinearOpMode {
    ColorSensor sensorColor;
    DcMotor frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
    @Override
    public void runOpMode() {

        // get a reference to the color sensor.
        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");
        REVColorSensor colorController = new REVColorSensor(sensorColor);
        frontLeft.getCurrentPosition();
        // wait for the start button to be pressed.
        waitForStart();

        // loop and read the RGB and distance data.
        // Note we use opModeIsActive() as our loop condition because it is an interruptible method.
        while (opModeIsActive()) {
            telemetry.addData("Red", colorController.getRed());
            telemetry.addData("Green", colorController.getGreen());
            telemetry.addData("Blue", colorController.getBlue());
            telemetry.update();
        }
    }
}
