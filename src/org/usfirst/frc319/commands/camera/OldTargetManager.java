package org.usfirst.frc319.commands.camera;

import java.util.ArrayList;
import java.util.List;

public class OldTargetManager {
	
	private OldTarget target;
	
	private static OldTargetManager instance = null;
	
	private List<IOldTargetListener> listeners;
	
	private OldTargetManager(){
		listeners = new ArrayList<IOldTargetListener>();
	}
	
	public static OldTargetManager getInstance(){
		if(instance == null){
			instance = new OldTargetManager();
			
		}
		
		return instance;
	}
	
	public void registerListener(IOldTargetListener listener){
		this.listeners.add(listener);
	}
	
	public void unregisterListener(IOldTargetListener listener){
		this.listeners.remove(listener);
	}
	
	public void setTarget(OldTarget target) {
		this.target = target;
		for(IOldTargetListener listener: listeners){
			listener.onTargetChange(target);
		}
	}
	
	public OldTarget getTarget() {
		return target;
	}
	
	

}
