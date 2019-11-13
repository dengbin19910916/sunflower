package io.xxx.sunflower.test.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedComeController {

    private final FeedComeService feedComeService;

    public FeedComeController(FeedComeService feedComeService) {
        this.feedComeService = feedComeService;
    }

    @GetMapping("feedcome")
    @ResponseBody
    public String feedCome(@RequestParam int consumerId) {
        feedComeService.feedCome(consumerId);
        return "SUCCESS";
    }
}
