package com.CustomerReward.dto;

import lombok.*;

/**
 * Model representing reward points for a customer in a specific month.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RewardPoints {

    private String month;
    private int points;

}
