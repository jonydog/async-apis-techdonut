package TechDonut.NonBlocking.controllers

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration


@RestController
class FluxAndMonoController {

    @GetMapping("/flux")
    fun returnFlux(): Flux<Int> {
        return Flux.just(1,2,3,4).
                delayElements(Duration.ofSeconds(1)).log()
    }

    @GetMapping("/fluxstream", produces= [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun returnFluxstream(): Flux<Int> {
        return Flux.just(1,2,3,4).
        delayElements(Duration.ofSeconds(1)).log()
    }

    @GetMapping("/cenas")
    fun returnHello(): Mono<String> {
        val hello = WebClient.create("http://www.mocky.io/v2/5ea850142d00005a343a3eea").get().retrieve().bodyToMono(String::class.java).log()
        val goodbye = WebClient.create("http://www.mocky.io/v2/5ea84fba2d00005a343a3ee7").get().retrieve().bodyToMono(String::class.java).log()

        return Mono.zip(hello, goodbye) { h, g -> h+"Francisco"+g}
    }

    @GetMapping("/cenasflux", produces= [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun returnFluxHello(): Flux<String> {
        val helloComplete = Flux.concat(
                WebClient.create("http://www.mocky.io/v2/5ea850142d00005a343a3eea").get().retrieve().bodyToFlux(String::class.java),
                WebClient.create("http://www.mocky.io/v2/5ea84fba2d00005a343a3ee7").get().retrieve().bodyToFlux(String::class.java)
        ).delayElements(Duration.ofSeconds(1)).log()
        return helloComplete
    }

}