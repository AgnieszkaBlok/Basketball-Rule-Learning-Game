package io.github.agnieszkablok.poznajkoszykowke.main;

import io.github.agnieszkablok.poznajkoszykowke.items.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AssetSetter {

    private static Item placeItemFromLine(String line){

        String[] tokens = line.split(",");
        String itemName;
        int x, y;

        try{
            itemName = tokens[0];
            x = Integer.parseInt(tokens[1]);
            y = Integer.parseInt(tokens[2]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
            System.out.println("Couldn't parse item line \"" + line + "\"");
            return null;
        }

        return switch (itemName) {
            case "Ball" -> new Ball(x, y);
            case "Chest" -> new Chest(x, y);
            case "Door" -> new Door(x, y);
            case "Key" -> new Key(x, y);
            case "Whistle" -> new Whistle(x, y);
            default -> null;
        };
    }

    public static List<Item> getItemsFromFile(String filepath) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        return reader.lines().map(AssetSetter::placeItemFromLine)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
