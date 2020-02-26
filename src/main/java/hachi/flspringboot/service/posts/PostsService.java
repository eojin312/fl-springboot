package hachi.flspringboot.service.posts;

import hachi.flspringboot.domain.posts.Posts;
import hachi.flspringboot.domain.posts.PostsRepository;
import hachi.flspringboot.web.dto.PostsListResponseDto;
import hachi.flspringboot.web.dto.PostsResponseDto;
import hachi.flspringboot.web.dto.PostsSaveRequestDto;
import hachi.flspringboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 트렌잭션 도메인 기능 간의 순서를 보장하는 service
 */
@RequiredArgsConstructor
@Service
public class PostsService {

    @Autowired
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


        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다 id = " + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
/*        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());*/
        return null;
    }
}
