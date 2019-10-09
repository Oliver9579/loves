package hu.targetshooting.controller;

import hu.targetshooting.model.domain.ShotResult;

import java.util.List;
import java.util.stream.Collectors;

public class ShotFacade {
    private final List<ShotResult> shotResults;

    public ShotFacade(List<ShotResult> shotResults) {
        this.shotResults = shotResults;
    }

    public String getBetterIds() {
        return shotResults.stream()
                .filter(i -> i.getShots().indexOf("++") > -1)
                .map(i -> i.getId())
                .map(i -> i.toString())
                .collect(Collectors.joining(" "));

    }
}

