package com.trackit.trackit_server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trackit.trackit_server.modal.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
}