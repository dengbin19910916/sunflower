package io.xxx.sunflower.site.service;

import java.util.List;

public interface MyEventListener<T> {

    void onDataChunk(List<T> chunk);

    void processComplete();
}
