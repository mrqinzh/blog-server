package com.mrqinzh.user.domain.dto;

import com.mrqinzh.framework.common.statistics.StatisticsDTO;
import lombok.Data;

@Data
public class UserStatisticsDTO extends StatisticsDTO {

    private long count;

}
