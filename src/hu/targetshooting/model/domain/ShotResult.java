package hu.targetshooting.model.domain;

public class ShotResult {

    private final Integer id;
    private final String shots;
    private final Integer score;

    public ShotResult(Integer id, String shots, Integer score) {
        this.id = id;
        this.shots = shots;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public Integer getScore() {
        return score;
    }

    public String getShots() {
        return shots;
    }

    @Override
    public String toString() {
        return "\n{" +
                "id=" + id +
                ", shots='" + shots + '\'' +
                ", score=" + score +
                '}';
    }
}
