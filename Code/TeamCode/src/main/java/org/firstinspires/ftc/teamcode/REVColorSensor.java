package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import java.util.Locale;

/*
 * This is an example LinearOpMode that shows how to use
 * the REV Robotics Color-Distance Sensor.
 *
 * It assumes the sensor is configured with the name "sensor_color_distance".
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 */

//@TeleOp
// @Disabled                            // Comment this out to add to the opmode list
public class REVColorSensor {
    ColorSensor sensorColor;
    // read RGB values from sensorColorIn
    public REVColorSensor(ColorSensor sensorColorIn) {
        sensorColor = sensorColorIn;
    }
    public double getRed() {
        return sensorColor.red();
    }
    public double getGreen() {
        return sensorColor.green();
    }
    public double getBlue() {
        return sensorColor.blue();
    }
}
