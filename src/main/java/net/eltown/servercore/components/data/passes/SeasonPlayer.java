package net.eltown.servercore.components.data.passes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class SeasonPlayer {

    private String name;
    private int points;
    private List<String> claimedRewards;
    private boolean isPremium;

}
