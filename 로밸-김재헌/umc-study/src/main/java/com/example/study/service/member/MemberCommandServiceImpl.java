package com.example.study.service.member;

import com.example.study.converter.MemberConverter;
import com.example.study.converter.MemberPreferConverter;
import com.example.study.domain.FoodCategory;
import com.example.study.domain.Member;
import com.example.study.domain.MemberPrefer;
import com.example.study.exception.ErrorStatus;
import com.example.study.exception.handler.FoodCategoryHandler;
import com.example.study.exception.handler.MemberHandler;
import com.example.study.repository.FoodCategoryRepository;
import com.example.study.repository.member.MemberRepository;
import com.example.study.web.dto.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDto.JoinDto request){

        Member newMember = MemberConverter.toMember(request);

        List<FoodCategory> foodCategories = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).toList();

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategories);

        memberPreferList.forEach(memberPrefer -> memberPrefer.setMember(newMember));

        return memberRepository.save(newMember);
    }

    @Override
    public Member find(Long id) {
        return memberRepository.findById(id).orElseThrow(()->new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
    }


}
