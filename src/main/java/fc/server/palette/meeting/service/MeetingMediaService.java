package fc.server.palette.meeting.service;

import fc.server.palette.meeting.entity.Meeting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class MeetingMediaService {
    private MediaRepository mediaRepository;

    public String uploadImage(MultipartFile multipartFile){
        String url = "http://fc-pallete.s3-website.ap-northeast-2.amazonaws.com";
        String originName = multipartFile.getOriginalFilename();
        String changedname = changedImageName(originName);
        return url + "/" + changedname;
    }
    public String changedImageName(String originName) { //이미지 이름 중복 방지를 위해 랜덤으로 생성
        String random = UUID.randomUUID().toString();
        return random + originName;
    }

}
