package deepak1308.eventmanager;

import org.junit.Test;

public class TestEventManager {

	@Test
    public void testEventManager()
    {
        System.out.println("Starting event-manager!!");
        
        EventManager eventManager = new EventManager();
        eventManager.startEventManagaer();
        
        System.out.println("Exiting event-manager!!");
    }
}
