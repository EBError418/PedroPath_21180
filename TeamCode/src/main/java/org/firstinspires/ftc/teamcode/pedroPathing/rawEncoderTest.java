package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareDevice;

import java.util.List;

@TeleOp(name = "EncDeepDebug")
public class rawEncoderTest extends OpMode {
    private final String[] names = {"rf", "rr", "lr", "lf"};
    private final DcMotorEx[] motors = new DcMotorEx[4];
    private final int[] last = new int[4];
    private List<LynxModule> hubs;

    @Override
    public void init() {
        hubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule h : hubs) h.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);

        StringBuilder sb = new StringBuilder("DEVICES:\n");
        for (HardwareDevice d : hardwareMap.getAll(HardwareDevice.class))
            sb.append(hardwareMap.getNamesOf(d)).append(" = ")
                    .append(d.getClass().getSimpleName()).append('\n');
        telemetry.addData("dump", sb.toString());
        telemetry.update();

        for (int i = 0; i < 4; i++) {
            motors[i] = hardwareMap.get(DcMotorEx.class, names[i]);
            motors[i].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    @Override
    public void loop() {
        for (LynxModule h : hubs) h.clearBulkCache();
        if (gamepad1.a) setAll(DcMotor.RunMode.RUN_USING_ENCODER);
        if (gamepad1.b) setAll(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("hubs", hubs.size());
        for (int i = 0; i < 4; i++) {
            int p = motors[i].getCurrentPosition();
            int d = p - last[i];
            last[i] = p;
            telemetry.addData(names[i], "pos=" + p + " d=" + d
                    + " vel=" + (int) motors[i].getVelocity()
                    + " on=" + motors[i].getController().getDeviceName());
        }
        telemetry.addLine("A=RUN_USING B=RUN_WITHOUT; spin each pod slowly");
        telemetry.update();
    }

    private void setAll(DcMotor.RunMode m) { for (DcMotorEx x : motors) x.setMode(m); }
}