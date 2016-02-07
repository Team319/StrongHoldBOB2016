   // Strong Hold BOB 2016 Subsystems (Arm)

package org.usfirst.frc319.subsystems;

import org.usfirst.frc319.RobotMap;
import org.usfirst.frc319.commands.*;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class arm extends Subsystem {

    private final CANTalon armMotor = RobotMap.armarmMotor;

    public void initDefaultCommand() {

        setDefaultCommand(new ArmStop());

    }

}

