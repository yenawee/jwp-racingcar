package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.PlayRequestDto;
import racingcar.dto.PlayResultResponseDto;
import racingcar.service.RacingGameService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RacingGameController {
    private RacingGameService racingGameService;

    public RacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping(value = "/plays", consumes = "application/json")
    public ResponseEntity<PlayResultResponseDto> play(@Valid @RequestBody PlayRequestDto playRequestDto) {
        String names = playRequestDto.getNames();
        Integer count = playRequestDto.getCount();

        return ResponseEntity.ok().body(racingGameService.run(names, count));
    }

    @RequestMapping(value = "/plays", produces = "application/json")
    public ResponseEntity<List<PlayResultResponseDto>> viewHistory() {
        return ResponseEntity.ok().body(racingGameService.getPlayHistory());
    }
}
