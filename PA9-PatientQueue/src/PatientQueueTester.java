import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PatientQueueTester {

    @Test
    public void testSize1() {
        PatientQueue Q = new PatientQueue();
        Q.enqueue("henny", 1);
        assertEquals(Q.size(), 1);
    }

    @Test
    public void testString() {
        PatientQueue Q = new PatientQueue();
        Q.enqueue("henny", 1);
        Q.enqueue("robert", 4);
        Q.toString();
        assertEquals(Q.toString(), "");
    }

    @Test
    public void testlotsofEnqueues() {
        PatientQueue Q = new PatientQueue();
        Q.enqueue("henny", 4);
        Q.enqueue("robert", 10);
        Q.enqueue("jim", 2);
        Q.enqueue("dollas", 15);
        Q.enqueue("cash", 5);
        Q.enqueue("iooo", 6);
        assertEquals(Q.toString(), "");
    }

    @Test
    public void testDequeueBubbleDown() {
        PatientQueue Q = new PatientQueue();
        Q.enqueue("henny", 1);
        Q.enqueue("robert", 10);
        Q.enqueue("jim", 2);
        Q.enqueue("dollas", 15);
        Q.enqueue("cash", 5);
        Q.enqueue("iooo", 4);
        Q.dequeue();
        assertEquals(Q.toString(), "");
    }

    @Test
    public void testDequeueOnEmpty() {
        PatientQueue Q = new PatientQueue();
        Q.dequeue();
        assertEquals(Q.toString(), "");
    }

    @Test
    public void testChangePriority() {
        PatientQueue Q = new PatientQueue();
        Q.enqueue("henny", 2);
        Q.enqueue("robert", 10);
        Q.enqueue("jim", 28);
        Q.enqueue("dollas", 45);
        Q.enqueue("cash", 59);
        Q.enqueue("iooo", 47);
        Q.changePriority("dollas", 7);
        assertEquals(Q.toString(), "");
    }
}
