package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler {
    private final MessageHandler targetHandler;
    private final int bufferSize;
    private final List<String> buffer;

    public MemoryHandler(MessageHandler targetHandler, int bufferSize) {
        this.targetHandler = targetHandler;
        this.bufferSize = bufferSize;
        this.buffer = new ArrayList<>();
    }

    @Override
    public void handle(String message) {
        buffer.add(message);

        if (buffer.size() >= bufferSize) {
            flush();
        }
    }

    public void flush() {
        if (!buffer.isEmpty()) {
            for (String message : buffer) {
                targetHandler.handle(message);
            }
            buffer.clear();
        }
    }

    public int getBufferSize() {
        return buffer.size();
    }
}