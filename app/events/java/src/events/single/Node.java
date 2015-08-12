package events.single;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import events.single.EventHandler;

public class Node {
	private List<Timeout> timeoutHandler = new ArrayList<Timeout>();
	private List<EventHandler> temperatureHandler = new ArrayList<EventHandler>();
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
				timeout.handler.handleEvent(new TimeoutEvent(timeout.deltaTimeMs));
				ii.remove();
			}
		}
	}
	
	public void receiveNewTemperature(int temperature) {
		if (this.temperature != temperature) {
			System.out.println("Node: temperature value change from " + this.temperature + " to: " + temperature);
			this.temperature = temperature;
			for(EventHandler handler : this.temperatureHandler) {
				handler.handleEvent(new TemperatureEvent(temperature));
			}
		}
	}
	
	public void registerTimeout(EventHandler handler, int deltaTimeMs) {
		if(handler != null && deltaTimeMs > 0) {
			System.out.println("Node.registerTimeout called");
			this.timeoutHandler.add(new Timeout(handler, deltaTimeMs));
		}
	}

	public void registerTemperature(EventHandler handler) {
		if(handler != null) {
			System.out.println("Node.registerTemperature called");
			this.temperatureHandler.add(handler);
		}
	}
	
	private class Timeout {
		public EventHandler handler;
		public int deltaTimeMs;
		public int ticks;
		public Timeout(EventHandler handler, int deltaTimeMs) {
			this.handler = handler;
			this.deltaTimeMs = deltaTimeMs;
			this.ticks = deltaTimeMs;
		}
	}
}
