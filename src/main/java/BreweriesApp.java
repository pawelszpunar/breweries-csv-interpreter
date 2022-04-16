import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import enums.Brewery;
import helpers.CreateBrewery;
import helpers.Tasks;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class BreweriesApp {

    public static final String pathToFile = "src/main/resources/breweries_usa.csv";

    public static void main(String[] args) {

        List<Brewery> breweries = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(pathToFile))) {
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                    .build();
            String[] record;
            while ((record = csvReader.readNext()) != null) {
                Brewery brewery = CreateBrewery.build(record);
                breweries.add(brewery);
            }
            csvReader.close();
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        Tasks.numberOfBreweriesInEachState(breweries); // A - What is the number of breweries in each state?
        Tasks.whatAreTheTopCitiesForBreweries(breweries); // B - What are the top cities for breweries?
        Tasks.howManyBreweriesHaveTheLinkToTheWebsite(breweries); // C - How many breweries have the link to the website?
        Tasks.howManyBreweriesLocatedInDelawareStateAlsoOfferTacos(breweries); // D - How many breweries located in Delaware state also offer tacos?
        Tasks.whatPercentageOfBreweriesInEachStateOffersWine(breweries); // E - What percentage of breweries in each state offers wine?
        Tasks.findDuplicateBreweries(breweries); // F - Find the duplicated records. How many breweries are listed more than once?
    }
}
