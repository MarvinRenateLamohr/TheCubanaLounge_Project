package za.co.marvinlamohr.thecubanalounge;

public class Reward {

    private String rewardsFirstName,rewardsLastName;
    private Long rewardsCardNumber,rewardsPoints;


    public Reward(String rewardsFirstName, String rewardsLastName, Long rewardsCardNumber, Long rewardsPoints) {
        this.rewardsFirstName = rewardsFirstName;
        this.rewardsLastName = rewardsLastName;
        this.rewardsCardNumber = rewardsCardNumber;
        this.rewardsPoints = rewardsPoints;
    }


    public String getRewardsFirstName() {
        return rewardsFirstName;
    }

    public void setRewardsFirstName(String rewardsFirstName) {
        this.rewardsFirstName = rewardsFirstName;
    }

    public String getRewardsLastName() {
        return rewardsLastName;
    }

    public void setRewardsLastName(String rewardsLastName) {
        this.rewardsLastName = rewardsLastName;
    }

    public Long getRewardsCardNumber() {
        return rewardsCardNumber;
    }

    public void setRewardsCardNumber(Long rewardsCardNumber) {
        this.rewardsCardNumber = rewardsCardNumber;
    }

    public Long getRewardsPoints() { return rewardsPoints; }

    public void setRewardsPoints(Long rewardsPoints) { this.rewardsPoints = rewardsPoints; }
}
