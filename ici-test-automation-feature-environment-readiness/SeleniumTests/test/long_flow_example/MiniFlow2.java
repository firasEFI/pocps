package long_flow_example;

import org.testng.annotations.Test;

public class MiniFlow2 {

    private Object miniFlowInput;

    public MiniFlow2() {
        this.miniFlowInput = new Object();
    }

    public MiniFlow2(Object miniFlowInput) {
        this.miniFlowInput = miniFlowInput;
    }

    @Test
    public void executeTest() {
        this.step1();
        this.step2();
    }

    private void step1() {
        System.out.println("Step1");
    }

    private void step2() {
        System.out.println("Step2");
    }
}
