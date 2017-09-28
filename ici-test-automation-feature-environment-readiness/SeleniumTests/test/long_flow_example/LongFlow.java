package long_flow_example;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LongFlow {

    private Object startInput1;
    private Object startInput2;

    @BeforeClass
    private void ClassSetup() {
        this.startInput1 = new Object();
        this.startInput2 = new Object();
    }

    @Test
    public void executeTest() {
        MiniFlow1 flow1 = new MiniFlow1(this.startInput1, this.startInput2);

        flow1.executeTest();

        MiniFlow2 flow2 = new MiniFlow2(flow1.getMiniFlowOutput());

        flow2.executeTest();
    }
}
