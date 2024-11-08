package com.weather.clima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputFilter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@SpringBootApplication
public class ClimaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClimaApplication.class, args);

		Scanner input = new Scanner(System.in);
		String urlBase = Services.URL_BASE;
		String apiKey = Services.API_KEY;
		String city;

		Functions.greeting();

		boolean continuar = true;
		while(continuar){
			try {
			System.out.println("Digite 0 se quiser sair: ");
			System.out.println("Qual cidade deseja saber o clima: ");
			city = input.nextLine();

			if (city.equals("0")){
				System.out.println("tchau...");
				continuar = false;

			}else{
			String url = urlBase + city + "&appid=" + apiKey;
			String response = Functions.getData(url);
			Functions.transformData(response);
			}

			}catch (Exception e){
				System.out.println(e.getMessage());
			}
		}
		input.close();
	}


}
