package hu.targetshooting.model.service;

import hu.targetshooting.model.domain.ShotResult;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileDataReader implements DataReader {

    private final ScoreCalculator scoreCalculator;
    private Integer id;

    public FileDataReader(ScoreCalculator scoreCalculator) {
        this.scoreCalculator = scoreCalculator;
        this.id = 0;
    }

    @Override
    public List<ShotResult> getData(String input) {
        return parse(read(input));
    }

    private List<String> read(String input) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            lines = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private List<ShotResult> parse(List<String> lines) {
        lines.remove(0);
        return lines.stream()
                .map(this::createShotResult)
                .collect(Collectors.toList());

    }

    private ShotResult createShotResult(String line) {
        return new ShotResult(++id, line, scoreCalculator.value(line));
    }
}
