package ca.sheridancollege.liuzhun.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
public class Message implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NonNull
	private String sender;	
	
	private String senderEmail;

	@NonNull
	private String receiver;
	
	@NonNull
	private String body;

	private int approval; // 0 is pending, 1 is accepted, 2 is rejected

	private int archiveStatus; // 0 is non archived, 1 is archived
}
