package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Basic TeleOp")
public class TeleOp2026 extends OpMode {
    private Follower follower;

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose());
    }

    @Override
    public void start() {
        follower.startTeleOpDrive(true); // brake mode
        follower.update();
    }

    @Override
    public void loop() {
        follower.setTeleOpDrive(
                gamepad1.left_stick_y,          // forward
                gamepad1.left_stick_x,          // strafe
                gamepad1.right_stick_x,        // turn
                true);                          // robot centric
        follower.update();

        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.update();
    }
}