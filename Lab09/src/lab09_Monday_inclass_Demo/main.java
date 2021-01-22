package lab09_Monday_inclass_Demo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

public class main {

    private static final String apiKey = "https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=AAPL&apikey=ONZ9SN8990XVME6L";


    public static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        //current position
        int cp;
        while ((cp = rd.read()) != -1){
            sb.append((char) cp);
        }
        return sb.toString();
    }
    public static JsonObject readJsonFromUrl(String url) throws IOException, JsonParseException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(jsonText).getAsJsonObject();
            return json;
        } finally {
            is.close();
        }
    }


    public static void main(String args[]) throws IOException, JsonParseException {
        JsonObject json = readJsonFromUrl(apiKey);

        JsonObject values = json.get("Monthly Time Series").getAsJsonObject();

        for (Map.Entry<String, JsonElement> entry : values.entrySet()) {
            String date = entry.getKey();
            float closePrice = entry.getValue().getAsJsonObject().get("4. close").getAsFloat();

            System.out.printf("%s : $%.2f \n", date, closePrice);
        }

    }


}
