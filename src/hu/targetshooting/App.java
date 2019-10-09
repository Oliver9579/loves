package hu.targetshooting;

import hu.targetshooting.controller.ShotFacade;
import hu.targetshooting.model.service.DataReader;
import hu.targetshooting.model.service.FileDataReader;
import hu.targetshooting.model.service.ScoreCalculator;

public class App {
    private final ShotFacade shotFacade;

    public App() {
        DataReader data = new FileDataReader(new ScoreCalculator());
        shotFacade = new ShotFacade(data.getData("verseny.txt"));
    }

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        System.out.println("2. feladat: " + shotFacade.getBetterIds());
    }
}
