This is simple event-manager program which uses multi-threading to perform following tasks:

  1. ReportStatus - which report the status of currently active events periodically.
  2. ReportTimeout - which periodically check if event-manager has been idle for the specified time 
                     without any active event and new event input.
  3. EventListener -  which periodically wait for user's input until timeout is reported by #2 thread.
  4. EventManager - The main class which start above child threads and join them till they execute.
  
  Testing:
  
  TestEventManager - It create event-manager object and start the event-manager which runs until idle timeout.
  
                   
