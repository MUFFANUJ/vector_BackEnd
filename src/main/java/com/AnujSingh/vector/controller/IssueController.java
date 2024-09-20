package com.AnujSingh.vector.controller;

import com.AnujSingh.vector.DTO.IssueDTO;
import com.AnujSingh.vector.model.Issue;
import com.AnujSingh.vector.model.User;
import com.AnujSingh.vector.repository.IssueRepository;
import com.AnujSingh.vector.request.IssueRequest;
import com.AnujSingh.vector.response.AuthResponse;
import com.AnujSingh.vector.response.MessageResponse;
import com.AnujSingh.vector.service.IssueService;
import com.AnujSingh.vector.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;
    @Autowired
    private IssueRepository issueRepository;

    @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueById(issueId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long projectId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueByProjectId(projectId));
    }

    @PostMapping
    public ResponseEntity<IssueDTO> createIssue(@RequestBody IssueRequest issue, @RequestHeader("Authorization") String token)
            throws Exception {
        User tokenUser = userService.findUserProfileByJwt(token);
        User user = userService.findUserById(tokenUser.getId());

        Issue issueCreated = issueService.createIssue(issue,tokenUser);
        IssueDTO issueDTO = new IssueDTO();
        issueDTO.setId(issueCreated.getId());
        issueDTO.setTitle(issueCreated.getTitle());
        issueDTO.setDescription(issueCreated.getDescription());
        issueDTO.setAssignee(issueCreated.getAssignee());
        issueDTO.setPriority(issueCreated.getPriority());
        issueDTO.setStatus(issueCreated.getStatus());
        issueDTO.setDueDate(issueCreated.getDueDate());
        issueDTO.setProject(issueCreated.getProject());
        issueDTO.setTags(issueCreated.getTags());
        issueDTO.setProjectId(issueCreated.getProjectID());

        return ResponseEntity.ok(issueDTO);


    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueId, @RequestHeader("Authorization") String token)
            throws Exception {
        User user = userService.findUserProfileByJwt(token);
        issueService.deleteIssue(issueId, user.getId());
        MessageResponse res = new MessageResponse();
        res.setMessage("Issue Deleted Successfully");
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<Issue> addUserToIssue(@PathVariable Long issueId,
                                                @PathVariable Long userId)
            throws Exception {
        Issue issue = issueService.addUsertoIssue(issueId, userId);
        return ResponseEntity.ok(issue);
    }

    @PutMapping("/{issueId}/status/{status}")
    public ResponseEntity<Issue> updateIssueStatus(@PathVariable Long issueId,
                                                @PathVariable String status)
            throws Exception {
        Issue issue = issueService.updateStatus(issueId, status);
        return ResponseEntity.ok(issue);
    }

}
