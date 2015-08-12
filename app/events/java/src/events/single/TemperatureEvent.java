package events.single;

public class TemperatureEvent extends Event {
	public int temperture;
	public TemperatureEvent(int temperature) {
		this.temperture = temperature;
	}
}
