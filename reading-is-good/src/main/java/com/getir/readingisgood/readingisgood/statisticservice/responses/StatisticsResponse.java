package com.getir.readingisgood.readingisgood.statisticservice.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.getir.readingisgood.readingisgood.authenticationservice.responses.BaseResponse;
import com.getir.readingisgood.readingisgood.statisticservice.models.StatisticsModel;

import java.math.BigDecimal;
import java.util.HashMap;

public class StatisticsResponse extends BaseResponse {
    @JsonProperty(value = "statistics", required = true)
    private HashMap<String, StatisticsModel> statistics;

    public StatisticsResponse(String returnCode, String errorMessage, HashMap<String, StatisticsModel> statistics) {
        super(returnCode, errorMessage);
        this.statistics = statistics;
    }
}

