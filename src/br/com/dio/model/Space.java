package br.com.dio.model;

public class Space {

    private Integer actualValue;

    private final int expectedValue;

    private final boolean fixed;

    public Space(int expectedValue, boolean fixed) {
        this.expectedValue = expectedValue;
        this.fixed = fixed;
        if (fixed){
            actualValue = expectedValue;
        }
    }

    public Integer getActualValue() {
        return actualValue;
    }

    public int getExpectedValue() {
        return expectedValue;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void clearSpace(){
        this.setActualValue(null);
    }

    public void setActualValue(Integer actualValue) {
        //não pode mudar uma valor fixo
        if (fixed) return;
        this.actualValue = actualValue;
    }
}
