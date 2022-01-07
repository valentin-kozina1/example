package com.pb.kozina.hw14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientServer {

    public static void main(String[] args) throws Exception {
        // Определяем номер порта, на котором нас ожидает сервер для ответа
        int port = 1234;

        // Открыть сокет (Socket) для обращения к локальному компьютеру
        // Сервер мы будем запускать на этом же компьютере
        // Это специальный класс для сетевого взаимодействия c клиентской стороны
        Socket socket = new Socket("localhost", port);

        // Пишем, что стартовал клиент
        System.out.println("Клиент подключился ");
        System.out.println("Сервер ждет запроса");
        Scanner scan = new Scanner(System.in);
        String message = scan.nextLine();

        // Создать поток для чтения символов из сокета
        // Для этого надо открыть поток сокета - socket.getInputStream()
        // Потом преобразовать его в поток символов - new InputStreamReader
        // И уже потом сделать его читателем строк - BufferedReader
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Создать поток для записи символов в сокет
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Отправляем тестовую строку в сокет
        out.println(message);
        System.out.println("Запрос отправлен на сервер ");

        String str;
        // Входим в цикл чтения, что нам ответил сервер
        while ((str = in.readLine()) != null) {
            // Печатаем ответ от сервера на консоль для проверки
            System.out.println(str);
        }

        in.close();
        out.close();
        socket.close();
    }
}
