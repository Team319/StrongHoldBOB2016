// Strong Hold BOB 2016 Subsystems (Tower Camera)

package org.usfirst.frc319.subsystems;

import org.usfirst.frc319.RobotMap;
import org.usfirst.frc319.commands.*;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TowerCamera extends Subsystem {

    private final Relay towerRelay = RobotMap.towerCameratowerRelay;

    public void initDefaultCommand() {

        setDefaultCommand(new CameraSleep());

    }
    
}

