package futures;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class WeatherPrinter implements Runnable {

	private String city;
	private CompletableFuture<HttpResponse<String>> info;

	public WeatherPrinter(String city) {
		this.city = city;
	}

	@Override
	public void run() {
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI(
							"http://api.weatherapi.com/v1/current.json?key=aba000064a724e1dbb8173624251402&q=" + city))
					.GET().build();
			info = HttpClient.newBuilder().build().sendAsync(request, HttpResponse.BodyHandlers.ofString());

			info.thenAccept(result -> System.out.println(result.body()));
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		print();
	}

	public void print() {
		try {
			info.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
