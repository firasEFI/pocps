package long_flow_example;

import org.testng.annotations.Test;

public class MiniFlow1 {

    private Object miniFlowInput1;
    private Object miniFlowInput2;

    private Object miniFlowOutput;

    public Object getMiniFlowOutput() {
        return this.miniFlowOutput;
    }

    public MiniFlow1() {
        this.miniFlowInput1 = new Object();
        this.miniFlowInput2 = new Object();
    }

    public MiniFlow1(Object miniFlowInput1, Object miniFlowInput2) {
        this.miniFlowInput1 = miniFlowInput1;
        this.miniFlowInput2 = miniFlowInput2;
    }

    @Test
    public void executeTest() {
        this.step1();

        this.miniFlowOutput = this.step2();
    }

    private void step1() {
        System.out.println("Step1");
    }

    private Object step2() {
        System.out.println("Step2");

        return new Object();
    }
}
