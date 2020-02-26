package hachi.flspringboot.web;

import hachi.flspringboot.service.posts.PostsService;
import hachi.flspringboot.web.dto.PostsResponseDto;
import hachi.flspringboot.web.dto.PostsSaveRequestDto;
import hachi.flspringboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * API 요청을 받을 controller
 */
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    /**
     * 포스트 등록 API
     * @param requestDto
     * @return
     */
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    /**
     * 포스트 변경 API
     * @param id
     * @param requestDto
     * @return
     */
    @PostMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    /**
     * 포스트 조회 API by 포스트ID
     * @param id
     * @return
     */
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
}