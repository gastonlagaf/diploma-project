package com.vehicle.rentservice.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "REPLIES", schema = "CAR_RENT_SERVICE")
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
	
	@Id
	@Column(name = "REPLY_ID")
	@SequenceGenerator(name = "REPLIES_SEQ", sequenceName = "REPLIES_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "REPLIES_SEQ", strategy = GenerationType.SEQUENCE)
	@Getter private Long id;
	
	@Column(name = "AUTHOR")
	@Getter @Setter private String authorName;
	
	@Column(name = "REPLY_TEXT")
	@Getter @Setter private String text;

}
