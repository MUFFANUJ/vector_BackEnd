package com.AnujSingh.vector.service;


import com.AnujSingh.vector.model.Issue;
import com.AnujSingh.vector.model.User;
import com.AnujSingh.vector.request.IssueRequest;

import java.util.List;
import java.util.Optional;

public interface IssueService {
    Issue getIssueById(long issueId) throws Exception;

    List<Issue> getIssueByProjectId(long projectId) throws Exception;

    Issue createIssue(IssueRequest issue, User user) throws Exception;

    void deleteIssue(Long issueId,Long userId) throws Exception;

    Issue addUsertoIssue(Long issueId, Long userId) throws Exception;

    Issue updateStatus(Long issueId, String status) throws Exception;
}
