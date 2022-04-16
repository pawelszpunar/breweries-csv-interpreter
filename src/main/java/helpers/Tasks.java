package helpers;

import enums.Brewery;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Tasks {

    public static void numberOfBreweriesInEachState(List<Brewery> breweries) {

        // A - What is the number of breweries in each state?

        LinkedHashMap<String, Integer> breweriesInStates = mapBreweriesInStates(breweries);
        System.out.println("A) What is the number of breweries in each state?");
        System.out.println(breweriesInStates);
    }

    public static LinkedHashMap<String, Integer> mapBreweriesInStates(List<Brewery> breweries) {
        Map<String, Integer> statesMap = new HashMap<>();

        for (Brewery temp : breweries) {
            if (statesMap.containsKey(temp.getProvince())) {
                int updatedValue = statesMap.get(temp.getProvince()) + 1;
                statesMap.put(temp.getProvince(), updatedValue);
            } else {
                statesMap.put(temp.getProvince(), 1);
            }
        }
        LinkedHashMap<String, Integer> reverseSortedStates = new LinkedHashMap<>();
        statesMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedStates.put(x.getKey(), x.getValue()));
        return reverseSortedStates;
    }

    public static void whatAreTheTopCitiesForBreweries(List<Brewery> breweries) {

        // B - What are the top cities for breweries?

        Map<String, Integer> citiesMap = new HashMap<>();

        for (Brewery temp : breweries) {
            if (citiesMap.containsKey(temp.getCity())) {
                int updatedValue = citiesMap.get(temp.getCity()) + 1;
                citiesMap.put(temp.getCity(), updatedValue);
            } else {
                citiesMap.put(temp.getCity(), 1);
            }
        }

        LinkedHashMap<String, Integer> reverseSortedCities = sortMap(citiesMap);

        System.out.println("B) What are the top cities for breweries?");
        System.out.println(reverseSortedCities);
    }

    public static void howManyBreweriesHaveTheLinkToTheWebsite(List<Brewery> breweries) {

        // C - How many breweries have the link to the website?

        int breweriesWithNoWebsite = (int) breweries
                .stream()
                .filter(x -> !x.getWebsite().isEmpty())
                .count();
        System.out.println("C) How many breweries have the link to the website?");
        System.out.println("Breweries with website: " + breweriesWithNoWebsite);
    }

    public static void howManyBreweriesLocatedInDelawareStateAlsoOfferTacos(List<Brewery> breweries) {

        // D - How many breweries located in Delaware state also offer tacos?
        // Note: Delaware is DE in province

        int numberOfBreweriesInDelawareOfferingTacos = (int) breweries
                .stream()
                .filter(x -> x.getProvince().equals("DE"))
                .filter(y -> {
                    if (y.getMenusList() != null) {
                        return y.getMenusList().stream().anyMatch(z -> z.getName().toLowerCase().contains("taco"));
                    }
                    return false;
                })
                .count();

        System.out.println("D) How many breweries located in Delaware state also offer tacos?");
        System.out.println("Answer: " + numberOfBreweriesInDelawareOfferingTacos);
    }

    public static void whatPercentageOfBreweriesInEachStateOffersWine(List<Brewery> breweries) {

        // E - What percentage of breweries in each state offers wine?

        LinkedHashMap<String, Integer> statesMap = mapBreweriesInStates(breweries);
        LinkedHashMap<String, String> statesMapOfferingWinePercentage = new LinkedHashMap<>();
        Set<String> keys = statesMap.keySet();

        for (String key : keys) {
            int breweriesOfferingWine = (int) breweries
                    .stream()
                    .filter(x -> x.getProvince().equals(key))
                    .filter(y -> {
                        if (y.getMenusList() != null) {
                            return y.getMenusList().stream().anyMatch(z -> z.getName().toLowerCase().contains("wine"));
                        }
                        return false;
                    })
                    .count();
            double percentage = (double) (100 * breweriesOfferingWine) / statesMap.get(key);
            String percentageString;
            if (percentage != 0) {
                percentageString = String.format("%.2f", percentage);
            } else {
                percentageString = String.format("%.0f", percentage);
            }
            statesMapOfferingWinePercentage.put(key, percentageString + "%");
        }

        System.out.println("E) What percentage of breweries in each state offers wine?");
        System.out.println(statesMapOfferingWinePercentage);
    }

    public static void findDuplicateBreweries(List<Brewery> breweries) {

        // F - Find the duplicated records. How many breweries are listed more than once?

        List<String> namesOfBreweries = breweries
                .stream()
                .map(Brewery::getName)
                .toList();
        Map<String, Integer> countedDuplicates = new HashMap<>();
        for (String tempName : namesOfBreweries) {
            countedDuplicates.put(tempName, Collections.frequency(namesOfBreweries, tempName));
        }

        LinkedHashMap<String, Integer> countedDuplicatesSorted = sortMap(countedDuplicates);

        Set<String> uniqueBreweries = Set.copyOf(namesOfBreweries);

        System.out.println("F) Find the duplicated records. How many breweries are listed more than once?");
        System.out.println("Total breweries: " + namesOfBreweries.size());
        System.out.println("Unique breweries: " + uniqueBreweries.size());
        System.out.println("Duplicated breweries: " + (namesOfBreweries.size() - uniqueBreweries.size()));
        System.out.println("Top 10 of breweries that appear more than once:");
        int countUniques = 0;
        for (Map.Entry<String, Integer> entry : countedDuplicatesSorted.entrySet()) {
            if (entry.getValue() > 1 && countUniques < 10) {
                System.out.println("\"" + entry.getKey() + "\" - " + entry.getValue());
                countUniques++;
            }
        }
    }

    public static LinkedHashMap<String, Integer> sortMap(Map<String, Integer> mapToSort) {
        LinkedHashMap<String, Integer> sorted = new LinkedHashMap<>();
        mapToSort
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
        return sorted;
    }
}
