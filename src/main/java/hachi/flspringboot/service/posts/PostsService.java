package hachi.flspringboot.service.posts;

import hachi.flspringboot.domain.posts.Posts;
import hachi.flspringboot.domain.posts.PostsRepository;
import hachi.flspringboot.web.dto.PostsResponseDto;
import hachi.flspringboot.web.dto.PostsSaveRequestDto;
import hachi.flspringboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    public final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. + id = " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다 id = " + id));

        return new PostsResponseDto(entity);
    }
}
