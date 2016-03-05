package org.usfirst.frc319.commands.camera;

public class Target {
	private double offsetPixels = 0;
	private double offsetDegrees = 0;
	
	
	public Target(double xOffsetPixels, double xOffsetDegrees){
		this.offsetDegrees = xOffsetDegrees;
		this.offsetPixels = xOffsetPixels;
	}
	
	public double getOffsetDegrees() {
		return offsetDegrees;
	}
	
	public double getOffsetPixels() {
		return offsetPixels;
	}
}
