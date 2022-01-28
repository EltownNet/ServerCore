package net.eltown.servercore.components.data.quests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class QuestPlayer {

    private final String player;
    private List<QuestData> questPlayerData;

    @AllArgsConstructor
    @Getter
    @Setter
    public static class QuestData {

        private String questNameId;
        private String questSubId;
        private String data;
        private int current;
        private int required;
        private long expire;

    }

    @AllArgsConstructor
    @Getter
    public static class FullQuestPlayer {

        private Quest quest;
        private QuestPlayer.QuestData questPlayerData;

    }

}
