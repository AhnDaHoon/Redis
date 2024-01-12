package com.example.redis.ranking;

import com.example.redis.service.RankingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class RankingTest {

    @Autowired
    private RankingService rankingService;

    /**
     * Redis SortedSets의 성능을 알아보기 위한 테스트
     */
    @Test
    void intMemorySortPerformance(){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            int score = (int)(Math.random() * 1000000);
            list.add(score);
        }

        Instant before = Instant.now();
        Collections.sort(list);
        Duration elapsed = Duration.between(before, Instant.now());
        System.out.println("elapsed = " + (elapsed.getNano() / 1000000) + "ms");
        // elapsed = 233ms
    }

    @Test
    void getRanks(){
        // 최초 연결에는 초기비용이 더 들어서 의미 없는 호출 실행
        rankingService.getTopRank(1);

        Instant before = Instant.now();
        Long userRank = rankingService.getUserRanking("user_100");
        Duration elapsed = Duration.between(before, Instant.now());

        System.out.println("userRank = " + (elapsed.getNano() / 1000000) + "ms");
        // userRank = 1ms

        before = Instant.now();
        List<String> topRank = rankingService.getTopRank(10);
        elapsed = Duration.between(before, Instant.now());

        System.out.println("topRank = " + (elapsed.getNano() / 1000000) + "ms");
        // topRank = 0ms
    }

    @Test
    void insertScore(){
        for (int i = 0; i < 1000000; i++) {
            int score = (int)(Math.random() * 1000000);
            String UserId = "user_"+i;

            rankingService.setUserScore(UserId, score);
        }
    }

}
