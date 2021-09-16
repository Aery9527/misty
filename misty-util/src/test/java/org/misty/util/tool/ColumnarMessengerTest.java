package org.misty.util.tool;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ColumnarMessengerTest {

    private final List<String> messages = Collections.unmodifiableList(Arrays.asList(
            "Aery"
            , "Alicia"
            , "Leona"
            , "Rion"
            , "Brian"
            , "Cynthia"
            , "Tina"
            , "Alan"
            , "Jack"
            , "Mary"
    ));

    @Test
    public void all_default() {
        printWithTest(new ColumnarMessenger().mix(this.messages));
    }

    @Test
    public void all_with() {
        printWithTest(new ColumnarMessenger()
                .withNewlineFirst()
                .withBeginning("kerker")
                .withEnding("GGG")
                .withColumnarPrefix(": ")
                .withBoundary()
                .withBoundaryLength(3)
                .withColumnarItem("*")
                .mix(this.messages)
        );
    }

    @Test
    public void newlineFirst() {
        ColumnarMessenger cm = new ColumnarMessenger();
        printWithTest(cm.withNewlineFirst().mix(this.messages));
        printWithTest(cm.withoutNewlineFirst().mix(this.messages));
    }

    @Test
    public void beginning() {
        ColumnarMessenger cm = new ColumnarMessenger();
        printWithTest(cm.withBeginning("This is beginning message").mix(this.messages));
        printWithTest(cm.withoutBeginning().mix(this.messages));
    }

    @Test
    public void ending() {
        ColumnarMessenger cm = new ColumnarMessenger();
        printWithTest(cm.withEnding("This is ending message").mix(this.messages));
        printWithTest(cm.withoutEnding().mix(this.messages));
    }

    @Test
    public void columnarPrefix() {
        ColumnarMessenger cm = new ColumnarMessenger();
        printWithTest(cm.withColumnarPrefix(">>").mix(this.messages));
        printWithTest(cm.withoutColumnarPrefix().mix(this.messages));
    }

    @Test
    public void boundary() {
        ColumnarMessenger cm = new ColumnarMessenger();
        printWithTest(cm.withBoundary().mix(this.messages));
        printWithTest(cm.withBoundary('*').mix(this.messages));
        printWithTest(cm.withoutBoundary().mix(this.messages));
    }

    @Test
    public void boundaryLength() {
        ColumnarMessenger cm = new ColumnarMessenger();
        printWithTest(cm.withBoundary().withBoundaryLength(1).mix(this.messages));
        printWithTest(cm.withBoundary('*').withBoundaryLength(5).mix(this.messages));
        printWithTest(cm.withColumnarPrefix(">> ").withBoundary('-').withBoundaryLengthAdaptive().mix(this.messages));
    }

    @Test
    public void columnarItem() {
        ColumnarMessenger cm = new ColumnarMessenger().withBoundary();
        printWithTest(cm.withColumnarItem("#").mix(this.messages));
        printWithTest(cm.withColumnarItemCounter().mix(this.messages));
    }

    public void printWithTest(String msg) {
        System.out.println("=== This Test Boundary ===");
        System.out.println(msg);
        System.out.println("=== This Test Boundary ===");
        System.out.println();
    }

}
