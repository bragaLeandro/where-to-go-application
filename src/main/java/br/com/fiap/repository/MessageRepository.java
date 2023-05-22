package br.com.fiap.repository;

import br.com.fiap.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "select m from Message m where m.user.id = :id")
    List<Message> findMessageByUserId(@Param("id") Long id);

    Message findByIdAndUserId(Long messageId, Long userId);

}