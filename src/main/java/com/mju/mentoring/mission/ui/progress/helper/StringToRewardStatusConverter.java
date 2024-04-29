package com.mju.mentoring.mission.ui.progress.helper;

import com.mju.mentoring.mission.domain.progress.RewardStatus;
import org.springframework.core.convert.converter.Converter;

public class StringToRewardStatusConverter implements Converter<String, RewardStatus> {

    @Override
    public RewardStatus convert(final String source) {
        return RewardStatus.valueOf(source.toUpperCase());
    }
}
