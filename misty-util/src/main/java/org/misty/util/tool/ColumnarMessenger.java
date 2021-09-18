package org.misty.util.tool;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.IntUnaryOperator;

public class ColumnarMessenger {

    public static ColumnarMessenger create() {
        return new ColumnarMessenger();
    }

    class ItemPrinter {

        private final Collection<String> items;

        private final BiConsumer<StringBuilder, String> printer;

        private int itemMaxLength = 0;

        private int prefixLength = 0;

        public ItemPrinter(Collection<String> items, String columnarPrefix, String columnarItem, boolean countBoundaryLength) {
            this.items = items;

            BiConsumer<StringBuilder, String> printer = buildPrinter(countBoundaryLength);
            printer = withColumnarPrefix(printer, columnarPrefix);
            printer = withColumnarItem(printer, columnarItem);
            printer = withMsg(printer);
            this.printer = printer;
        }

        public void print(StringBuilder sb) {
            boolean first = true;
            for (String item : this.items) {
                if (first) {
                    first = false;
                } else {
                    printNewline(sb);
                }

                this.printer.accept(sb, item);
            }
        }

        private BiConsumer<StringBuilder, String> buildPrinter(boolean countBoundaryLength) {
            return countBoundaryLength ? (sb, msg) -> {
                if (msg.length() > this.itemMaxLength) {
                    this.itemMaxLength = msg.length();
                }
            } : (sb, msg) -> {
            };
        }

        private BiConsumer<StringBuilder, String> withColumnarPrefix(BiConsumer<StringBuilder, String> printer, String columnarPrefix) {
            if (columnarPrefix == null || columnarPrefix.isEmpty()) {
                return printer;
            }

            this.prefixLength += columnarPrefix.length();

            return (sb, msg) -> {
                printer.accept(sb, msg);
                sb.append(columnarPrefix);
            };
        }

        private BiConsumer<StringBuilder, String> withColumnarItem(BiConsumer<StringBuilder, String> printer, String columnarItem) {
            boolean useCounter = columnarItem == null;

            if (useCounter) {
                int itemsSizeLength = String.valueOf(this.items.size()).length();

                this.prefixLength += itemsSizeLength + 3;

                String format = "%0" + itemsSizeLength + "d";
                AtomicInteger counter = new AtomicInteger(1);
                return (sb, msg) -> {
                    printer.accept(sb, msg);
                    sb.append("(").append(String.format(format, counter.getAndIncrement())).append(") ");
                };
            }

            this.prefixLength += columnarItem.length();

            return (sb, msg) -> {
                printer.accept(sb, msg);
                sb.append(columnarItem);
            };
        }

        private BiConsumer<StringBuilder, String> withMsg(BiConsumer<StringBuilder, String> printer) {
            return (sb, msg) -> {
                printer.accept(sb, msg);
                sb.append(msg);
            };
        }

        public int getItemMaxLength() {
            return itemMaxLength;
        }

        public int getPrefixLength() {
            return prefixLength;
        }
    }

    private static final int ADAPTIVE_BOUNDARY_LENGTH = 0;

    private boolean newlineFirst = false;

    private String beginning = "";

    private String ending = "";

    private String columnarPrefix = "";

    private boolean boundaryUsable = false;

    private char boundaryChar = '-';

    private int boundaryLength = ADAPTIVE_BOUNDARY_LENGTH;

    private String columnarItem;

    /**
     * default
     */
    public ColumnarMessenger withoutNewlineFirst() {
        this.newlineFirst = false;
        return this;
    }

    public ColumnarMessenger withNewlineFirst() {
        this.newlineFirst = true;
        return this;
    }

    /**
     * default
     */
    public ColumnarMessenger withoutBeginning() {
        this.beginning = "";
        return this;
    }

    public ColumnarMessenger withBeginning(String beginning) {
        this.beginning = beginning;
        return this;
    }

    /**
     * default
     */
    public ColumnarMessenger withoutEnding() {
        this.ending = "";
        return this;
    }

    public ColumnarMessenger withEnding(String ending) {
        this.ending = ending;
        return this;
    }

    /**
     * default
     */
    public ColumnarMessenger withoutColumnarPrefix() {
        this.columnarPrefix = "";
        return this;
    }

