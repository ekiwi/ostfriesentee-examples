package events.single;

public class TimeoutEvent extends Event {
	public int deltaTimeMs;
	public TimeoutEvent(int deltaTimeMs) {
		this.deltaTimeMs = deltaTimeMs;
	}
}
