package hu.targetshooting.controller;

import hu.targetshooting.model.domain.ShotResult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ShotService {
    private final List<ShotResult> shotResults;

    public ShotService(List<ShotResult> shotResults) {
        this.shotResults = shotResults;
    }

    public String getBetterIds() {
        return shotResults.stream()
                .filter(ShotResult::hasTwoSuccessShotsInRow)
                .map(ShotResult::getId)
                .map(Object::toString)
                .collect(Collectors.joining(" "));

    }

    public Integer getLongestShotSequencesId() {
        return shotResults.stream()
                .max(Comparator.comparing(ShotResult::getShotsCount))
                .map(ShotResult::getId)
                .get();
    }

    public String getShotResultDetails(int id) {
        ShotResult shotResult = getShotResultById(id);
        return String.format("   a. Célt érő lővések: %s%n" +
                        "   b. Az eltalált korongok száma %d%n" +
                        "   c. A leghoszabb hibátlan sorozat hossza: %d%n" +
                        "   d. A versenyzők pontszáma: %d",
                shotResult.getSuccessShotsIndexes(), shotResult.countSuccessShots(),
                shotResult.getLongestSuccessSequenceSize(), shotResult.getScore());

    }

    public List<String> getFinalResult() {
        List<String> lines = new ArrayList<>();
        List<ShotResult> finalResultList = createFinalResult();
        int prewScore = 0, prewOrder = 0;
        for (int i = 0; i < finalResultList.size(); i++) {
            ShotResult shotResult = finalResultList.get(i);
            int order = shotResult.getScore() == prewScore ? prewOrder : i + 1;
            lines.add(order + "\t" + shotResult.getId() + "\t" + shotResult.getScore());
            prewScore = shotResult.getScore();
            prewOrder = order;
        }
        return lines;
//        return createFinalResult().stream()
//                .map(i -> i.getId() + "\t" + i.getScore())
//                .collect(Collectors.toList());
    }

    private List<ShotResult> createFinalResult() {
        return shotResults.stream()
                .sorted((i, j) -> j.getScore().compareTo(i.getScore()))
                .collect(Collectors.toList());
    }

    private ShotResult getShotResultById(int id) {
        return shotResults.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .get();
    }
}

