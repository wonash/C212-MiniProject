import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

class WeatherForecast {


    /**
     * contact a weather API with some parameters to retrieve the weather report for the upcoming week
     * and display the weather report
     * @param args Command-line arguments (unused).
     * @throws Exception When error occurs during HTTP request or JSON parsing
     */
    public static void main(String[] args) throws Exception {

        try {
            URL url = new URL("https://api.open-meteo.com/v1/forecast?latitude=39.1653&longitude=86.5264&hourly=temperature_2m&temperature_unit=fahrenheit&timezone=EST");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder infoString = new StringBuilder();
                // Scanner scanner = new Scanner(url.openStream());
                Scanner scanner = new Scanner(new InputStreamReader(conn.getInputStream()));


                while (scanner.hasNext()) {
                    infoString.append(scanner.nextLine());
                }

                scanner.close();

                // System.out.println(infoString);

//                JsonParser parse = new JsonParser();
//                JsonArray dataObject = (JsonArray) parse.parse(String.valueOf(infoString));

//                JsonElement jsonElement = JsonParser.parseString(infoString.toString());
//                JsonObject mainObject = jsonElement.getAsJsonObject();
//
////                JsonObject wxData = (JsonObject) dataObject.get(0);
//                JsonObject wxDataHr = mainObject.getAsJsonObject("hourly");
//                JsonObject wxDataLat = mainObject.getAsJsonObject("latitude");
//                System.out.println("Weather Data: " + wxDataHr.toString());
//                System.out.println("Weather Data: " + wxDataLat.toString());
//                        // check project website

                JsonElement e = JsonParser.parseString(String.valueOf(infoString));

                // System.out.println(e.toString());

                JsonElement hourlyObject = e.getAsJsonObject().get("hourly");

                // System.out.println(hourlyObject.toString());

                for (JsonElement s : hourlyObject.getAsJsonObject().get("time").getAsJsonArray()) {
                    // System.out.println(s.getAsString());
                }

                for (JsonElement t : hourlyObject.getAsJsonObject().get("temperature_2m").getAsJsonArray()) {
                    // System.out.println(t.getAsFloat());

                }

                // forecast print

                System.out.println("\nWouldn't you like to know weather boy?\n");

                System.out.println("Bloomington 7-Day Forecast in Fahrenheit: ");

//                for (JsonElement s : hourlyObject.getAsJsonObject().get("time").getAsJsonArray()) {
//                    for (JsonElement t : hourlyObject.getAsJsonObject().get("temperature_2m").getAsJsonArray()) {
//
//                    }
//                }

                for (int i = 0; i < hourlyObject.getAsJsonObject().get("time").getAsJsonArray().size(); i++) {

                    if(i % 24 == 0) {
                        System.out.println("\n Forecast for " + hourlyObject.getAsJsonObject().get("time").getAsJsonArray().get(i).getAsString().substring(0,10));
                    }
                    if(i % 3 == 0) {
                        System.out.println("\t" + hourlyObject.getAsJsonObject().get("time").getAsJsonArray().get(i).getAsString().substring(11) + " | " + hourlyObject.getAsJsonObject().get("temperature_2m").getAsJsonArray().get(i).getAsFloat());

                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

}

// System.out.println(hourlyObject.getAsJsonObject().get("time").getAsJsonArray().get(i).getAsString().charAt(12));
// result
// System.out.println(hourlyObject.getAsJsonObject().get("time").getAsJsonArray().get(i).getAsString() + " | " + hourlyObject.getAsJsonObject().get("temperature_2m").getAsJsonArray().get(i).getAsFloat());
