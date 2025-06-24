public class Multithreading {
    public static void main(String[] args) {
        System.out.println("Main thread starting...");
        
        // Demonstrating thread creation using Runnable
        Thread thread1 = new Thread(new MyRunnable(), "Thread-1");
        thread1.setPriority(Thread.MIN_PRIORITY); // Setting priority
        
        // Demonstrating thread creation by extending Thread
        MyThread thread2 = new MyThread("Thread-2");
        thread2.setPriority(Thread.MAX_PRIORITY);
        
        thread1.start();
        thread2.start();
        
        // Using isAlive() and join()
        try {
            System.out.println("Thread-1 is alive: " + thread1.isAlive());
            thread1.join(2000); // Wait for thread1 to finish or timeout after 2 seconds
            System.out.println("Thread-1 is alive after join: " + thread1.isAlive());
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
        
        // Thread states
        System.out.println("Thread-2 state: " + thread2.getState());
        
        // Create and start producer-consumer threads
        MessageQueue queue = new MessageQueue();
        new Producer(queue);
        new Consumer(queue);
        
        // Demonstrating synchronized block with multiple threads accessing shared resource
        Counter counter = new Counter();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            }).start();
        }
        
        // Give time for threads to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Final count: " + counter.getCount());
        System.out.println("Main thread ending.");
    }
    
    // Thread creation method 1: Implementing Runnable
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " starting.");
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted.");
            }
            System.out.println(Thread.currentThread().getName() + " terminating.");
        }
    }
    
    // Thread creation method 2: Extending Thread class
    static class MyThread extends Thread {
        public MyThread(String name) {
            super(name);
        }
        
        @Override
        public void run() {
            System.out.println(getName() + " starting.");
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(800);
                    System.out.println(getName() + ": " + i);
                }
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted.");
            }
            System.out.println(getName() + " terminating.");
        }
    }
    
    // Thread synchronization example
    static class Counter {
        private int count = 0;
        
        // Using synchronized method
        public synchronized void increment() {
            count++;
        }
        
        public int getCount() {
            return count;
        }
    }
    
    // Producer-Consumer pattern with wait() and notify()
    static class MessageQueue {
        private String message;
        private boolean empty = true;
        
        public synchronized String receive() {
            // Wait until message is available
            while (empty) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException caught");
                }
            }
            
            // Toggle status
            empty = true;
            
            // Notify producer that space is available
            notifyAll();
            
            return message;
        }
        
        public synchronized void send(String message) {
            // Wait until message has been received
            while (!empty) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException caught");
                }
            }
            
            // Store message
            this.message = message;
            
            // Toggle status
            empty = false;
            
            // Notify consumer that message is available
            notifyAll();
        }
    }
    
    static class Producer implements Runnable {
        private MessageQueue messageQueue;
        
        public Producer(MessageQueue messageQueue) {
            this.messageQueue = messageQueue;
            new Thread(this, "Producer").start();
        }
        
        @Override
        public void run() {
            String[] messages = {"Message 1", "Message 2", "Message 3", "Done"};
            
            for (String message : messages) {
                messageQueue.send(message);
                System.out.println("Sent: " + message);
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Producer interrupted");
                }
            }
        }
    }
    
    static class Consumer implements Runnable {
        private MessageQueue messageQueue;
        
        public Consumer(MessageQueue messageQueue) {
            this.messageQueue = messageQueue;
            new Thread(this, "Consumer").start();
        }
        
        @Override
        public void run() {
            String message;
            
            do {
                message = messageQueue.receive();
                System.out.println("Received: " + message);
                
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    System.out.println("Consumer interrupted");
                }
            } while (!message.equals("Done"));
        }
    }
}