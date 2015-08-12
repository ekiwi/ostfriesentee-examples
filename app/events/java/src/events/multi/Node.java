package events.multi;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Node {
	private List<Timeout> timeoutHandler = new ArrayList<Timeout>();
	private List<TemperatureEventHandler> temperatureHandler = new ArrayList<TemperatureEventHandler>();
	private int temperature;
	
	public Node(int temperature) {
		this.temperature = temperature;
	}
	
	public void run(long tick) {
		Iterator<Timeout> ii = this.timeoutHandler.iterator();
		while(ii.hasNext()) {
			Timeout timeout = ii.next();
			timeout.ticks--;
			if (timeout.ticks <= 0) {
				timeout.handler.onTimeout(timeout.deltaTimeMs);
				ii.remove();
			}
		}
	}
	
	public void receiveNewTemperature(int temperature) {
		if (this.temperature != temperature) {
			System.out.println("Node: temperature value change from " + this.temperature + " to: " + temperature);
			this.temperature = temperature;
			for(TemperatureEventHandler handler : this.temperatureHandler) {
				handler.onNewTemperaturValue(temperature);
			}
		}
	}
	
	public void registerTimeout(TimeoutEventHandler handler, int deltaTimeMs) {
		if(handler != null && deltaTimeMs > 0) {
			System.out.println("Node.registerTimeout called");
			this.timeoutHandler.add(new Timeout(handler, deltaTimeMs));
		}
	}

	public void registerTemperature(TemperatureEventHandler handler) {
		if(handler != null) {
			System.out.println("Node.registerTemperature called");
			this.temperatureHandler.add(handler);
		}
	}
	
	private class Timeout {
		public TimeoutEventHandler handler;
		public int deltaTimeMs;
		public int ticks;
		public Timeout(TimeoutEventHandler handler, int deltaTimeMs) {
			this.handler = handler;
			this.deltaTimeMs = deltaTimeMs;
			this.ticks = deltaTimeMs;
		}
	}
}
