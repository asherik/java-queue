package ru.org;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Queue {
    //Глобальное хранение очереди
    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {
        // Работа продюсера
        Thread producerThread = new Thread(() -> {
            while (true) {
                try {
                    // Добавление данных в очередь
                    String data = "Время " + System.currentTimeMillis();
                    queue.put(data);
                    System.out.println("Продюсер добавил данные в очередь: " + data);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Работа консюмера
        Thread consumerThread = new Thread(() -> {
            while (true) {
                try {
                    // Чтение из очереди
                    String data = queue.take();
                    System.out.println("Консьюмер прочел из очереди: " + data);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Запуск потоков
        producerThread.start();
        consumerThread.start();
    }
}
