package org.usfirst.frc319;

import edu.wpi.first.wpilibj.AnalogInput;

public class SharpIRSensor extends AnalogInput {
	
	public SharpIRSensor( int channel)
	{
		super(channel);
	}
	
	public double getRange()
	{
		double voltage = this.getVoltage();
		double range = 0.0;
		
		//do some math to turn voltage into range
		
		return range;
		
	}

}