package hu.targetshooting.model.service;

import hu.targetshooting.model.domain.ShotResult;

import java.util.List;

public interface DataReader {
    List<ShotResult> getData(String input);
}
