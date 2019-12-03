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
