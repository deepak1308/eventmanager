This is simple event-manager program which uses multi-threading to perform following tasks:

  1. ReportStatus - which report the status of currently active events periodically.
  2. ReportTimeout - which periodically check if event-manager has been idle for the specified time 
                     without any active event and new event input.
  3. EventListener -  which periodically wait for user's input until timeout is reported by #2 thread.
  4. EventManager - The main class which start above child threads and join them till they execute.
  
  Testing:
  
  TestEventManager - It create event-manager object and start the event-manager which runs until idle timeout.
  
  input.txt - sample input for events in format <0/1 even-type start/end> <ipAddress> <machineName>
  
  output.txt - output of processing events and exit of event-manager with idle timeout
  
  Dependency:
  
  pom.xml has dependency on Junit tests which are needed to run the tests in file TestEventManager.
  
                   
