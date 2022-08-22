package com.witbackend.week8.repository;

import com.witbackend.week8.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReplyRepository extends JpaRepository<Reply, UUID> {
}