    public ColumnarMessenger withColumnarPrefix(String columnarPrefix) {
        this.columnarPrefix = columnarPrefix;
        return this;
    }

    /**
     * default
     */
    public ColumnarMessenger withoutBoundary() {
        this.boundaryUsable = false;
        return this;
    }

    public ColumnarMessenger withBoundary() {
        this.boundaryUsable = true;
        return this;
    }

    public ColumnarMessenger withBoundary(char boundaryChar) {
        this.boundaryUsable = true;
        this.boundaryChar = boundaryChar;
        return this;
    }

    /**
     * default
     */
    public ColumnarMessenger withBoundaryLengthAdaptive() {
        this.boundaryLength = ADAPTIVE_BOUNDARY_LENGTH;
        return this;
    }

    public ColumnarMessenger withBoundaryLength(int boundaryLength) {
        this.boundaryLength = boundaryLength <= 0 ? ADAPTIVE_BOUNDARY_LENGTH : boundaryLength;
        return this;
    }

    /**
     * default
     */
    public ColumnarMessenger withColumnarItemCounter() {
        this.columnarItem = null;
        return this;
    }

    public ColumnarMessenger withColumnarItem(String columnarItem) {
        this.columnarItem = columnarItem;
        return this;
    }

    public String mix(Collection<String> items) {
        StringBuilder sb = new StringBuilder();

        boolean countBoundaryLength = this.boundaryUsable && isAdaptiveBoundaryLength(boundaryLength);
        ItemPrinter itemPrinter = new ItemPrinter(items, this.columnarPrefix, this.columnarItem, countBoundaryLength);
        itemPrinter.print(sb);

        printBeginningAndEnding(sb, this.beginning, this.ending);

        if (this.boundaryUsable) {
            printBoundary(sb, itemPrinter, this.boundaryLength, this.boundaryChar, this.beginning, this.ending);
        }

        if (this.newlineFirst) {
            sb.insert(0, System.lineSeparator());
        }

        return sb.toString();
    }

    protected void printBeginningAndEnding(StringBuilder sb, String beginning, String ending) {
        if (beginning != null && !beginning.isEmpty()) {
            sb.insert(0, beginning + System.lineSeparator());
        }

        if (ending != null && !ending.isEmpty()) {
            printNewline(sb);
            sb.append(ending);
        }
    }

    protected void printBoundary(StringBuilder sb, ItemPrinter itemPrinter, int boundaryLength, char boundaryChar,
                                 String beginning, String ending) {
        boolean isAdaptiveBoundaryLength = isAdaptiveBoundaryLength(boundaryLength);
        if (isAdaptiveBoundaryLength) {
            boundaryLength = itemPrinter.getItemMaxLength();
            boundaryLength += itemPrinter.getPrefixLength();

            int beginningLineMaxLength = findMaxLengthEachLine(beginning);
            int endingMaxLength = findMaxLengthEachLine(ending);

            if (beginningLineMaxLength > boundaryLength || endingMaxLength > boundaryLength) {
                boundaryLength = Math.max(beginningLineMaxLength, endingMaxLength);
            }
        }

        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < boundaryLength; i++) {
            sb2.append(boundaryChar);
        }

        String boundary = sb2.toString();

        sb.insert(0, boundary + System.lineSeparator());
        printNewline(sb);

        sb.append(boundary);
    }

    protected boolean isAdaptiveBoundaryLength(int boundaryLength) {
        return boundaryLength <= ADAPTIVE_BOUNDARY_LENGTH;
    }

    protected void printNewline(StringBuilder sb) {
        sb.append(System.lineSeparator());
    }

    protected int findMaxLengthEachLine(String target) {
        IntUnaryOperator indexOf = (formIndex) -> target.indexOf(System.lineSeparator(), formIndex);

        int index = indexOf.applyAsInt(0);
        if (index <= 0) {
            return target.length();
        }

        int length = index;
        while (index > 0) {
            int earlierIndex = index;
            index = indexOf.applyAsInt(index + 1);

            int intervalLength = index < 0 ? target.length() - earlierIndex : index - earlierIndex;
            intervalLength -= System.lineSeparator().length();

            if (intervalLength > length) {
                length = intervalLength;
            }
        }

        return length;
    }

}
