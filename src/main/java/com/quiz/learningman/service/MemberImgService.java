package com.quiz.learningman.service;

import com.quiz.learningman.entity.MemberProfileImg;
import com.quiz.learningman.repository.MemberImgRepository;
import com.quiz.learningman.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MemberImgService {

    @Value("${memberImgLocation}")
    private String memberImgLocation;

    private final MemberImgRepository memberImgRepository;
    private final FileService fileService;

    public String saveMemberImg(MemberProfileImg memberProfileImg, MultipartFile memberImgFile) throws Exception{
        String oriImgName = memberImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(memberImgLocation, oriImgName, memberImgFile.getBytes());
            imgUrl = "/images/member/" + imgName;
        }
        memberProfileImg.updateMemberImg(oriImgName, imgName, imgUrl);
        memberImgRepository.save(memberProfileImg);
        return memberProfileImg.getImgUrl();
    }
    public String baseImg(Long id){
        MemberProfileImg memberProfileImg = memberImgRepository.findByMemberImgIdJpql(id);
        return memberProfileImg.getImgUrl();
    }
}