package com.rafaelrain.downloader.core;

import com.google.common.collect.Queues;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Downloader {

    private final ForkJoinPool pool = new ForkJoinPool(5000);

    private final Queue<URL> urlQueue = Queues.newConcurrentLinkedQueue();
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    private final File folder;

    private int id = 0;

    public Downloader(File folder) {
        this.folder = folder;
        if(!folder.exists()) folder.mkdir();

        executor.scheduleWithFixedDelay(
                this::update,
                0,
                10,
                TimeUnit.MILLISECONDS
        );

    }

    public void addToQueue(String uri, int times) throws MalformedURLException {
        addToQueue(new URL(uri), times);
    }

    public void addToQueue(URL url, int times) {
        int i = 0;

        while (i < times) {
            urlQueue.add(url);
            i++;
        }
    }

    private void update() {
        if (urlQueue.isEmpty()) return;

        URL url = urlQueue.poll();
        File file = new File(folder, "Download-" + id++);

        pool.execute(() -> {
            try {
                FileUtils.copyURLToFile(url, file);

                String name = file.getName();
                String size = String.valueOf((file.length() / 1024) / 1024);

                System.out.println(
                        String.format("Terminado o download de %s com o tamanho de %sMB", name, size)
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void disable() {
        executor.shutdown();

        pool.shutdown();
    }
}
