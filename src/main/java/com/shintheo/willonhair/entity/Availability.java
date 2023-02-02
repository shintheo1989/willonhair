package com.shintheo.willonhair.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Availability {
	public List<WeekDaysDao> weekDays;
	public List<DaysOffDao> daysOff;
}
