package com.shintheo.willonhair.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.shintheo.willonhair.base.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "t_appointment")
public class AppointmentDao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "c_id", updatable = false, nullable = false)
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(name = "c_status", nullable = false, length = 20)
	private Status status;
	@DateTimeFormat
	@Column(name = "c_bookingStart", nullable = false)
	private Date bookingStart;
	@DateTimeFormat()
	@Column(name = "c_bookingEnd", nullable = false)
	private Date bookingEnd;
	@Column(name = "c_notifyParticipants", nullable = false, length = 1)
	private int notifyParticipants;
	@Column(name = "c_serviceId", nullable = false, length = 11)
	private int serviceId;
	@Column(name = "c_packageId", nullable = false, length = 11)
	private int packageId;
	@Column(name = "c_providerId", nullable = false, length = 11)
	private int providerId;
	@Column(name = "c_locationId", nullable = false, length = 11)
	private int locationId;
	@Column(name = "c_internalNotes", length = 627)
	private String internalNotes;
	@Column(name = "c_googleCalendarEventId")
	private String googleCalendarEventId;
	@Column(name = "c_googleMeetUrl")
	private String googleMeetUrl;
	@Column(name = "c_outlookCalendarEventId")
	private String outlookCalendarEventId;
	@Column(name = "c_zoomMeeting", length = 627)
	private String zoomMeeting;
	@Column(name = "c_lessonSpace", length = 627)
	private String lessonSpace;
	@Column(name = "c_parentId", nullable = false, length = 11)
	private int parentId;
}
