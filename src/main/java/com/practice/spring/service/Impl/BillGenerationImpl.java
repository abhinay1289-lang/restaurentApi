package com.practice.spring.service.Impl;

import com.practice.spring.service.BillGeneration;
import org.springframework.stereotype.Service;

@Service
public class BillGenerationImpl implements BillGeneration {
    @Override
    public void billGeneration() {
//        try {
//            String content =
//                    StreamUtils.copyToString(
//                            new ClassPathResource("pdf-template.html").getInputStream(),
//                            Charset.defaultCharset());
//
//            content = content.replaceAll("\\[Candidate's Name\\]", candidate.getName());
//            content = content.replaceAll("\\[Recruiter's Name\\]", recruiter.getFirstName());
//            content =
//                    content.replaceAll(
//                            "\\[Interviewer's Name\\]", evaluator.getFirstName() + " " + evaluator.getLastName());
//            content = content.replaceAll("\\[Job Position\\]", sjd.getName());
//            content = content.replaceAll("\\[Interview Level\\]", request.getInterviewLevel());
//            content = content.replaceAll("\\[Interviewer's Decision\\]", request.getInterviewResult());
//            String profileLink =
//                    "https://tad.peopletech.com/source-job-definition/candidate/"
//                            + Base64.getEncoder().encodeToString(request.getSjdId().toString().getBytes())
//                            + "/"
//                            + Base64.getEncoder().encodeToString(request.getCandidateId().toString().getBytes());
//            content = content.replaceAll("\\[Profile\\]", profileLink);
//            mailService.sendMail(
//                    Arrays.asList(recruiter.getEmail()),
//                    Arrays.asList(evaluator.getEmail()),
//                    "Interview Feedback Submission Confirmation",
//                    content);
//
//        } catch (Exception e) {
//            log.info("Email send failed on feedback submission", e.getStackTrace());
//        }
    }
}
