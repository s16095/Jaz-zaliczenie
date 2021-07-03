package pjatk.adrwoj.jazs16095nbp.controller;

import com.google.gson.Gson;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pjatk.adrwoj.jazs16095nbp.model.Currency;
import pjatk.adrwoj.jazs16095nbp.service.NbpCurrencyService;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

@RestController
@RequestMapping("nbpapi")
public class NbpApiController {

    private final NbpCurrencyService nbpCurrencyService;

    public NbpApiController(NbpCurrencyService nbpCurrencyService) {
        this.nbpCurrencyService = nbpCurrencyService;
    }


    @GetMapping("currency")
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity nbpCurrency(
            @RequestParam(name = "table") String table,
            @RequestParam(name = "code") String code,
            @RequestParam(name = "startDate") String startDate,
            @RequestParam(name = "endDate") String endDate) throws Exception {

        String json = downloadJsonFromURL(table, code, startDate, endDate);
//        if (!json.isEmpty()) {
////            Currency currency = new Currency(json.)
//
//
////            nbpCurrencyService.saveCurrency();
//        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }
//    private Currency transformJson() {
//        JsonObject data = new Gson().fromJson(jsonString, JsonObject.class);
//        JsonArray names = data .get("items").getAsJsonArray();
//        for(JsonElement element : names){
//            JsonObject object = element.getAsJsonObject();
//            System.out.println(object.get("metadata").getAsJsonObject().get("name").getAsString());
//        }
//    }


    public  String downloadJsonFromURL(
            String table, String code, String startDate, String endDate) throws MalformedURLException {
        URL nbpApiUrl = new URL(String.format("http://api.nbp.pl/api/exchangerates/rates/%s/%s/%s/%s/", table, code, startDate, endDate));

        Gson gson = new Gson();
        Currency currency = gson.fromJson(String.valueOf(nbpApiUrl), (Type) Currency.class);

        nbpCurrencyService.saveCurrency(currency);
        try {
            StringBuilder jsonText = new StringBuilder();
            try (InputStream myInputStream = nbpApiUrl.openStream();
                 Scanner scanner = new Scanner(myInputStream)) {
                while (scanner.hasNextLine()) {
                    jsonText.append(scanner.nextLine());
                }
                return jsonText.toString();
            }
        } catch (IOException e) {
            System.err.println("Failed to get content " + e.getMessage());
            return null;

        }
    }
}
