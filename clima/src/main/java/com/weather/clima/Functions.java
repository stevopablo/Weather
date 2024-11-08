package com.weather.clima;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.JsonAdapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Functions {


    public static String getData(String urlString) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection connec = (HttpURLConnection) url.openConnection();
            connec.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connec.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connec.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static void transformData(String data){
        JsonObject json = JsonParser.parseString(data).getAsJsonObject();
        JsonObject main = json.getAsJsonObject("main");
        JsonObject wind = json.getAsJsonObject("wind");

        double temperature = main.get("temp").getAsDouble() - 273.15;
        String tempFormat = String.format("%.1f", temperature);
        System.out.println("tempFormat = " + tempFormat + " C");

        double windSpeed = wind.get("speed").getAsDouble();
        System.out.println("Wind Speed = " + windSpeed +  " km/h");

        int humidity = main.get("humidity").getAsInt();
        System.out.println("Humidity = " + humidity + "%");
    }

    public static void greeting(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual seu nome: ");
        String name = scanner.nextLine();
        System.out.println("Bem-vindo " + name + " !");
    }

}
