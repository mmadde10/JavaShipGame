package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PirateShipFactoryGenerator {
	private List<PirateShipFactory> factories;
    private Random rand;

    public PirateShipFactoryGenerator() {
        factories = new ArrayList<>();

        //Add one of each factory
        factories.add(new NormalPirateShipFactory());
        factories.add(new ExtremePirateShipFactory());

        rand = new Random();
    }

    public PirateShipFactory getRandomFactory() {
        int index = rand.nextInt(factories.size());
        return factories.get(index);
    }
}
