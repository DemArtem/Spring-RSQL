package org.itstep;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        
SpringApplication.run
(DemoApplication.class, args);
    }
}

@Controller
@RequiredArgsConstructor
class HomeController {

    private final PostRepository posts;

    @GetMapping("/")
    public String home(final Model model) {
        //posts.findAll().subscribe(System.out::println);
        Flux<Post> postList = this.posts.findAll();
        model.addAttribute("posts", new ReactiveDataDriverContextVariable(postList, 100));
        return "home";
    }

    @GetMapping("/")
    public String getById(@RequestParam(name = "id") String id, final Model model) {
        Mono<Post> post= this.posts.findById(UUID.fromString(id));
        model.addAttribute("posts", new ReactiveDataDriverContextVariable(post, 100));
        return "getBuId";
    }
}


@Slf4j
@Component
class PostRepository {

    private static final List<Post> DATA = new ArrayList<>();

    static {
        
//IntStream.of(1, 100) //Только два значения
        IntStream.range(1, 100) //Диапазон значений
                .forEachOrdered(i -> DATA.add(
                        Post.builder().id(UUID.randomUUID()).title("post #" + i).content("content of post#" + i)
                                .build()));
        
DATA.stream().forEach(System.out::println);

    }

    Flux<Post> findAll() {
        return Flux.fromIterable(DATA);
    }

    Mono<Post> findById(UUID id) {
        return findAll().filter(p -> p.getId().equals(id)).last();
    }

    Mono<Post> save(Post post) {
        Post saved = Post.builder().id(UUID.randomUUID()).title(post.getTitle())
                .content(post.getContent()).build();
        log.debug("saved post: {}", saved);
        DATA.add(saved);
        return Mono.just(saved);
    }

}

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Post {

    private UUID id;
    private String title;
    private String content;
} 