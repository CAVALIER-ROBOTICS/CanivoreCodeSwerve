// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ShootCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Limelight;
import frc.robot.subsystems.HoodSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class HoodCommand extends PIDCommand {
  /** Creates a new HoodCommand. */
  HoodSubsystem hood;
  public HoodCommand(HoodSubsystem hoodSub) {
    super(
        // The controller that the command will use
        new PIDController(0.04, 0.007, 0),//.052
        // This should return the measurement
        hoodSub::getAngle,
        // This should return the setpoint (can also be a constant)
        ()-> Limelight.getAngle(),
        // () -> SmartDashboard.getNumber("Hood Angle input", 14),
        // This uses the output
        (output) -> {
          // Use the output here
          hoodSub.setHood(output);
         
          SmartDashboard.putNumber("table Angle", Limelight.getAngle());
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    hood = hoodSub;
    addRequirements(hoodSub);
 }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return hood.getVoltage()>23;
  }
}
