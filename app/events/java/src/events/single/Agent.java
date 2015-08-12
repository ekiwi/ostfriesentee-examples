package events.single;

import events.single.Node;

public class Agent implements EventHandler{
	private String name;
	private Node node;

	public Agent(String name, Node node) {
		this.name = name;
		this.node = node;
		this.node.registerTemperature(this);
		this.node.registerTimeout(this, 1000);
		this.node.registerTimeout(this, 2000);
	}

	public void handleEvent(Event event) {
		if(event instanceof TemperatureEvent) {
			int temperature = ((TemperatureEvent)event).temperture;
			System.out.println(Agent.this.name + ": Temperature is now " + temperature + " degrees.");
		} else if(event instanceof TimeoutEvent) {
			int deltaTimeMs = ((TimeoutEvent)event).deltaTimeMs;
			System.out.println(Agent.this.name + ": Timeout called after " + deltaTimeMs + " ms.");
		}
	}

}
