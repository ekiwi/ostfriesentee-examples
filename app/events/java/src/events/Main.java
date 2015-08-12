package events;

public class Main {
	public static void main(String[] args)
	{
		System.out.println("Events");
		System.out.println("\n\n---------------------------------------\n");
		testMulti();
		System.out.println("\n\n---------------------------------------\n");
		testSingle();
	}
	
	
	private static void testMulti() {
		System.out.println("Handle events using multiple handlers an inner classes....");
		events.multi.Node node = new events.multi.Node(10);
		events.multi.Agent agent1 = new events.multi.Agent("agent1", node);
		for(long ii = 0; ii < 10000; ++ii) {
			node.run(ii);
			if(ii == 1000) {
				node.receiveNewTemperature(15);
			}
		}
	}
	
	private static void testSingle() {
		System.out.println("Handle events using a single event handle....");
		events.single.Node node = new events.single.Node(10);
		events.single.Agent agent1 = new events.single.Agent("agent1", node);
		for(long ii = 0; ii < 10000; ++ii) {
			node.run(ii);
			if(ii == 1000) {
				node.receiveNewTemperature(15);
			}
		}
	}
}
