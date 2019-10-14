package hu.targetshooting;

import hu.targetshooting.controller.ShotService;
import hu.targetshooting.model.service.Console;
import hu.targetshooting.model.service.DataReader;
import hu.targetshooting.model.service.FileDataReader;
import hu.targetshooting.model.service.ScoreCalculator;

import java.util.Scanner;

public class App {
    private final ShotService shotService;
    private final Console console;

    public App() {
        DataReader data = new FileDataReader(new ScoreCalculator());
        shotService = new ShotService(data.getData("verseny.txt"));
        console=new Console(new Scanner(System.in));
    }

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        System.out.println("2. feladat: Az egymást követően többször találó versenyzők: " + shotService.getBetterIds());
        System.out.println("3. feladat: A legtöbb lövést leadó versenyző rajtszáma: " + shotService.getLongestShotSequencesId());
        int id = console.raedInt("5. feladat: Adjon meg egy rajtszámot: ");
        System.out.println(shotService.getShotResultDetails(id));
    }
}
