package ru.org;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Queue {

    //Глобальное хранение очереди
    private static final BlockingQueue<String> queueGlobal = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {
        // Работа продюсера
        Thread producerThread = producerThread();

        // Работа консюмера
        Thread consumerThread = consumerThread();

        // Запуск потоков
        producerThread.start();
        consumerThread.start();
    }

    private static Thread producerThread() {
        return new Thread(() -> {
            while (true) {
                try {
                    // Добавление данных в очередь
                    producerAddInQueue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static Thread consumerThread() {
        return new Thread(() -> {
            while (true) {
                try {
                    // Чтение из очереди
                    producerReadFromQueue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void producerReadFromQueue() throws InterruptedException {
        String data = queueGlobal.take();
        System.out.println("Консьюмер прочел из очереди: " + data);
        Thread.sleep(2000);
    }

    private static void producerAddInQueue() throws InterruptedException {
        String data = "Время " + System.currentTimeMillis();
        queueGlobal.put(data);
        System.out.println("Продюсер добавил данные в очередь: " + data);
        Thread.sleep(2000);
    }
}
