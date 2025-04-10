package com.customerreward.dto;

import java.util.List;

 /* Model representing reward points and total point for a customer*/

public class CustomerReward {

    private Long customerId;
    private List<RewardPoints> rewardPointsList;
    private int totalPoints;

      public CustomerReward() {
        }

        public CustomerReward(Long customerId, List<RewardPoints> rewardPointsList, int totalPoints) {
            this.customerId = customerId;
            this.rewardPointsList = rewardPointsList;
            this.totalPoints = totalPoints;
        }

        public Long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
        }

        public List<RewardPoints> getRewardPointsList() {
            return rewardPointsList;
        }

        public void setRewardPointsList(List<RewardPoints> rewardPointsList) {
            this.rewardPointsList = rewardPointsList;
        }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
}
