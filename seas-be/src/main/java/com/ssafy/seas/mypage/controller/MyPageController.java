package com.ssafy.seas.mypage.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.seas.common.constants.SuccessCode;
import com.ssafy.seas.common.dto.ApiResponse;
import com.ssafy.seas.flashcard.dto.FavoriteDto;
import com.ssafy.seas.flashcard.service.FlashcardService;
import com.ssafy.seas.member.dto.MemberDto;
import com.ssafy.seas.mypage.dto.MyPageDto;
import com.ssafy.seas.mypage.dto.StreakDto;
import com.ssafy.seas.mypage.service.MyPageService;
import com.ssafy.seas.quiz.dto.IncorrectNoteDto;
import com.ssafy.seas.ranking.dto.BadgeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {
	private final MyPageService myPageService;
	private final FlashcardService flashcardService;

	@GetMapping("/my-info")
	public ApiResponse<MemberDto.MyInfoResponse> getMyInfo(@RequestParam(required = false) String nickname) {

		return ApiResponse.success(SuccessCode.GET_SUCCESS, myPageService.getMyInfo(nickname));
	}

	// 방사형 그래프
	@GetMapping("/quiz-rate")
	public ApiResponse<List<MyPageDto.QuizRate>> getQuizRate(@RequestParam(required = false) String nickname) {

		return ApiResponse.success(SuccessCode.GET_SUCCESS, myPageService.getQuizRate(nickname));
	}

	// 플래시카드 즐겨찾기 목록
	@GetMapping("/flashcard/favorite")
	public ApiResponse<List<FavoriteDto.CardIdPerCategory>> getFavoriteFlashcardIds() {
		return ApiResponse.success(SuccessCode.GET_SUCCESS, flashcardService.getFavoriteFlashcardIds());
	}

	// 오답노트 목록 불러오기
	@GetMapping("/incorrect")
	public ApiResponse<List<IncorrectNoteDto.QuizIdPerCategory>> getIncorrectNotes() {
		return ApiResponse.success(SuccessCode.GET_SUCCESS, myPageService.getIncorrectNotes());
	}

	// 성적 추이 그래프
	@GetMapping("/graph")
	public ApiResponse<List<MyPageDto.PerformanceGraph>> getGraph() {

		return ApiResponse.success(SuccessCode.GET_SUCCESS, myPageService.getQuizPerformanceGraph());
	}

	@GetMapping("/badge")
	public ApiResponse<List<BadgeDto.BadgeResponse>> getBadges(@RequestParam(required = false) String nickname) {

		return ApiResponse.success(SuccessCode.GET_SUCCESS, myPageService.getBadges(nickname));
	}

	// 스트릭
	@GetMapping("/history")
	public ApiResponse<List<StreakDto.Response>> getStreak() {
		return ApiResponse.success(SuccessCode.GET_SUCCESS, myPageService.getStreak());
	}

	@GetMapping("/incorrect-note/info/{quizId}")
	public ApiResponse<MyPageDto.IncorrectNoteInfo> getIncorrectNoteInfo(@PathVariable("quizId") Integer quizId){
		return ApiResponse.success(SuccessCode.GET_SUCCESS, myPageService.getIncorrectNoteInfo(quizId));
	}

}
