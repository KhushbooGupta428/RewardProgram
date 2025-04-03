package com.CustomerReward.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

/* Model representing reward points and total point for a customer*/


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReward {

    private Long customerId;
    private List<RewardPoints> rewardPointsList;
    private int totalPoints;


}
