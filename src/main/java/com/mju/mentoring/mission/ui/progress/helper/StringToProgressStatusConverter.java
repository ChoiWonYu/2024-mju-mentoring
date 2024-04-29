package com.mju.mentoring.mission.ui.progress.helper;

import com.mju.mentoring.mission.domain.progress.ProgressStatus;
import org.springframework.core.convert.converter.Converter;

public class StringToProgressStatusConverter implements Converter<String, ProgressStatus> {

    @Override
    public ProgressStatus convert(final String source) {
            return ProgressStatus.valueOf(source.toUpperCase());
    }
}
