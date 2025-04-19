package com.trackit.trackit_server.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trackit.trackit_server.modal.Issue;
import com.trackit.trackit_server.modal.IssueStatus;
import com.trackit.trackit_server.repo.IssueRepository;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    // Get an issue by ID
    public Optional<Issue> getIssueById(Long id) {
        return issueRepository.findById(id);
    }

    // Create a new issue
    public Issue createIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    // Update issue status
    public Issue updateIssueStatus(Long id, IssueStatus status) {
        Optional<Issue> issueOpt = issueRepository.findById(id);
        if (issueOpt.isPresent()) {
            Issue issue = issueOpt.get();
            issue.setStatus(status);
            return issueRepository.save(issue);
        }
        return null; // Could also throw a custom exception
    }
}
