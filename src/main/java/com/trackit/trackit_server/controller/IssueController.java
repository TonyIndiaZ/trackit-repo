package com.trackit.trackit_server.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trackit.trackit_server.modal.Issue;
import com.trackit.trackit_server.modal.IssueStatus;
import com.trackit.trackit_server.service.IssueService;

@RestController
@RequestMapping("/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    // Create a new issue
    @PostMapping("/new")
    public ResponseEntity<Issue> createIssue(@RequestBody Issue issue) {
        Issue createdIssue = issueService.createIssue(issue);
        return ResponseEntity.ok(createdIssue);
    }

    // Get issue by ID
    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssue(@PathVariable Long id) {
        Optional<Issue> issue = issueService.getIssueById(id);
        return issue.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update issue status
    @PutMapping("/{id}/status")
    public ResponseEntity<Issue> updateIssueStatus(@PathVariable Long id, @RequestParam IssueStatus status) {
        Issue updatedIssue = issueService.updateIssueStatus(id, status);
        return updatedIssue != null ? ResponseEntity.ok(updatedIssue) : ResponseEntity.notFound().build();
    }
}
