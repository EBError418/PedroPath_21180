package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.control.PredictiveBrakingCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.Encoder;
import com.pedropathing.ftc.localization.constants.ThreeWheelConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(8)
            .centripetalScaling(0)
            .headingPIDFCoefficients(new PIDFCoefficients(1.0, 0, 0, 0))
            .translationalPIDFCoefficients(new PIDFCoefficients(0.1, 0, 0.01, 0))
            .predictiveBrakingCoefficients(new PredictiveBrakingCoefficients(0.1, 0.0441, 0.00124));

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .rightFrontMotorName("lr")   // was "rf"
            .rightRearMotorName("lf")    // was "rr"
            .leftRearMotorName("rf")     // was "lr"
            .leftFrontMotorName("rr")    // was "lf"
            .leftFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .leftRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .xVelocity(63.143)
            .yVelocity(52.412);

    public static ThreeWheelConstants localizerConstants = new ThreeWheelConstants()
            .leftPodY(3.5)
            .rightPodY(-3.5)
            .strafePodX(-8)
            .leftEncoder_HardwareMapName("rf")
            .rightEncoder_HardwareMapName("lr")
            .strafeEncoder_HardwareMapName("rr")
            .leftEncoderDirection(Encoder.FORWARD)
            .rightEncoderDirection(Encoder.FORWARD)
            .strafeEncoderDirection(Encoder.FORWARD)
            .forwardTicksToInches(0.00201)
            .strafeTicksToInches(0.00201)
            .turnTicksToInches(0.00192);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .threeWheelLocalizer(localizerConstants)
                .build();
    }
}
