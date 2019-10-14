package hu.targetshooting.controller;

import hu.targetshooting.model.domain.ShotResult;

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
        return String.format("   a.Célt érő lővések: %s%n   b. Az eltalált korongok száma %d%n   d.A versenyzők pontszáma: %d",
                shotResult.getSuccessShotsIndexes(), shotResult.countSuccessShots(), shotResult.getScore());

    }

    private ShotResult getShotResultById(int id) {
        return shotResults.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .get();
    }
}

