package com.CustomerReward.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

 /* Model representing reward points and total point for a customer*/


public class CustomerReward {

    private Long customerId;
    private List<RewardPoints> rewardPointsList;
    private int totalPoints;


}
