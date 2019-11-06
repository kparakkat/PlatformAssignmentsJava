package com.kk.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CalculatorTest {
    @Test
    public void testAdd() {
        assertEquals(11, Calculator.add(5, 6));
    }
    
    @Test
    public void testSub() {
        assertEquals(5, Calculator.sub(11, 6));
    }
}
