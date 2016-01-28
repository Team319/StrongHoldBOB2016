// Strong Hold BOB 2016 Subsystems (Climber)

package org.usfirst.frc319.subsystems;

import org.usfirst.frc319.RobotMap;
import org.usfirst.frc319.commands.*;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class climber extends Subsystem {

    private final CANTalon climberMotor = RobotMap.climberclimberMotor;

    public void initDefaultCommand() {

        setDefaultCommand(new ClimberStop());

    }

}

