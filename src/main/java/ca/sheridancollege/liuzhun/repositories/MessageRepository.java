package ca.sheridancollege.liuzhun.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.liuzhun.beans.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	public List<Message> findByApprovalAndArchiveStatus(int approval, int archiveStatus);
	public List<Message> findByReceiverAndApproval(String receiver, int approval);
}
