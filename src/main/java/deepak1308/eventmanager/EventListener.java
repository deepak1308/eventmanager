package deepak1308.eventmanager;

import java.io.*;
import java.util.StringTokenizer;

public class EventListener implements Runnable {
	
	private EventManager eventManager;
	
	public EventListener(EventManager eventManager) {
		this.eventManager = eventManager;
	}
	
	@Override
    public void run() {
		PrintStream logger = eventManager.getOutputStream();
		InputStream inputStream = eventManager.getInputStream();	
		BufferedReader in = 
				new BufferedReader(new InputStreamReader(inputStream));
		StringTokenizer tokenizer;
		String input;
		int type; // 0/1 - start/end of event
		long ipAddress;
		String machineName;
		
		while(!eventManager.isIdle()) {
    	    try 
    	    {
    	      logger.println("Enter event in format <0/1 even-type start/end> <ipAddress> <machineName>");
    	      long startTime = System.currentTimeMillis();
    	      while ((System.currentTimeMillis() - startTime) < 2000
    	              && !in.ready()) {
    	    	  // wait for 2 seconds for user to provide next event input
    	      }
    		  if (in.ready()) {
    		    try {
    		      input = in.readLine();
    		      tokenizer = new StringTokenizer(input);
                  type = Integer.parseInt(tokenizer.nextToken());
                  ipAddress = Long.parseLong(tokenizer.nextToken());
                  machineName = tokenizer.nextToken();
              
                  if(type == 1) {
                	if(eventManager.getEventsMap().containsKey(ipAddress))  {
                		eventManager.getEventsMap().remove(ipAddress);
                	}
                	else {
                		logger.println("Event does not exist");
                	}            	    
                  }
                  else if(type == 0) {
                	if(eventManager.getEventsMap().containsKey(ipAddress)) {
                		logger.println("Event already started");
                	}
                	else {
                		eventManager.getEventsMap().put(ipAddress, machineName);
                	}            	    
                  }
                  else {
                	  logger.println("Invalid event-type");
                  }
    		    }
    		    catch(Exception e) {
    		    	logger.println("Invalid input!!");
    		    }
    		    
    		    Thread.sleep(1000);
    		  }              
            } catch (Exception e) {
            	logger.println("EventListener thread exit with exception: " + e);
            }
		  }
    }
}