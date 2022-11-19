package com.seonko.OurLittleDiary.service;

import com.seonko.OurLittleDiary.config.auth.PrincipalDetails;
import com.seonko.OurLittleDiary.domain.Post;
import com.seonko.OurLittleDiary.dto.CreatePostDTO;

import java.util.List;

public interface PostService {

    void createPost(PrincipalDetails principalDetails, CreatePostDTO createPostDTO) throws Exception;
    void updatePost(PrincipalDetails principalDetails, CreatePostDTO createPostDTO) throws Exception;
    List<Post> diaryPostList(Long diaryId, String targetDate) throws Exception;
    Post readPost(Long postId) throws Exception;
    void deletePost(Long postId) throws Exception;

}
