package za.co.marvinlamohr.thecubanalounge;

public class Prize {

    private int prizeId;
    private String prizeName;
    private int prizePoints;

    public Prize(int prizeId, String prizeName, int prizePoints) {
        this.prizeId = prizeId;
        this.prizeName = prizeName;
        this.prizePoints = prizePoints;
    }

    public int getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(int prizeId) {
        this.prizeId = prizeId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public int getPrizePoints() {
        return prizePoints;
    }

    public void setPrizePoints(int prizePoints) {
        this.prizePoints = prizePoints;
    }
}
