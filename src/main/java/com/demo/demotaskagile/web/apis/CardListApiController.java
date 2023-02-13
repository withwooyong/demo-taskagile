package com.demo.demotaskagile.web.apis;

import com.demo.demotaskagile.domain.application.CardListService;
import com.demo.demotaskagile.domain.application.commands.AddCardListCommand;
import com.demo.demotaskagile.domain.application.commands.ChangeCardListPositionsCommand;
import com.demo.demotaskagile.domain.model.cardlist.CardList;
import com.demo.demotaskagile.web.payload.AddCardListPayload;
import com.demo.demotaskagile.web.payload.ChangeCardListPositionsPayload;
import com.demo.demotaskagile.web.results.AddCardListResult;
import com.demo.demotaskagile.web.results.ApiResult;
import com.demo.demotaskagile.web.results.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CardListApiController extends AbstractBaseController {

    private CardListService cardListService;

    public CardListApiController(CardListService cardListService) {
        this.cardListService = cardListService;
    }

    @PostMapping("/api/card-lists")
    public ResponseEntity<ApiResult> addCardList(@RequestBody AddCardListPayload payload,
                                                 HttpServletRequest request) {
        AddCardListCommand command = payload.toCommand();
        addTriggeredBy(command, request);

        CardList cardList = cardListService.addCardList(command);
        return AddCardListResult.build(cardList);
    }

    @PostMapping("/api/card-lists/positions")
    public ResponseEntity<ApiResult> changeCardListPositions(@RequestBody ChangeCardListPositionsPayload payload,
                                                             HttpServletRequest request) {
        ChangeCardListPositionsCommand command = payload.toCommand();
        addTriggeredBy(command, request);

        cardListService.changePositions(command);
        return Result.ok();
    }
}
