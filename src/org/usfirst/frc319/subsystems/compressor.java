// Strong Hold BOB 2016 Subsystems (Compressor)

package org.usfirst.frc319.subsystems;

import org.usfirst.frc319.RobotMap;
import org.usfirst.frc319.commands.*;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class compressor extends Subsystem {

    private final Compressor compressor = RobotMap.compressorcompressor;
    private final AnalogInput pressureSensor = RobotMap.compressorpressureSensor;

    public void initDefaultCommand() {

        setDefaultCommand(new CompressorRun());

    }
    
    public void CompressorRun(){
    	compressor.setClosedLoopControl(true);
    
    }

}

