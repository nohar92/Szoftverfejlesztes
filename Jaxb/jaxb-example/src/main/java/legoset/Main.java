package legoset;

import java.time.Year;
import java.util.*;


import jaxb.JAXBHelper;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main {

    public static void main(String[] args) throws Exception {

        LegoSet legoSet = new LegoSet();
        legoSet.setNumber("75211");
        legoSet.setName("Imperial TIE Fighter");
        legoSet.setTheme("Star Wars");
        legoSet.setSubTheme("Solo");
        legoSet.setYear(Year.of(2018));
        legoSet.setPieces(519);
        legoSet.setUrl("https://brickset.com/sets/75211-1/Imperial-TIE-Fighter");

        Set<String> tags = new HashSet<>();
        tags.add("Starfighter");
        tags.add("Stormtrooper");
        tags.add("Star Wars");
        tags.add("Solo");
        legoSet.setTags(tags);


        ArrayList<Minifig> names = new ArrayList<>();
        names.add(new Minifig("Imperial Mudtrooper", 2));
        names.add(new Minifig("Imperial Pilot", 1));
        names.add(new Minifig("Mimban Stormtrooper", 1));
        legoSet.setMinifigs(names);

        ArrayList<Weight> weights = new ArrayList<>();
        weights.add(new Weight(0.89, "kg"));
        legoSet.setWeights(weights);


        JAXBHelper.toXML(legoSet, System.out);
        JAXBHelper.toXML(legoSet, new FileOutputStream("legoSet.xml"));
        System.out.println(JAXBHelper.fromXML(LegoSet.class, new FileInputStream("legoSet.xml")));

    }


}
